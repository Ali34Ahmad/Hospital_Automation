package com.example.doctor_profile.main

class DoctorProfileUiActions(
    navigationActions:DoctorProfileNavigationUiActions,
    businessActions:DoctorProfileBusinessUiActions,
) :DoctorProfileBusinessUiActions by businessActions,
   DoctorProfileNavigationUiActions by navigationActions


interface DoctorProfileBusinessUiActions {
    fun onDeactivateAccount()
    fun onReactivateAccount()
    fun onLogout()
    fun onResignDoctor()
    fun onHideErrorDialog()
    fun onRefresh()
    fun onClearToastMessage()
    fun onUpdateSelectedAppointmentTypeDialog(index:Int?)
}

interface DoctorProfileNavigationUiActions {
    fun navigateToAppointmentsScreen()
    fun navigateToEmploymentHistoryScreen()
    fun navigateUp()
    fun navigateToLoginScreen()
    fun navigateToPrescriptionsScreen()
    fun navigateToMedicalRecordsScreen()
    fun navigateToDepartmentDetailsScreen(clinicId: Int)
}