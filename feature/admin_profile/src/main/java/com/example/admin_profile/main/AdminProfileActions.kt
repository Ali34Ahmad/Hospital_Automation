package com.example.admin_profile.main

import com.example.utility.ui.AppNavigationUiAction

class AdminProfileUiActions(
    navigationActions:AdminProfileNavigationUiActions,
    businessActions:AdminProfileBusinessUiActions,
) :AdminProfileBusinessUiActions by businessActions,
   AdminProfileNavigationUiActions by navigationActions


interface AdminProfileBusinessUiActions {
    fun onRefreshProfile()
}

interface AdminProfileNavigationUiActions:AppNavigationUiAction {
    fun navigateUp()
}