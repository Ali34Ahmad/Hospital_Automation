package com.example.admin_profile

import com.example.model.admin_account.AdminProfile
import com.example.model.admin_account.AdminProfileResponse
import com.example.model.employee.EmployeeProfileResponse
import com.example.network.model.response.EmployeeProfileResponseDto
import com.example.utility.network.Error

data class AdminProfileUiState(
    val adminId: Int?=null,
    val userInfo: AdminProfileResponse?=null,
    val isLoadingProfile: Boolean=true,
    val fetchingProfileError: Error?=null,

    val showErrorDialog: Boolean=false,
    val errorDialogText: String="",
)
