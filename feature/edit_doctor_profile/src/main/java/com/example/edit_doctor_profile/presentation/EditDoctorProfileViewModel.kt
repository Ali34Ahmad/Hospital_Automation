package com.example.edit_doctor_profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.appointment_type.CreateAppointmentTypeUseCase
import com.example.domain.use_cases.appointment_type.DeleteAppointmentTypeUseCase
import com.example.domain.use_cases.appointment_type.UpdateAppointmentTypeUseCase
import com.example.domain.use_cases.doctor.profile.GetCurrentDoctorProfileUseCase
import com.example.domain.use_cases.work_day.CreateWorkDayUseCase
import com.example.domain.use_cases.work_day.DeleteWorkDayUseCase
import com.example.domain.use_cases.work_day.UpdateWorkDayUseCase
import com.example.edit_doctor_profile.presentation.preview.mockAppointmentType
import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.model.doctor.doctor_profile.DoctorProfileSummary
import com.example.model.doctor.workday.WorkDaySummaryData
import com.example.model.enums.ScreenState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import com.example.utility.validation.validator.PositiveNumberValidator
import com.example.utility.validation.validator.TextValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.map

class EditDoctorProfileViewModel(
    private val getCurrentDoctorProfileUseCase: GetCurrentDoctorProfileUseCase,
    private val createWorkDayUseCase: CreateWorkDayUseCase,
    private val deleteWorkDayUseCase: DeleteWorkDayUseCase,
    private val updateWorkDayUseCase: UpdateWorkDayUseCase,
    private val createAppointmentTypeUse: CreateAppointmentTypeUseCase,
    private val updateAppointmentTypeUseCase: UpdateAppointmentTypeUseCase,
    private val deleteAppointmentTypeUseCase: DeleteAppointmentTypeUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(
        EditDoctorProfileUIState()
    )
    val uiState: StateFlow<EditDoctorProfileUIState> = _uiState.onStart {
        loadInitialData()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        _uiState.value
    )

    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    private fun updateRefreshState(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private suspend fun loadInitialData(){
        updateScreenState(ScreenState.LOADING)
        getCurrentDoctorProfileUseCase()
            .onError {
                updateScreenState(ScreenState.ERROR)
            }
            .onSuccess { response->
                updateScreenState(ScreenState.SUCCESS)
                updateCurrentProfileData(response)
            }
    }
    private fun updateCurrentProfileData(response: DoctorProfileResponse){
        val profileData = DoctorProfileSummary(
            fullName = response.profile.fullName,
            availabilitySchedule = response.profile.availabilitySchedule
                .map {
                    WorkDaySummaryData(
                        id = it.id,
                        day = it.dayOfWeek,
                        startTime = it.startTime,
                        endTime = it.endTime
                    )
                },
            imageUrl = response.profile.imageUrl,
            appointmentTypes = response.profile.appointmentTypes.map {
                AppointmentTypeSummaryData(
                    id = it.id,
                    name = it.name,
                    duration = it.standardDurationInMinutes,
                    description = it.description
                )
            }
        )
        _uiState.value = _uiState.value.copy(profileData = profileData)
        _uiState.value = _uiState.value.copy(workDays = profileData.availabilitySchedule.distinct())
        _uiState.value = _uiState.value.copy(appointmentTypes = profileData.appointmentTypes.distinct())
    }
    private fun refreshData()= viewModelScope.launch{
            updateRefreshState(true)
            getCurrentDoctorProfileUseCase()
                .onSuccess{ response->
                    updateRefreshState(false)
                    updateCurrentProfileData(response)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    if(uiState.value.screenState == ScreenState.ERROR){
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError {
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    private fun showToast(message: UiText?) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateCurrentDialog(dialog: ProfileDialog?){
        _uiState.value = _uiState.value.copy(currentDialog = dialog)
    }
    private fun addNewWorkDay()=viewModelScope.launch{
        uiState.value.currentWorkDay?.let { workday->
            updateCurrentDialog(ProfileDialog.LOADING)
            createWorkDayUseCase(workday)
                .onError {
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }.onSuccess {
                    val newList= uiState.value.workDays + workday
                    _uiState.value = _uiState.value.copy(workDays = newList.sortedBy { it.day })
                    //if added successfully then refresh the data to show new ones
                    refreshData()
                }
            updateCurrentDialog(null)
        }
    }
    private fun deleteWorkDay()=viewModelScope.launch{
        uiState.value.currentWorkDay?.let { workDay->
            updateCurrentDialog(ProfileDialog.LOADING)
            deleteWorkDayUseCase(workDay.id)
                .onError {
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }.onSuccess {
                    val newList = uiState.value.workDays - workDay
                    _uiState.value = _uiState.value.copy(workDays = newList.sortedBy { it.day })
                }
            updateCurrentDialog(null)
        }
    }
    private fun updateWorkDay()=viewModelScope.launch {
        uiState.value.currentWorkDay?.let { updatedWorkday->
            updateCurrentDialog(ProfileDialog.LOADING)
            updateWorkDayUseCase(updatedWorkday,updatedWorkday.id)
                .onError {
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }.onSuccess {
                    val newList = uiState.value.workDays.map { workDay->
                        if(workDay.id == updatedWorkday.id)
                            updatedWorkday
                        else
                            workDay
                    }
                    _uiState.value = _uiState.value.copy(workDays = newList)
                }
            updateCurrentDialog(null)
        }
    }
    private fun addNewAppointmentType() = viewModelScope.launch{
        uiState.value.selectedAppointmentType?.let {appointmentType->
            updateCurrentDialog(ProfileDialog.LOADING)
            createAppointmentTypeUse(appointmentType)
                .onError {
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }.onSuccess {
                    val newList= uiState.value.appointmentTypes + appointmentType
                    _uiState.value = _uiState.value.copy(appointmentTypes = newList)
                    //if added successfully then refresh the data to show new ones
                    refreshData()
                }
            updateCurrentDialog(null)
        }
    }
    private fun deleteAppointmentType() = viewModelScope.launch {
        uiState.value.selectedAppointmentType?.let { appointmentType->
            updateCurrentDialog(ProfileDialog.LOADING)
            deleteAppointmentTypeUseCase(appointmentType.id)
                .onError {
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }.onSuccess {
                    val newList = uiState.value.appointmentTypes - appointmentType
                    _uiState.value = _uiState.value.copy(appointmentTypes = newList)
                }
            updateCurrentDialog(null)
        }
    }
    private fun updateAppointmentType() = viewModelScope.launch {
        uiState.value.selectedAppointmentType?.let {updatedAppointmentType->
            updateCurrentDialog(ProfileDialog.LOADING)
            updateAppointmentTypeUseCase(updatedAppointmentType, mockAppointmentType.id)
                .onError {
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }.onSuccess {
                    val newList = uiState.value.appointmentTypes.map { appointmentType->
                        if(appointmentType.id == updatedAppointmentType.id)
                            updatedAppointmentType
                        else
                            appointmentType
                    }
                    _uiState.value = _uiState.value.copy(appointmentTypes = newList)
                }
            updateCurrentDialog(null)
        }
    }
    private fun validateName(){
        _uiState.value.selectedAppointmentType?.let { appointmentType->
            val name = appointmentType.name
            val error = TextValidator.validate(name)
            val errorText = error?.let { UiText.StringResource(resId = R.string.required_field) }
            _uiState.value = _uiState.value.copy(
                nameErrorText = errorText,
                isNameValid = errorText == null
            )
        }
    }
    private fun validateNumber(){
        _uiState.value.selectedAppointmentType?.let { appointmentType->
            val duration = appointmentType.duration
            val error = PositiveNumberValidator.validate("$duration")
            val errorText = error?.let { UiText.StringResource(resId = R.string.invalid_number) }
            _uiState.value = _uiState.value.copy(
                durationErrorText = errorText,
                isDurationValid = errorText == null
            )
        }
    }
    
    fun onAction(action: EditDoctorProfileUIActions){
        when(action){
            is EditDoctorProfileUIActions.UpdateDialog -> {
                updateCurrentDialog(action.dialog)
            }
            is EditDoctorProfileUIActions.UpdateSelectedDayOfWeek->{
                _uiState.value = _uiState.value.copy(
                    selectedDayOfWeek = action.dayOfWeek
                )
            }
            is EditDoctorProfileUIActions.UpdateSelectedStartTime->{
                _uiState.value = _uiState.value.copy(
                    selectedStartTime = action.time
                )
            }
            is EditDoctorProfileUIActions.UpdateAppointmentTypeDialog->{
                _uiState.value = _uiState.value.copy(isEditing = action.isEditing)
            }
            is EditDoctorProfileUIActions.UpdateSelectedEndTime->{
                _uiState.value = _uiState.value.copy(
                    selectedEndTime = action.time
                )
            }
            is EditDoctorProfileUIActions.UpdateCurrentWorkDay-> {
                _uiState.value = _uiState.value.copy(
                    currentWorkDay = action.workDay
                )
            }
            is EditDoctorProfileUIActions.AddNewWorkDay->{
                addNewWorkDay()
            }
            EditDoctorProfileUIActions.UpdateWorkDay->{
                updateWorkDay()
            }
            EditDoctorProfileUIActions.DeleteWorkDay->{
                deleteWorkDay()
            }
            EditDoctorProfileUIActions.Refresh -> {
                refreshData()
            }
            EditDoctorProfileUIActions.ClearToastMessage -> {
                _uiState.value = _uiState.value.copy(toastMessage = null)
            }
            EditDoctorProfileUIActions.AddNewAppointmentType -> {
                addNewAppointmentType()
            }
            EditDoctorProfileUIActions.UpdateAppointmentType->{
                 updateAppointmentType()
            }
            EditDoctorProfileUIActions.DeleteAppointmentType -> {
                deleteAppointmentType()
            }
            is EditDoctorProfileUIActions.UpdateSelectedAppointmentType->{
                _uiState.value = _uiState.value.copy(selectedAppointmentType = action.appointmentType)
            }
            is EditDoctorProfileUIActions.ChangeAppointmentName->{
                uiState.value.selectedAppointmentType?.let { appointmentType ->
                    val updatedData = appointmentType.copy(name = action.newName)
                    _uiState.value = _uiState.value.copy(selectedAppointmentType = updatedData)
                }
                validateName()
                Log.d("EditProfileViewModel","${uiState.value.isAppointmentTypeSaveButtonEnabled}")
            }
            is EditDoctorProfileUIActions.ChangeAppointmentDescription->{
                uiState.value.selectedAppointmentType?.let { appointmentType ->
                    val updatedData = appointmentType.copy(description = action.newDescription)
                    _uiState.value = _uiState.value.copy(selectedAppointmentType = updatedData)
                }
            }
            is EditDoctorProfileUIActions.ChangeAppointmentDuration->{
                uiState.value.selectedAppointmentType?.let { appointmentType ->
                    val updatedData = appointmentType.copy(duration = action.newDuration)
                    _uiState.value = _uiState.value.copy(selectedAppointmentType = updatedData)
                }
                validateNumber()
                Log.d("EditProfileViewModel","${uiState.value.isAppointmentTypeSaveButtonEnabled}")
            }

            EditDoctorProfileUIActions.ValidateFields->{
                validateName()
                validateNumber()
            }
        }
    }
}