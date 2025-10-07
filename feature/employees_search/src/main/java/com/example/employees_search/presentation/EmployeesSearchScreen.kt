package com.example.employees_search.presentation

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
import com.example.model.employee.EmployeeData
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.drawers.EmployeeDrawer
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.list_items.SimpleProfileListItem
import com.example.ui_components.components.progress_indicator.SmallCircularProgressIndicator
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.tab.CustomTabsLayout
import com.example.ui_components.components.topbars.HospitalAutomationTopBarWithSearchBar

@Composable
fun EmployeesSearchScreen(
    viewModel: EmployeeSearchViewModel,
    navigationActions: EmployeesSearchNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val employees = viewModel.employees.collectAsLazyPagingItems()

    EmployeesSearchScreen(
        onAction = viewModel::onAction,
        uiState = uiState.value,
        navigationActions = navigationActions,
        employees = employees,
        modifier = modifier,
    )
}


@Composable
fun EmployeesSearchScreen(
    onAction: (EmployeesSearchUIAction)-> Unit,
    navigationActions: EmployeesSearchNavigationActions,
    uiState: EmployeesSearchUIState,
    employees: LazyPagingItems<EmployeeData>,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(uiState.isDrawerOpened) {
        if(uiState.isDrawerOpened)drawerState.open()
        else drawerState.close()
    }

    when(employees.loadState.refresh){
        is LoadState.Error -> onAction(EmployeesSearchUIAction.UpdateScreenState(ScreenState.ERROR))
        LoadState.Loading -> onAction(EmployeesSearchUIAction.UpdateScreenState(ScreenState.LOADING))
        is LoadState.NotLoading -> onAction(EmployeesSearchUIAction.UpdateScreenState(ScreenState.SUCCESS))
    }
    val tabs = EmployeeState.entries.map { status->
        LabeledBadgeData(
            label = status.toString(),
            badge = uiState.statistics.getByState(status)
        )
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
            image = AppIcons.Outlined.vaccine,
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
    EmployeeDrawer(
        state = drawerState,
        title = R.string.medicare,
        modifier = Modifier,
        buttons = drawerButtons,
        selectedIndex = null,
        onItemSelected = {
            drawerButtons[it].onClick()
            onAction(EmployeesSearchUIAction.ToggleDrawer)
        },
        onChangeThemeClick = {
            onAction(EmployeesSearchUIAction.ToggleTheme)
        },
        isDarkTheme = uiState.isDarkTheme,
    ){
        Scaffold(
            topBar = {
                HospitalAutomationTopBarWithSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = {
                        onAction(EmployeesSearchUIAction.UpdateSearchQuery(it))
                    },
                    onTrailingIconClick = {
                        onAction(
                            EmployeesSearchUIAction.UpdateSearchQuery("")
                        )
                    },
                    placeholderText = R.string.emplyee_name,
                    onNavigationIconCLick = {
                        onAction(EmployeesSearchUIAction.ToggleDrawer)
                    },
                    navigationIcon = AppIcons.Outlined.menu
                )
            },
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surface
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(MaterialTheme.spacing.medium16),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CustomTabsLayout(
                    items = tabs,
                    selectedItemIndex = uiState.selectedTab.ordinal,
                    onItemSelected = {
                        onAction(EmployeesSearchUIAction.UpdateTab(EmployeeState.entries[it]))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                AnimatedContent(
                    targetState = uiState.screenState
                ) { state ->
                    when (state) {
                        ScreenState.IDLE -> Unit
                        ScreenState.LOADING -> {
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
                                    onAction(EmployeesSearchUIAction.Refresh)
                                },
                            ) {
                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }

                        ScreenState.SUCCESS -> {
                            val itemCount = employees.itemCount
                            //if no items
                            if (itemCount == 0) {
                                PullToRefreshColumn(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxHeight(),
                                    refreshing = uiState.isRefreshing,
                                    onRefresh = {
                                        onAction(
                                            EmployeesSearchUIAction.Refresh
                                        )
                                    },
                                ) {
                                    ErrorComponent(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        title = stringResource(R.string.no_matching_result),
                                        description = stringResource(R.string.no_appointment_subtitle)
                                    )
                                }
                            } else {
                                PullToRefreshBox(
                                    refreshing = uiState.isRefreshing,
                                    onRefresh = {
                                        onAction(
                                            EmployeesSearchUIAction.Refresh
                                        )
                                    },
                                ) {
                                    LazyColumn(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                    ) {
                                        items(itemCount) { index ->
                                            val employee = employees[index]
                                            employee?.let { employee ->
                                                SimpleProfileListItem(
                                                    imageUrl = employee.imageUrl,
                                                    name = employee.fullName,
                                                    onClick = {
                                                        employee.id?.let { id ->
                                                            navigationActions.navigateToEmployeeProfile(
                                                                employeeId = id
                                                            )
                                                        }
                                                    },
                                                    modifier = Modifier.fillMaxWidth(),
                                                )
                                            }
                                        }
                                        if (employees.loadState.append == LoadState.Loading) {
                                            item {
                                                SmallCircularProgressIndicator(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
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