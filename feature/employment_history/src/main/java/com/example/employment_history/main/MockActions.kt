package com.example.employment_history.main

import com.example.model.enums.Role


fun mockEmploymentHistoryNavigationUiActions() = object : EmploymentHistoryNavigationUiActions {
    override fun navigateToAcceptedByAdminProfileScreen() {

    }

    override fun navigateToResignedByAdminProfileScreen() {

    }

    override fun navigateToSuspendedByAdminProfileScreen(role: Role?) {

    }

    override fun navigateUp() {

    }

}

fun mockEmploymentHistoryBusinessUiActions() = object : EmploymentHistoryBusinessUiActions {
    override fun onReloadEmploymentHistory() {

    }

    override fun onHideFileDownloaderDialog() {

    }

    override fun onDownloadFile() {

    }

    override fun onCancelFileDownloading() {

    }

    override fun onShowFileDownloaderDialog() {

    }

    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }

}
