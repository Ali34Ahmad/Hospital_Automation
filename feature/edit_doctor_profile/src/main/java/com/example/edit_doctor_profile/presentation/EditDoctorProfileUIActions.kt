package com.example.edit_doctor_profile.presentation

import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.model.doctor.workday.WorkDaySummaryData
import java.time.DayOfWeek
import java.time.LocalTime

interface EditDoctorProfileUIActions {
    //Refresh
    data object Refresh: EditDoctorProfileUIActions

    //Dialogs
    data class UpdateDialog(val dialog: ProfileDialog?) : EditDoctorProfileUIActions

    //Choose day of week dialog
    data class UpdateSelectedDayOfWeek(val dayOfWeek: DayOfWeek) : EditDoctorProfileUIActions

    //Edit times dialog
    data class UpdateSelectedStartTime(val time: LocalTime?) : EditDoctorProfileUIActions
    data class UpdateSelectedEndTime(val time: LocalTime?) : EditDoctorProfileUIActions

    //Workday : UI
    data class UpdateCurrentWorkDay(val workDay: WorkDaySummaryData?) : EditDoctorProfileUIActions

    //Workday : network operations
    data object AddNewWorkDay: EditDoctorProfileUIActions
    data object UpdateWorkDay: EditDoctorProfileUIActions
    data object DeleteWorkDay: EditDoctorProfileUIActions

    //Appointment type : UI
    data class UpdateSelectedAppointmentType(
        val appointmentType: AppointmentTypeSummaryData?
    ) : EditDoctorProfileUIActions

    //Appointment type: network operations
    data object AddNewAppointmentType: EditDoctorProfileUIActions
    data object UpdateAppointmentType: EditDoctorProfileUIActions
    data object DeleteAppointmentType: EditDoctorProfileUIActions

    //Appointment type dialog
    data class ChangeAppointmentName(val newName: String): EditDoctorProfileUIActions
    data class ChangeAppointmentDuration(val newDuration: Int): EditDoctorProfileUIActions
    data class ChangeAppointmentDescription(val newDescription: String): EditDoctorProfileUIActions
    object ValidateFields: EditDoctorProfileUIActions
    data class UpdateAppointmentTypeDialog(val isEditing: Boolean): EditDoctorProfileUIActions


    //Toast
    data object ClearToastMessage: EditDoctorProfileUIActions
}

interface EditDoctorProfileNavigationActions{
    fun navigateUp()
}