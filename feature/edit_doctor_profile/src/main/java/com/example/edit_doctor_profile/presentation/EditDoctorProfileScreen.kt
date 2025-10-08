package com.example.edit_doctor_profile.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.constants.icons.AppIcons
import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.model.doctor.workday.WorkDaySummaryData
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.dialog.AppointmentTypeDialog
import com.example.ui_components.components.dialog.ChooseDayDialog
import com.example.ui_components.components.dialog.ConfirmationDialog
import com.example.ui_components.components.dialog.EditTimeRangeDialog
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.TimePickerDialog
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.list.AppointmentTypesList
import com.example.ui_components.components.list.WorkDaysList
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import java.time.LocalTime


@Composable
fun EditDoctorProfileScreen(
    viewModel: EditDoctorProfileViewModel,
    navigationActions: EditDoctorProfileNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EditDoctorProfileScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditDoctorProfileScreen(
    uiState: EditDoctorProfileUIState,
    onAction: (EditDoctorProfileUIActions)-> Unit,
    navigationActions: EditDoctorProfileNavigationActions,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let {
            Toast.makeText(
                context,
                it.asString(context),
                Toast.LENGTH_SHORT
            ).show()
            onAction(EditDoctorProfileUIActions.ClearToastMessage)
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            uiState.profileData?.let {data->
                HospitalAutomationTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = data.fullName.toString(),
                    onNavigationIconClick = navigationActions::navigateUp,
                    imageUrl = data.imageUrl,
                    navigationIcon = AppIcons.Outlined.arrowBack
                )
            }
        }
    ) { innerPadding->
        AnimatedContent(
            uiState.screenState,
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
        ) {state->
            when(state){
                ScreenState.IDLE ->{
                    FetchingDataItem(
                        Modifier.fillMaxSize()
                    )
                }
                ScreenState.LOADING -> {
                    FetchingDataItem(
                        Modifier.fillMaxSize()
                    )
                }
                ScreenState.ERROR -> {
                    PullToRefreshColumn(
                        refreshing = uiState.isRefreshing,
                        modifier = Modifier.fillMaxSize(),
                        onRefresh = {
                            onAction(EditDoctorProfileUIActions.Refresh)
                        },
                        verticalArrangement = Arrangement.Center
                    ){
                        ErrorComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                ScreenState.SUCCESS ->{
                    PullToRefreshBox(
                        refreshing = uiState.isRefreshing,
                        onRefresh = {
                            onAction(EditDoctorProfileUIActions.Refresh)
                        },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            WorkDaysList(
                                workDays = uiState.workDays,
                                modifier = modifier.fillMaxWidth(),
                                onItemDeleteClick = { workdayToRemove ->
                                    onAction(
                                        EditDoctorProfileUIActions.UpdateCurrentWorkDay(
                                            workdayToRemove
                                        )
                                    )
                                    onAction(
                                        EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.CONFIRM)
                                    )
                                },
                                onItemEditClick = {
                                    onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(it))
                                    onAction(
                                        EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.EDIT_WORKDAY)
                                    )
                                },
                                onAddButtonClick = {
                                    onAction(
                                        EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.CHOOSE_DAY)
                                    )
                                },
                            )
                            Spacer(Modifier.height(MaterialTheme.spacing.large24))
                            AppointmentTypesList(
                                appointmentTypes = uiState.appointmentTypes,
                                onEdit = {
                                    onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(
                                        it
                                    ))
                                    onAction(EditDoctorProfileUIActions.UpdateAppointmentTypeDialog(isEditing = true))
                                    onAction(EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.APPOINTMENT_TYPE))
                                    //When Editing we need initial validation
                                    onAction(EditDoctorProfileUIActions.ValidateFields)
                                },
                                onDelete = {
                                    onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(
                                        it
                                    ))
                                    onAction(EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.CONFIRM))
                                },
                                onAddButtonClick = {
                                    onAction(EditDoctorProfileUIActions.UpdateAppointmentTypeDialog(isEditing = false))
                                    onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(null))
                                    onAction(EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.APPOINTMENT_TYPE))
                                },
                            )
                        }

                        }
                }
            }
        }

        //Dialogs section
        uiState.currentDialog?.let { dialog->
            when(dialog){
                ProfileDialog.LOADING ->{
                    LoadingDialog(
                        showDialog = true,
                        title = stringResource(R.string.please_wait),
                        subtitle = stringResource(R.string.saving_changes)
                    )
                }
                ProfileDialog.CONFIRM -> {
                    val isDeletingWorkDay = uiState.currentWorkDay != null
                    val description = if(isDeletingWorkDay) R.string.confirm_workday_deletion
                    else R.string.confirm_appointment_type_deletion

                    ConfirmationDialog(
                        showDialog = true,
                        title = stringResource(R.string.confirm_deletion),
                        description = stringResource(description),
                        onDismiss = {
                            //close the dialog
                            onAction(EditDoctorProfileUIActions.UpdateDialog(null))
                            if(isDeletingWorkDay){
                                //clear the selected
                                onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(null))
                            }else{
                                onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(null))
                            }
                        },
                        onConfirm = {
                            if(isDeletingWorkDay){
                                //Delete the selected workday
                                onAction(EditDoctorProfileUIActions.DeleteWorkDay)
                                //clear the selected workday
                                onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(null))
                            }else{
                                //Delete the selected Appointment type
                                onAction(EditDoctorProfileUIActions.DeleteAppointmentType)
                                //Clear the selected Appointment type
                                onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(null))
                            }

                        },
                    )
                }
                ProfileDialog.EDIT_WORKDAY -> {
                    uiState.currentWorkDay?.let {
                        EditTimeRangeDialog(
                            startTime = it.startTime,
                            endTime = it.endTime,
                            onStartTimeOptionClick = {startTime->
                                onAction(EditDoctorProfileUIActions.UpdateSelectedStartTime(startTime))
                                onAction(EditDoctorProfileUIActions.UpdateSelectedEndTime(null))
                                onAction(EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.EDIT_TIME))
                            },
                            onEndTimeOptionClick = {endTime->
                                onAction(EditDoctorProfileUIActions.UpdateSelectedStartTime(null))
                                onAction(EditDoctorProfileUIActions.UpdateSelectedEndTime(endTime))
                                onAction(EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.EDIT_TIME))
                            },
                            onDismissRequest = {
                                onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(null))
                                onAction(EditDoctorProfileUIActions.UpdateDialog(null))
                            },
                            onConfirm = {
                                onAction(EditDoctorProfileUIActions.UpdateWorkDay)
                            },
                            title = it.day.toString(),
                        )
                    }
                }
                ProfileDialog.EDIT_TIME -> {
                    val initialTime = uiState.selectedStartTime?:
                    uiState.selectedEndTime?:
                    LocalTime.of(12,0)

                    val isStartTime = uiState.selectedStartTime!=null

                    val title = if(isStartTime) R.string.select_start_time
                    else R.string.select_end_time

                    TimePickerDialog(
                        onConfirm = {newTime->
                            uiState.currentWorkDay?.let { workDay ->
                                val updatedWorkDay = when {
                                    uiState.selectedStartTime != null -> workDay.copy(startTime = newTime)
                                    uiState.selectedEndTime != null   -> workDay.copy(endTime = newTime)
                                    else -> null
                                }

                                updatedWorkDay?.let {
                                    Log.d("EditProfileScreen", "updatedWorkDay : $updatedWorkDay")
                                    onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(it))
                                    onAction(EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.EDIT_WORKDAY))
                                }
                            }
                        },
                        onDismiss = {
                            onAction(EditDoctorProfileUIActions.UpdateDialog(ProfileDialog.EDIT_WORKDAY))
                        },
                        timePickerState = rememberTimePickerState(
                            initialHour = initialTime.hour,
                            initialMinute = initialTime.minute
                        ),
                        title = title,
                    )
                }
                ProfileDialog.CHOOSE_DAY -> {
                    ChooseDayDialog(
                        onDismissDialog = {
                            //clear cached data and hide the dialog
                            onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(null))
                            onAction(EditDoctorProfileUIActions.UpdateDialog(null))
                        },
                        disabledDays = uiState.workDays.map { it.day },
                        onDaySelected = { day ->
                            //create new workday with default times
                            val newWorkDay = WorkDaySummaryData(
                                id = 0,
                                day = day,
                                startTime = LocalTime.of(8, 0),
                                endTime = LocalTime.of(16, 0)
                            )
                            //save the new workday and preparing it for adding to the database
                            onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(newWorkDay))
                        },
                        onConfirm = {
                            uiState.currentWorkDay?.let { newWorkDay ->
                                //adding new day to the database
                                onAction(EditDoctorProfileUIActions.AddNewWorkDay)
                            }
                            //clear cached data and hide the dialog
                            onAction(EditDoctorProfileUIActions.UpdateCurrentWorkDay(null))
                            onAction(EditDoctorProfileUIActions.UpdateDialog(null))
                        },
                        selectedDayOfWeek = uiState.currentWorkDay?.day,
                    )
                }
                ProfileDialog.APPOINTMENT_TYPE -> {
                    //Editing or New
                    val defaultAppointmentType = AppointmentTypeSummaryData(
                        id = 0,
                        name = "",
                        duration = 0,
                        description = "",
                    )
                    //Get the current appointment type
                    val currentAppointmentType = uiState.selectedAppointmentType?:
                    defaultAppointmentType

                    //Update the data placeholder
                    onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(currentAppointmentType))

                    val title = if(uiState.isEditing) stringResource(R.string.edit_appointment_type)
                    else stringResource(R.string.add_appointment_type)

                    AppointmentTypeDialog(
                        isSaveEnabled = uiState.isAppointmentTypeSaveButtonEnabled,
                        title = title,
                        onDismissRequest = {
                            onAction(EditDoctorProfileUIActions.UpdateDialog(null))
                            onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(null))
                        },
                        appointmentTypeValue = currentAppointmentType.name,
                        onAppointmentTypeValueChanged = {
                            onAction(EditDoctorProfileUIActions.ChangeAppointmentName(it))
                        },
                        isAppointmentTypeError = uiState.isNameValid == false,
                        durationValue = currentAppointmentType.duration.toString(),
                        onDurationChanged = {
                            val duration = it.toIntOrNull()
                            duration?.let {
                                onAction(EditDoctorProfileUIActions.ChangeAppointmentDuration(duration))
                            }
                        },
                        isDurationError = uiState.isDurationValid == false,
                        descriptionValue = currentAppointmentType.description,
                        onDescriptionChanged = {
                            onAction(EditDoctorProfileUIActions.ChangeAppointmentDescription(it))
                        },
                        onSaveClick = {
                            Log.d("EditDoctorViewModel","isEditing = $uiState")
                            if(uiState.isEditing){
                                onAction(EditDoctorProfileUIActions.UpdateAppointmentType)
                            }
                            else {onAction(EditDoctorProfileUIActions.AddNewAppointmentType)}
                            onAction(EditDoctorProfileUIActions.UpdateSelectedAppointmentType(null))
                        },
                    )
                }
            }
        }
    }
}