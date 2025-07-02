package com.example.appointment_details.presentation

import com.example.util.UiText

sealed interface AppointmentDetailsAction {
    object MarkAsMissed : AppointmentDetailsAction
    object MarkAsPassed : AppointmentDetailsAction
    object Refresh: AppointmentDetailsAction
    data class ShowToast(val message: UiText): AppointmentDetailsAction
    object ClearToastMessage: AppointmentDetailsAction
}
interface AppointmentNavigationActions{
    fun navigateUp()
    fun navigateToDepartmentDetails(deptId: Int)
    fun navigateToVaccineDetails(vaccineId: Int)
    fun navigateToAddMedicalDiagnosis(appointmentId: Int)
}

internal val mockNavigationActions = object :AppointmentNavigationActions{
    override fun navigateUp() {
    }

    override fun navigateToDepartmentDetails(deptId: Int) {
    }

    override fun navigateToVaccineDetails(vaccineId: Int) {
    }

    override fun navigateToAddMedicalDiagnosis(appointmentId: Int) {
    }
}