package com.example.doctor_schedule.presentation

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.constants.icons.AppIcons
import com.example.doctor_schedule.presentation.custom_screens.PermissionRequiredScreen
import com.example.doctor_schedule.presentation.custom_screens.SuccessScreen
import com.example.doctor_schedule.presentation.model.AppointmentUIModel
import com.example.ext.toAppropriateFormat
import com.example.model.DrawerButton
import com.example.model.enums.ScreenState
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.drawers.EmployeeDrawer
import com.example.ui_components.components.items.FilterItem
import com.example.ui_components.components.topbars.custom.DoctorScheduleTopBar
import kotlinx.coroutines.launch

@Composable
fun DoctorScheduleScreen(
    viewModel: DoctorScheduleViewModel,
    navigationActions: DoctorScheduleNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val appointments = viewModel.appointmentsFlow.collectAsLazyPagingItems()
    DoctorScheduleScreen(
        uiState = uiState.value,
        appointments = appointments,
        navigationActions = navigationActions,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}
@Composable
internal fun DoctorScheduleScreen(
    uiState: DoctorScheduleUIState,
    appointments: LazyPagingItems<AppointmentUIModel>,
    navigationActions: DoctorScheduleNavigationActions,
    onAction:(DoctorScheduleUIAction)-> Unit,
    modifier: Modifier = Modifier
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState.isDrawerOpened) {
        if(uiState.isDrawerOpened)drawerState.open()
        else drawerState.close()
    }

    appointments.let {
        when(it.loadState.refresh){
            is LoadState.Error ->{
                Log.d("DoctorScheduleViewModel", "Error ")
                onAction(DoctorScheduleUIAction.UpdateState(ScreenState.ERROR))
            }
            LoadState.Loading -> {
                Log.d("DoctorScheduleViewModel", "Loading ")
                onAction(DoctorScheduleUIAction.UpdateState(ScreenState.LOADING))
            }
            is LoadState.NotLoading -> {
                Log.d("DoctorScheduleViewModel", "Not loading ")
                onAction(DoctorScheduleUIAction.UpdateState(ScreenState.SUCCESS))
            }
        }
    }
    val drawerButtons = listOf(
        DrawerButton(
            text = R.string.profile,
            image = AppIcons.Outlined.accountCircle,
            onClick = {
                navigationActions.navigateToDoctorProfile(doctorId = uiState.doctorId)
            }
        ),
        DrawerButton(
            text = R.string.notifications,
            image = AppIcons.Outlined.notification,
            onClick = {
                navigationActions.navigateToNotifications(doctorId = uiState.doctorId)
            }
        ),
        DrawerButton(
            text = R.string.medical_records,
            image = AppIcons.Outlined.medicalRecords,
            onClick = {
                navigationActions.navigateToMedicalRecords(doctorId = uiState.doctorId)
            }
        ),
        DrawerButton(
            text = R.string.prescriptions,
            image = AppIcons.Outlined.prescription,
            onClick = {
                navigationActions.navigateToPrescriptions(doctorId = uiState.doctorId)
            }
        ),
        DrawerButton(
            text = R.string.vaccines,
            image = AppIcons.Outlined.vaccines,
            onClick = {
                navigationActions.navigateToVaccines(doctorId = uiState.doctorId)
            }
        ),
        DrawerButton(
            text = R.string.vaccine_table,
            image = AppIcons.Outlined.medicalRecords,
            onClick = {
                navigationActions.navigateToMedicalRecords(doctorId = uiState.doctorId)
            }
        ),


    )
    EmployeeDrawer(
        state = drawerState,
        title = R.string.mail,
        buttons = drawerButtons,
        selectedIndex = null,
        onItemSelected = {
            drawerButtons[it].onClick()
            scope.launch {
                drawerState.close()
            }
        },
        onChangeThemeClick = {
            onAction(
                DoctorScheduleUIAction.ToggleTheme
            )
        },
        isDarkTheme = uiState.isDarkTheme
    ) {
        Scaffold(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surface,
            topBar = {
                DoctorScheduleTopBar(
                    isPermissionsGranted = uiState.isPermissionGranted,
                    isSearchBarVisible = uiState.isSearchBarVisible,
                    searchQuery = uiState.searchQuery,
                    onQueryChanged = { newValue ->
                        onAction(
                            DoctorScheduleUIAction.UpdateSearchQuery(newValue)
                        )
                    },
                    onQueryDeleted = {
                        onAction(
                            DoctorScheduleUIAction.UpdateSearchQuery("")
                        )
                    },
                    onSearch = {
                        onAction(
                            DoctorScheduleUIAction.ShowSearchBar
                        )
                    },
                    onBackButtonClick = {
                        onAction(
                            DoctorScheduleUIAction.HideSearchBar
                        )
                    },
                    onDatePickerIconClick = {
                        onAction(
                            DoctorScheduleUIAction.ShowDatePicker
                        )
                    },
                    onDrawerOpened = {
                        onAction(
                            DoctorScheduleUIAction.ToggleDrawer
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
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
                    subtitle = uiState.selectedDate?.toAppropriateFormat().toString(),
                    onClose = {
                        onAction(
                            DoctorScheduleUIAction.ClearDateFilter
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
                AnimatedContent(
                    uiState.isPermissionGranted
                ) { state ->
                    when (state) {
                        true -> SuccessScreen(
                            uiState = uiState,
                            onAction = onAction,
                            appointments = appointments,
                            onNavigateToAppointmentDetails = navigationActions::navigateToAppointmentDetails,
                            modifier = modifier
                                .padding(
                                    horizontal = MaterialTheme.sizing.small16,
                                    vertical = MaterialTheme.sizing.small8,
                                )
                                .fillMaxSize()
                        )

                        false ->
                            PermissionRequiredScreen(
                                state = uiState.permissionsState,
                                isRefreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(DoctorScheduleUIAction.RefreshPermission)
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