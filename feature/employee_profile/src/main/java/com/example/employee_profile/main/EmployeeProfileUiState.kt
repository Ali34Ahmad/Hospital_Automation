package com.example.employee_profile.main

import com.example.employee_profile.navigation.EmployeeProfilePreviousDestination
import com.example.model.employee.EmployeeProfileResponse
import com.example.utility.network.Error

data class EmployeeProfileUiState(
    val userInfo: EmployeeProfileResponse?=null,
    val isLoadingProfile: Boolean=true,
    val fetchingProfileError: Error?=null,

    val isLoggedOutSuccessfully: Boolean=false,
    val logOutError: Error?=null,
    val isAccountDeactivatedSuccessfully: Boolean=false,
    val accountDeactivationError: Error?=null,

    val showLoadingDialog: Boolean=false,
    val loadingDialogText: String="",

    val showErrorDialog: Boolean=false,
    val errorDialogText: String="",
)
