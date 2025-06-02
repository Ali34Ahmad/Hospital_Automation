package com.example.admin_profile.main

import com.example.model.admin_account.AdminProfileResponse
import com.example.utility.network.Error

data class AdminProfileUiState(
    val adminId: Int?=null,
    val userInfo: AdminProfileResponse?=null,
    val isLoadingProfile: Boolean=true,
    val fetchingProfileError: Error?=null,

    val showErrorDialog: Boolean=false,
    val errorDialogText: String="",
)
