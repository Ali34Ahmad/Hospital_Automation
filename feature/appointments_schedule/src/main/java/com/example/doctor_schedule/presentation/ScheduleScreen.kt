package com.example.doctor_schedule.presentation

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.constants.icons.AppIcons
import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.doctor_schedule.presentation.custom_screens.PermissionRequiredScreen
import com.example.doctor_schedule.presentation.custom_screens.SuccessScreen
import com.example.doctor_schedule.presentation.model.AppointmentUIModel
import com.example.ext.toAppropriateDateFormat
import com.example.model.DrawerButton
import com.example.model.enums.ScreenState
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.drawers.EmployeeDrawer
import com.example.ui_components.components.items.FilterItem
import com.example.ui_components.components.topbars.custom.ScheduleTopBar

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel,
    navigationActions: ScheduleNavigationActions,
    modifier: Modifier = Modifier
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val appointments = viewModel.appointmentsFlow.collectAsLazyPagingItems()
    ScheduleScreen(
        uiState = uiState.value,
        appointments = appointments,
        navigationActions = navigationActions,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
internal fun ScheduleScreen(
    uiState: ScheduleUIState,
    appointments: LazyPagingItems<AppointmentUIModel>,
    navigationActions: ScheduleNavigationActions,
    onAction:(ScheduleUIAction)-> Unit,
    modifier: Modifier = Modifier
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(uiState.isDrawerOpened) {
        if(uiState.isDrawerOpened)drawerState.open()
        else drawerState.close()
    }


    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                Log.d("Doctor Screen Observer","is first launch :${uiState.isFirstLaunch}")
                if (uiState.isFirstLaunch) {
                    onAction(
                        ScheduleUIAction.UpdateIsFirstLaunchToFalse
                    )
                } else {
                    onAction(
                        ScheduleUIAction.Refresh
                    )
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    appointments.let {
        when(it.loadState.refresh){
            is LoadState.Error ->{
                onAction(ScheduleUIAction.UpdateState(ScreenState.ERROR))
            }
            LoadState.Loading -> {
                onAction(ScheduleUIAction.UpdateState(ScreenState.LOADING))
            }
            is LoadState.NotLoading -> {
                onAction(ScheduleUIAction.UpdateState(ScreenState.SUCCESS))
            }
        }
    }
    val drawerButtons = listOf(
        DrawerButton(
            text = R.string.profile,
            image = AppIcons.Outlined.accountCircle,
            onClick = {
                navigationActions.navigateToDoctorProfile()
            }
        ),
        DrawerButton(
            text = R.string.notifications,
            image = AppIcons.Outlined.notification,
            onClick = {
                navigationActions.navigateToNotifications()
            }
        ),
        DrawerButton(
            text = R.string.medical_records,
            image = AppIcons.Outlined.medicalRecords,
            onClick = {
                navigationActions.navigateToMedicalRecords()
            },
            enabled = uiState.isPermissionGranted || uiState.hasAdminAccess
        ),
        DrawerButton(
            text = R.string.prescriptions,
            image = AppIcons.Outlined.prescription,
            onClick = {
                navigationActions.navigateToPrescriptions()
            },
            enabled = uiState.isPermissionGranted || uiState.hasAdminAccess
        ),
        DrawerButton(
            text = R.string.vaccines,
            image = AppIcons.Outlined.vaccines,
            onClick = {
                navigationActions.navigateToVaccines()
            },
            enabled = uiState.isPermissionGranted || uiState.hasAdminAccess
        ),
        DrawerButton(
            text = R.string.vaccine_table,
            image = AppIcons.Outlined.medicalRecords,
            onClick = {
                navigationActions.navigateToVaccineTable()
            },
            enabled = uiState.isPermissionGranted || uiState.hasAdminAccess
        ),


    )
    EmployeeDrawer(
        state = drawerState,
        title = R.string.doctor,
        buttons = drawerButtons,
        selectedIndex = null,
        onItemSelected = {
            drawerButtons[it].onClick()
            onAction(ScheduleUIAction.ToggleDrawer)
        },
        onChangeThemeClick = {
            onAction(
                ScheduleUIAction.ToggleTheme
            )
        },
        isDarkTheme = uiState.isDarkTheme
    ) {
        Scaffold(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surface,
            topBar = {
                ScheduleTopBar(
                    isPermissionsGranted = uiState.isPermissionGranted || uiState.hasAdminAccess,
                    isSearchBarVisible = uiState.isSearchBarVisible,
                    searchQuery = uiState.searchQuery,
                    onQueryChanged = { newValue ->
                        onAction(
                            ScheduleUIAction.UpdateSearchQuery(newValue)
                        )
                    },
                    onQueryDeleted = {
                        onAction(
                            ScheduleUIAction.UpdateSearchQuery("")
                        )
                    },
                    onSearch = {
                        onAction(
                            ScheduleUIAction.ShowSearchBar
                        )
                    },
                    onBackButtonClick = {
                        onAction(
                            ScheduleUIAction.HideSearchBar
                        )
                    },
                    onDatePickerIconClick = {
                        onAction(
                            ScheduleUIAction.ShowDatePicker
                        )
                    },
                    onDrawerOpened = {
                        onAction(
                            ScheduleUIAction.ToggleDrawer
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    imageUrl = uiState.imageUrl,
                    name = uiState.name,
                    speciality = uiState.speciality,
                    showChildPlaceholder = uiState.searchType == AppointmentSearchType.CHILD,
                    onTitleClick = {
                        uiState.id?.let { id->
                            when(uiState.searchType){
                                AppointmentSearchType.DOCTOR -> navigationActions.navigateToDoctorProfile(id = id)
                                AppointmentSearchType.CHILD -> navigationActions.navigateToChildProfile(id = id)
                                AppointmentSearchType.USER -> navigationActions.navigateToUserProfileProfile(id = id)
                            }
                        }
                    },
                    onNavigateUp = navigationActions::navigateUp,
                    canNavigateUp = uiState.canNavigateUp,
                )
            }
        ) { innerPadding ->

            //either show the permission required screen or show the appointments

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.animateContentSize()
                    .padding(innerPadding)
            ) {
                AnimatedVisibility(
                uiState.hasDateFilter(),
                enter = slideInVertically(initialOffsetY ={-it}),
                exit = slideOutVertically(targetOffsetY = {-it})
            ) {
                FilterItem(
                    title = stringResource(R.string.date_filter),
                    subtitle = uiState.selectedDate?.toAppropriateDateFormat()?.toString().orEmpty(),
                    onClose = {
                        onAction(
                            ScheduleUIAction.ClearDateFilter
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
                AnimatedContent(
                    uiState.isPermissionGranted || uiState.hasAdminAccess
                ) { state ->
                    when (state) {
                        true -> SuccessScreen(
                            uiState = uiState,
                            onAction = onAction,
                            appointments = appointments,
                            onNavigateToAppointmentDetails = navigationActions::navigateToAppointmentDetails,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = MaterialTheme.sizing.small16,
                                    vertical = MaterialTheme.sizing.small8,
                                )
                        )

                        false ->
                            PermissionRequiredScreen(
                                state = uiState.permissionsState,
                                isRefreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(ScheduleUIAction.RefreshPermission)
                                },
                                modifier = modifier
                                    .padding(MaterialTheme.spacing.medium16)
                            )

                    }
                }
            }
        }
    }
}