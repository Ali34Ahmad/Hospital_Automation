package com.example.appointment_details.presentation

import com.example.util.UiText

sealed interface AppointmentDetailsAction {
    object MarkAsMissed : AppointmentDetailsAction
    object MarkAsPassed : AppointmentDetailsAction
    object Refresh: AppointmentDetailsAction
    data class ShowToast(val message: UiText): AppointmentDetailsAction
    object ClearToastMessage: AppointmentDetailsAction
    data class OpenDialog(val title: String, val subtitle: String): AppointmentDetailsAction
    object CloseDialog: AppointmentDetailsAction

    object UpdateIsFirstLaunchToFalse : AppointmentDetailsAction
}
interface AppointmentDetailsNavigationActions{
    fun navigateUp()
    fun navigateToDepartmentDetails(deptId: Int)
    fun navigateToVaccineDetails(vaccineId: Int)
    fun navigateToDoctorSchedule()
    fun navigateToAddMedicalDiagnosis(
        appointmentId: Int,
        fullName: String,
        patientId: Int?,
        childId: Int?,
        canSkip: Boolean,
    )
    fun navigateToGuardianProfile(guardianId: Int)
}
