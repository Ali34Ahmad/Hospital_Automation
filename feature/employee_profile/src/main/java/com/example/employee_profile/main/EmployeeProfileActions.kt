package com.example.employee_profile.main

import com.example.utility.ui.AppNavigationUiAction

class EmployeeProfileUiActions(
    navigationActions:EmployeeProfileNavigationUiActions,
    businessActions:EmployeeProfileBusinessUiActions,
) :EmployeeProfileBusinessUiActions by businessActions,
   EmployeeProfileNavigationUiActions by navigationActions


interface EmployeeProfileBusinessUiActions {
    fun onDeactivateAccount()
    fun onReactivateMyAccount()
    fun onLogout()
    fun onResign()
    fun hideErrorDialog()
    fun onRefresh()
    fun clearToastMessage()
    fun onUpdateDeactivationReason(value:String)
    fun onHideDeactivationReasonDialog()
    fun onShowDeactivationReasonDialog()
}

interface EmployeeProfileNavigationUiActions {
    fun navigateToAddedChildrenScreen(employeeId:Int?)
    fun navigateToEmploymentHistoryScreen(employeeId:Int?)
    fun navigateUp()
    fun navigateToLoginScreen()
}