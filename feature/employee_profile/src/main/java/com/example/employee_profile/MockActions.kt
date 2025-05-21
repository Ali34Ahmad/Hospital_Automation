package com.example.employee_profile

import com.example.constants.enums.Gender


fun mockEmployeeProfileNavigationUiActions()=object : EmployeeProfileNavigationUiActions {
    override fun navigateToCallApp(phoneNumber: String) {

    }

    override fun navigateToEmail(email: String,subject: String) {

    }

    override fun navigateToAddedChildrenScreen() {

    }

    override fun navigateToEmploymentHistoryScreen() {

    }

    override fun navigateUp() {

    }

    override fun navigateToLoginScreen() {

    }

}

fun mockEmployeeProfileBusinessUiActions()=object : EmployeeProfileBusinessUiActions {
    override fun onRefreshProfile() {

    }

    override fun onDeactivateMyAccount() {

    }

    override fun onLogout() {

    }

    override fun hideErrorDialog() {

    }

}
