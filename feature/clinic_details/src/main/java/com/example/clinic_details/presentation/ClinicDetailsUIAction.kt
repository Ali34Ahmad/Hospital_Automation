package com.example.clinic_details.presentation

interface ClinicDetailsUIAction {
    object Refresh : ClinicDetailsUIAction
    object SendRequest : ClinicDetailsUIAction
    object ClearToast: ClinicDetailsUIAction
    object DeactivateClinic: ClinicDetailsUIAction
    object ReactivateClinic: ClinicDetailsUIAction
    //Dialogs
    //info dialog
    object HideInfoDialog: ClinicDetailsUIAction
    data class ShowInfoDialog(val title: String, val subtitle: String): ClinicDetailsUIAction
    //warning dialog
    object ShowWarningDialog: ClinicDetailsUIAction
    object HideWarningDialog: ClinicDetailsUIAction
    data class UpdateDeactivationReason(val  newValue: String) : ClinicDetailsUIAction
    //loading dialog
    object ShowLoadingDialog
    object HideLoadingDialog
}

interface ClinicNavigationAction{
    fun navigateUp()
    fun navigateToDoctorProfile()
    fun navigateToScheduleScreen()
    fun navigateToVaccines()
    fun navigateToAllDoctors()
    fun navigateToAllAppointments()
    fun navigateToPrescriptions()
    fun navigateToMedicalRecords()
    fun navigateToContractHistory()
    fun navigateToEditClinic()
}