package com.example.doctor_schedule.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.doctor_schedule.presentation.model.AppointmentUIModel
import com.example.ext.toAppropriateFormat
import com.example.model.Doctor
import com.example.model.LabeledBadgeData
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.enums.ScreenState
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.AppointmentCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.dialog.DatePickerDialog
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.tab.CustomTabsLayout
import com.example.ui_components.components.topbars.custom.DoctorScheduleTopBar
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import java.time.LocalDateTime

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
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            DoctorScheduleTopBar(
                isSearchBarVisible = uiState.isSearchBarVisible,
                searchQuery = uiState.searchQuery,
                onQueryChanged = {newValue->
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
                        DoctorScheduleUIAction.OpenDrawer
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.sizing.small16)
        ){
            AnimatedVisibility(
                uiState.isDatePickerVisible,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ){
                Surface {
                    DatePickerDialog(
                        onConfirm = {
                            onAction(
                                DoctorScheduleUIAction.UpdateDate(it)
                            )
                        },
                        datePickerState = rememberUseCaseState(
                            visible = uiState.isDatePickerVisible,
                            onCloseRequest = {
                                onAction(
                                    DoctorScheduleUIAction.HideDatePicker
                                )
                            },
                            onDismissRequest = {
                                {
                                    onAction(
                                        DoctorScheduleUIAction.HideDatePicker
                                    )
                                }
                            }
                        )
                    )
                }

            }
            when(uiState.screenState){
                ScreenState.IDLE -> Unit
                ScreenState.LOADING -> {
                    FetchingDataItem(Modifier.fillMaxSize())
                }
                ScreenState.ERROR ->{
                    PullToRefreshColumn(
                        refreshing = uiState.isRefreshing,
                        modifier = Modifier.fillMaxSize(),
                        onRefresh = {
                            onAction(DoctorScheduleUIAction.Refresh)
                        },
                        verticalArrangement = Arrangement.Center
                    ){
                        ErrorComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                ScreenState.SUCCESS -> {
                        val itemCount = appointments.itemCount
                        //if no items
                        PullToRefreshBox(
                            refreshing = uiState.isRefreshing,
                            onRefresh = {
                                onAction(
                                    DoctorScheduleUIAction.Refresh
                                )
                            },
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                            ) {
                               item {
                                   CustomTabsLayout(
                                       items = AppointmentState.entries.map { state ->
                                           val badge = uiState.statistics.getByState(state)
                                           LabeledBadgeData(
                                               label = state.toString(),
                                               badge = badge
                                           )
                                       },
                                       selectedItemIndex = AppointmentState.entries.indexOf(uiState.selectedTab),
                                       onItemSelected = {
                                           onAction(
                                               DoctorScheduleUIAction.UpdateTab(
                                                   AppointmentState.entries[it]
                                               )
                                           )
                                       },
                                       modifier = Modifier
                                           .fillMaxWidth()
                                           .padding(top = MaterialTheme.spacing.small8)
                                   )
                               }
                                if (itemCount == 0) {
                                    item {
                                        ErrorComponent(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(MaterialTheme.spacing.medium16),
                                            title = stringResource(R.string.no_matching_result),
                                            description = stringResource(R.string.no_appointment_subtitle)
                                        )
                                    }
                                } else {
                                    //if we have some items
                                    items(itemCount) { index ->
                                        val appointment = appointments[index]
                                        when (appointment) {
                                            is AppointmentUIModel.AppointmentModel -> {
                                                appointment.run {
                                                    AppointmentCard(
                                                        imageUrl = imageUrl,
                                                        name = fullName,
                                                        tag = appointmentType,
                                                        date = date.toAppropriateFormat(),
                                                        onClick = {
                                                            navigationActions.navigateToAppointmentDetails(
                                                                appointment.id
                                                            )
                                                        },
                                                        modifier = Modifier.fillMaxWidth(),
                                                    )
                                                }
                                            }

                                            is AppointmentUIModel.SeparatorModel -> {
                                                Text(
                                                    text = appointment.desc.asString(),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(
                                                            vertical = MaterialTheme.spacing.small8
                                                        ),
                                                    style = MaterialTheme.typography.titleMedium,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            }

                                            null -> Unit
                                        }

                                    }
                                    if (appointments.loadState.prepend == LoadState.Loading) {
                                        item {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(MaterialTheme.spacing.medium16),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator()
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