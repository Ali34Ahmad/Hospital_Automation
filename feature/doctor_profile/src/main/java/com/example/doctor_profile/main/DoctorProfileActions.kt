package com.example.doctor_profile.main

class DoctorProfileUiActions(
    navigationActions:DoctorProfileNavigationUiActions,
    businessActions:DoctorProfileBusinessUiActions,
) :DoctorProfileBusinessUiActions by businessActions,
   DoctorProfileNavigationUiActions by navigationActions


interface DoctorProfileBusinessUiActions {
    fun onRefreshProfile()
    fun onDeactivateMyAccount()
    fun onReactivateMyAccount()
    fun onLogout()
    fun hideErrorDialog()
    fun onRefresh()
    fun clearToastMessage()
}

interface DoctorProfileNavigationUiActions {
    fun navigateToAppointmentsScreen()
    fun navigateToEmploymentHistoryScreen()
    fun navigateUp()
    fun navigateToLoginScreen()
    fun navigateToPrescriptionsScreen()
    fun navigateToMedicalRecordsScreen()
    fun navigateToDepartmentDetailsScreen()
}