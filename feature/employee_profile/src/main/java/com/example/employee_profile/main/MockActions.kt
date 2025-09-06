package com.example.employee_profile.main


fun mockEmployeeProfileNavigationUiActions()=object : EmployeeProfileNavigationUiActions {
    override fun navigateToAddedChildrenScreen(employeeId:Int?) {

    }

    override fun navigateToEmploymentHistoryScreen(employeeId:Int?) {

    }

    override fun navigateUp() {

    }

    override fun navigateToLoginScreen() {

    }

}

fun mockEmployeeProfileBusinessUiActions()=object : EmployeeProfileBusinessUiActions {
    override fun onDeactivateAccount() {

    }

    override fun onReactivateMyAccount() {

    }

    override fun onLogout() {

    }

    override fun onResign() {

    }

    override fun hideErrorDialog() {

    }

    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }

    override fun onUpdateDeactivationReason(value: String) {

    }

    override fun onHideDeactivationReasonDialog() {

    }

    override fun onShowDeactivationReasonDialog() {

    }

}
