package com.example.edit_doctor_profile.presentation

import android.util.Log
import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.model.doctor.doctor_profile.DoctorProfileSummary
import com.example.model.doctor.workday.WorkDaySummaryData
import com.example.model.enums.ScreenState
import com.example.util.UiText
import java.time.DayOfWeek
import java.time.LocalTime

data class EditDoctorProfileUIState(
    val profileData: DoctorProfileSummary? = null,
    val screenState: ScreenState = ScreenState.IDLE,
    val currentDialog: ProfileDialog? = null,

    //Workdays section
    val workDays: List<WorkDaySummaryData> = emptyList(),
    val currentWorkDay: WorkDaySummaryData? = null,
    val selectedDayOfWeek: DayOfWeek? = null,
    val selectedStartTime: LocalTime? = null,
    val selectedEndTime: LocalTime? = null,

    //Appointment types section
    val appointmentTypes: List<AppointmentTypeSummaryData> = emptyList(),
    val selectedAppointmentType: AppointmentTypeSummaryData? = null,
    val isNameValid: Boolean? = null,
    val isDurationValid: Boolean? = null,
    val nameErrorText: UiText? = null,
    val durationErrorText: UiText? = null,

    val isRefreshing: Boolean = false,
    val isEditing: Boolean = false,
    val toastMessage: UiText? = null,
){
    val isAppointmentTypeSaveButtonEnabled : Boolean
        get() {
            Log.d("EditProfileUIState","duration: $isDurationValid, appType: $isNameValid")
            return isDurationValid == true && isNameValid == true
        }
}

enum class ProfileDialog{
    LOADING,
    CONFIRM,
    EDIT_WORKDAY,
    EDIT_TIME,
    CHOOSE_DAY,
    APPOINTMENT_TYPE
}