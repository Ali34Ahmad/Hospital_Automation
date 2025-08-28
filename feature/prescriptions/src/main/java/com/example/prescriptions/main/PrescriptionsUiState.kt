package com.example.prescriptions.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.model.user.UserMainInfo
import com.example.util.UiText

data class PrescriptionsUiState(
    val userMainInfo: UserMainInfo?=null,
    val searchText: String = "",
    val topBarMode: TopBarState = TopBarState.DEFAULT,

    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage: UiText?=null,
)