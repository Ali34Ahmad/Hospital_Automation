package com.example.employment_history.main

class EmploymentHistoryUiActions(
    navigationActions:EmploymentHistoryNavigationUiActions,
    businessActions:EmploymentHistoryBusinessUiActions,
) :EmploymentHistoryBusinessUiActions by businessActions,
   EmploymentHistoryNavigationUiActions by navigationActions


interface EmploymentHistoryBusinessUiActions {
    fun onRefreshProfile()
    fun hideErrorDialog()
}

interface EmploymentHistoryNavigationUiActions {
    fun navigateToAcceptedByAdminProfileScreen()
    fun navigateToToResignedByAdminProfileScreen()
    fun navigateToToSuspendedByAdminProfileScreen()
    fun navigateUp()
}