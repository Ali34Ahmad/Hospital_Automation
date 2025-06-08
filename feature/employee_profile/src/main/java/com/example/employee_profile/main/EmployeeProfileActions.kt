package com.example.employee_profile.main

import com.example.utility.ui.AppNavigationUiAction

class EmployeeProfileUiActions(
    navigationActions:EmployeeProfileNavigationUiActions,
    businessActions:EmployeeProfileBusinessUiActions,
) :EmployeeProfileBusinessUiActions by businessActions,
   EmployeeProfileNavigationUiActions by navigationActions


interface EmployeeProfileBusinessUiActions {
    fun onRefreshProfile()
    fun onDeactivateMyAccount()
    fun onReactivateMyAccount()
    fun onLogout()
    fun hideErrorDialog()
    fun onRefresh()
    fun clearToastMessage()
}

interface EmployeeProfileNavigationUiActions:AppNavigationUiAction {
    fun navigateToAddedChildrenScreen()
    fun navigateToEmploymentHistoryScreen()
    fun navigateUp()
    fun navigateToLoginScreen()
}