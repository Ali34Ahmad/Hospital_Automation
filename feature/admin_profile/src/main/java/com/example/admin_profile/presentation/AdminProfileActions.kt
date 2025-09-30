package com.example.admin_profile.presentation

import com.example.utility.ui.AppNavigationUiAction

class AdminProfileUiActions(
    navigationActions:AdminProfileNavigationUiActions,
    businessActions:AdminProfileBusinessUiActions,
) :AdminProfileBusinessUiActions by businessActions,
   AdminProfileNavigationUiActions by navigationActions


interface AdminProfileBusinessUiActions {
    fun onGetAdminProfile()
    fun onRefresh()
    fun clearToastMessage()
}

interface AdminProfileNavigationUiActions:AppNavigationUiAction {
    fun navigateUp()
}
