package com.example.clinic_details.presentation

interface ClinicDetailsUIAction {
    object Refresh : ClinicDetailsUIAction
    object SendRequest : ClinicDetailsUIAction
    object HideDialog: ClinicDetailsUIAction
    data class ShowDialog(val title: String,val subtitle: String): ClinicDetailsUIAction
    object ClearToast: ClinicDetailsUIAction
}

interface ClinicNavigationAction{
    fun navigateUp()
    fun navigateToDoctorProfile()
    fun navigateToScheduleScreen()
    fun navigateToVaccines()
}