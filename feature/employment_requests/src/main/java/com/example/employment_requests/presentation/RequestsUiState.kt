package com.example.employment_requests.presentation

import com.example.model.enums.ScreenState
import com.example.util.UiText

data class RequestsUiState(
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,

    val isDarkTheme: Boolean=false,
    val isDrawerOpened: Boolean = false,
)
