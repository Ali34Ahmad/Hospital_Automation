package com.example.doctor_schedule.presentation.custom_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.doctor_schedule.presentation.DoctorScheduleUIAction
import com.example.doctor_schedule.presentation.DoctorScheduleUIState
import com.example.doctor_schedule.presentation.model.AppointmentUIModel
import com.example.ext.toAppropriateDateTimeFormat
import com.example.model.LabeledBadgeData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.AppointmentCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.dialog.DatePickerDialog
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.progress_indicator.SmallCircularProgressIndicator
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.tab.CustomTabsLayout
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import java.time.LocalDateTime

@Composable
fun SuccessScreen(
    uiState: DoctorScheduleUIState,
    onAction: (DoctorScheduleUIAction)-> Unit,
    appointments: LazyPagingItems<AppointmentUIModel>,
    onNavigateToAppointmentDetails: (id: Int)-> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
    ){
        val items = AppointmentState.entries.toList()-AppointmentState.PENDING
        CustomTabsLayout(
                items = items.map { state ->
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
        )
            if(uiState.isDatePickerVisible){
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
                FetchingDataItem(
                    Modifier
                        .fillMaxSize()
                )
            }
            ScreenState.ERROR ->{
                    PullToRefreshColumn(
                        refreshing = uiState.isRefreshing,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight(),
                        onRefresh = {
                            onAction(DoctorScheduleUIAction.Refresh)
                        },
                    ) {
                        ErrorComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            ScreenState.SUCCESS -> {
                val itemCount = appointments.itemCount
                //if no items
                if (itemCount == 0) {
                        PullToRefreshColumn(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxHeight(),
                            refreshing = uiState.isRefreshing,
                            onRefresh = {
                                onAction(
                                    DoctorScheduleUIAction.Refresh
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
                    }
                else {
                    PullToRefreshBox(
                        refreshing = uiState.isRefreshing,
                        onRefresh = {
                            onAction(
                                DoctorScheduleUIAction.Refresh
                            )
                        },
                    ){
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                        ){
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
                                                date = LocalDateTime.of(date,time).toAppropriateDateTimeFormat(),
                                                onClick = {
                                                    onNavigateToAppointmentDetails(
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
                                                .fillMaxWidth(),
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }

                                    null -> Unit
                                }
                            }
                            if (appointments.loadState.append == LoadState.Loading) {
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