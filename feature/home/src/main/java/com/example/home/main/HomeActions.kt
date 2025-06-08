package com.example.home.main

class HomeUiActions(
    navigationActions:HomeNavigationUiActions,
    businessActions:HomeBusinessUiActions,
) :HomeBusinessUiActions by businessActions,
   HomeNavigationUiActions by navigationActions


interface HomeBusinessUiActions {
    fun onStartButtonClick()
    fun onChangeTheme()
    fun onRefresh()
    fun clearToastMessage()
}

interface HomeNavigationUiActions {
    fun navigateToAddChildScreen()
    fun navigateToAddGuardianScreen()
    fun navigateToEmployeeProfileScreen()
    fun navigateToRequestsScreen()
    fun navigateToAddedChildrenScreen()
}