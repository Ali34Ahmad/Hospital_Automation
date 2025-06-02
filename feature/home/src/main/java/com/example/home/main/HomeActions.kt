package com.example.home.main

class HomeUiActions(
    navigationActions:HomeNavigationUiActions,
    businessActions:HomeBusinessUiActions,
) :HomeBusinessUiActions by businessActions,
   HomeNavigationUiActions by navigationActions


interface HomeBusinessUiActions {
    fun onStartButtonClick()
    fun onUpdateSelectedDrawerIndex(index: Int)
    fun onChangeTheme()
}

interface HomeNavigationUiActions {
    fun navigateToAddChildScreen()
    fun navigateToAddGuardianScreen()
    fun navigateToEmployeeProfileScreen()
    fun navigateToRequestsScreen()
    fun navigateToAddedChildrenScreen()
}