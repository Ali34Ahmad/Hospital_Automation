package com.example.doctor_profile.main

import com.example.utility.ui.AppNavigationUiAction

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

interface DoctorProfileNavigationUiActions:AppNavigationUiAction {
    fun navigateToAddedChildrenScreen()
    fun navigateToEmploymentHistoryScreen()
    fun navigateUp()
    fun navigateToLoginScreen()
}