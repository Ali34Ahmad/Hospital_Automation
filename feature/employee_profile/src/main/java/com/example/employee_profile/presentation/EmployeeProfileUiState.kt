package com.example.employee_profile.presentation

import com.example.employee_profile.navigation.EmployeeProfileAccessType
import com.example.model.employee.EmployeeProfileResponse
import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.Error

data class EmployeeProfileUiState(
    val userInfo: EmployeeProfileResponse?=null,
    val employeeId: Int?=null,

    val employeeProfileAccessType: EmployeeProfileAccessType?=null,
    val profileScreenState: ScreenState= ScreenState.IDLE,

    val isLoggedOutSuccessfully: Boolean=false,
    val logOutError: Error?=null,

    val isAccountDeactivatedSuccessfully: Boolean=false,
    val accountDeactivationError: Error?=null,

    val showLoadingDialog: Boolean=false,
    val loadingDialogText: UiText?=null,

    val showErrorDialog: Boolean=false,
    val errorDialogText: UiText?=null,

    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,

    val isWriteDeactivationReasonDialogShown: Boolean=false,
    val deactivationReasonText:String="",

    )
