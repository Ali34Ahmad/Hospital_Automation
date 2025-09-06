package com.example.employment_requests.main

import com.example.model.enums.ScreenState
import com.example.model.user.UserMainInfo
import com.example.model.vaccine.VaccineData
import com.example.util.UiText

data class RequestsUiState(
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,

    val isDarkTheme: Boolean=false,
    val isDrawerOpened: Boolean = false,
)
