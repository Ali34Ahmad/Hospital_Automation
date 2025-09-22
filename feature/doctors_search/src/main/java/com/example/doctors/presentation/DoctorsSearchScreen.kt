package com.example.doctors.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.constants.icons.AppIcons
import com.example.model.DrawerButton
import com.example.model.LabeledBadgeData
import com.example.model.doctor.DoctorData
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.drawers.EmployeeDrawer
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.list_items.ProfileListItem
import com.example.ui_components.components.progress_indicator.SmallCircularProgressIndicator
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.tab.CustomTabsLayout
import com.example.ui_components.components.topbars.custom.DoctorSearchTopBar

@Composable
fun DoctorSearchScreen(
    viewModel: DoctorsSearchViewModel,
    navigationActions: DoctorsSearchNavigationActions,
    modifier: Modifier = Modifier,
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val doctors = viewModel.doctors.collectAsLazyPagingItems()
    DoctorSearchScreen(
        onAction = viewModel::onAction,
        uiState = uiState.value,
        navigationActions = navigationActions,
        doctors = doctors,
        modifier = modifier
    )
}


@Composable
fun DoctorSearchScreen(
    onAction: (DoctorsSearchUIAction)-> Unit,
    navigationActions: DoctorsSearchNavigationActions,
    uiState: DoctorsSearchUIState,
    doctors: LazyPagingItems<DoctorData>,
    modifier: Modifier = Modifier,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(uiState.isDrawerOpened) {
        if(uiState.isDrawerOpened) drawerState.open()
        else drawerState.close()
    }


        when(doctors.loadState.refresh){
            is LoadState.Error -> onAction(DoctorsSearchUIAction.UpdateScreenState(ScreenState.ERROR))
            LoadState.Loading -> onAction(DoctorsSearchUIAction.UpdateScreenState(ScreenState.LOADING))
            is LoadState.NotLoading -> onAction(DoctorsSearchUIAction.UpdateScreenState(ScreenState.SUCCESS))
        }

    val drawerButtons = listOf(
        DrawerButton(
            text = R.string.profile,
            image = AppIcons.Outlined.accountCircle,
            onClick = {
                navigationActions.navigateToAdminProfile()
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
            text = R.string.vaccines,
            image = AppIcons.Outlined.vaccines,
            onClick = {
                navigationActions.navigateToVaccines()
            },
        ),

        DrawerButton(
            text = R.string.vaccination_table,
            image = AppIcons.Outlined.vaccinationTable,
            onClick = {
                navigationActions.navigateToVaccineTable()
            },
        ),
    )
    val tabs = EmployeeState.entries.map { status->
        LabeledBadgeData(
            label = status.toString(),
            badge = uiState.statistics.getByState(status)
        )
    }
    EmployeeDrawer(
        state = drawerState,
        title = R.string.medicare,
        modifier = Modifier,
        buttons = drawerButtons,
        selectedIndex = null,
        onItemSelected = {
            drawerButtons[it].onClick()
            onAction(DoctorsSearchUIAction.ToggleDrawer)
        },
        onChangeThemeClick = {
            onAction(DoctorsSearchUIAction.ToggleTheme)
        },
        isDarkTheme = uiState.isDarkTheme,
    ) {
        Scaffold (
            topBar = {
                DoctorSearchTopBar(
                    state = uiState.topBarState,
                    onToggleState = {
                        onAction(
                            DoctorsSearchUIAction.ToggleTopBarState
                        )
                    },
                    title = uiState.clinicName,
                    searchQuery = uiState.searchQuery,
                    onQueryChanged = {
                        onAction(
                            DoctorsSearchUIAction.UpdateSearchQuery(it)
                        )
                    },
                    onClearQuery = {
                        onAction(
                            DoctorsSearchUIAction.UpdateSearchQuery("")
                        )
                    },
                    onToggleButtonClick = {
                        onAction(
                            DoctorsSearchUIAction.ToggleDrawer
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    onNavigateUp = navigationActions::navigateUp
                )
            },
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surface
        ){ innerPadding->
            Column(
                modifier = Modifier.padding(innerPadding)
                    .padding(MaterialTheme.spacing.medium16),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CustomTabsLayout(
                    items = tabs,
                    selectedItemIndex = uiState.selectedTab.ordinal,
                    onItemSelected = {
                        onAction(DoctorsSearchUIAction.UpdateTab(EmployeeState.entries[it]))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                AnimatedContent(
                    targetState = uiState.screenState
                ) { state->
                    when(state){
                        ScreenState.IDLE -> Unit
                        ScreenState.LOADING ->{
                            FetchingDataItem(
                                Modifier.fillMaxSize()
                            )
                        }
                        ScreenState.ERROR -> {
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxHeight(),
                                onRefresh = {
                                    onAction(DoctorsSearchUIAction.Refresh)
                                },
                            ) {
                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                        ScreenState.SUCCESS -> {
                                val itemCount = doctors.itemCount
                                //if no items
                                if (itemCount == 0) {
                                    PullToRefreshColumn(
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxHeight(),
                                        refreshing = uiState.isRefreshing,
                                        onRefresh = {
                                            onAction(
                                                DoctorsSearchUIAction.Refresh
                                            )
                                        },
                                    ) {
                                        ErrorComponent(
                                            modifier = Modifier
                                                .fillMaxWidth() ,
                                            title = stringResource(R.string.no_matching_result),
                                            description = stringResource(R.string.no_appointment_subtitle)
                                        )
                                    }
                                } else {
                                    PullToRefreshBox(
                                        refreshing = uiState.isRefreshing,
                                        onRefresh = {
                                            onAction(
                                                DoctorsSearchUIAction.Refresh
                                            )
                                        },
                                    ) {
                                        LazyColumn(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                        ) {
                                            items(itemCount) { index ->
                                                val doctor = doctors[index]
                                                doctor?.let { doctor ->
                                                    ProfileListItem(
                                                        imageUrl = doctor.imageUrl,
                                                        title = doctor.fullName,
                                                        subtitle = doctor.speciality
                                                            ?: stringResource(R.string.not_determined),
                                                        onClick = {
                                                            doctor.id?.let { doctorId ->
                                                                navigationActions.navigateToDoctorProfile(
                                                                    doctorId
                                                                )
                                                            }
                                                        },
                                                        modifier = Modifier.fillMaxWidth(),
                                                    )
                                                }
                                            }
                                            if (doctors.loadState.append == LoadState.Loading) {
                                                item {
                                                    SmallCircularProgressIndicator(
                                                        modifier = Modifier.fillMaxWidth()
                                                            .padding(MaterialTheme.spacing.medium16)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}