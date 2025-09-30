package com.example.home.presentation

class EmployeeEmployeeEmployeeHomeUiActions(
    navigationActions:EmployeeHomeNavigationUiActions,
    businessActions:EmployeeHomeBusinessUiActions,
) :EmployeeHomeBusinessUiActions by businessActions,
   EmployeeHomeNavigationUiActions by navigationActions


interface EmployeeHomeBusinessUiActions {
    fun onStartButtonClick()
    fun onChangeTheme()
    fun onRefresh()
    fun clearToastMessage()
}

interface EmployeeHomeNavigationUiActions {
    fun navigateToAddChildScreen()
    fun navigateToAddGuardianScreen()
    fun navigateToEmployeeProfileScreen()
    fun navigateToRequestsScreen()
    fun navigateToAddedChildrenScreen()
}