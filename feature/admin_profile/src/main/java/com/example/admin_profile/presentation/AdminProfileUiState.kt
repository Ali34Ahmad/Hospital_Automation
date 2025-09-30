package com.example.admin_profile.presentation

import com.example.model.admin_account.AdminProfileResponse
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class AdminProfileUiState(
    val adminId: Int?=null,
    val userInfo: AdminProfileResponse?=null,
    val screenState: ScreenState= ScreenState.IDLE,

    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,
)
