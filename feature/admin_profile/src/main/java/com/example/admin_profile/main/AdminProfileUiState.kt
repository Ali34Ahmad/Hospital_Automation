package com.example.admin_profile.main

import com.example.model.admin_account.AdminProfileResponse
import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.Error

data class AdminProfileUiState(
    val adminId: Int?=null,
    val userInfo: AdminProfileResponse?=null,
    val screenState: ScreenState= ScreenState.IDLE,

    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,
)
