package com.example.employee_profile

class EmployeeProfileUiActions(
    navigationActions:EmployeeProfileNavigationUiActions,
    businessActions:EmployeeProfileBusinessUiActions,
) :EmployeeProfileBusinessUiActions by businessActions,
   EmployeeProfileNavigationUiActions by navigationActions


interface EmployeeProfileBusinessUiActions {
    fun onRefreshProfile()
    fun onDeactivateMyAccount()
    fun onLogout()
    fun hideErrorDialog()
}

interface EmployeeProfileNavigationUiActions {
    fun navigateToCallApp(phoneNumber: String)
    fun navigateToEmail(email: String,subject: String)
    fun navigateToAddedChildrenScreen()
    fun navigateToEmploymentHistoryScreen()
    fun navigateUp()
    fun navigateToLoginScreen()
}