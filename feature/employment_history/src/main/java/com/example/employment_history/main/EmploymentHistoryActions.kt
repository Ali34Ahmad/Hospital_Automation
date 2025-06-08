package com.example.employment_history.main

class EmploymentHistoryUiActions(
    navigationActions:EmploymentHistoryNavigationUiActions,
    businessActions:EmploymentHistoryBusinessUiActions,
) :EmploymentHistoryBusinessUiActions by businessActions,
   EmploymentHistoryNavigationUiActions by navigationActions


interface EmploymentHistoryBusinessUiActions {
    fun onReloadEmploymentHistory()
    fun onHideFileDownloaderDialog()
    fun onDownloadFile()
    fun onCancelFileDownloading()
    fun onShowFileDownloaderDialog()
    fun onRefresh()
    fun clearToastMessage()
}

interface EmploymentHistoryNavigationUiActions {
    fun navigateToAcceptedByAdminProfileScreen()
    fun navigateToToResignedByAdminProfileScreen()
    fun navigateToToSuspendedByAdminProfileScreen()
    fun navigateUp()
}