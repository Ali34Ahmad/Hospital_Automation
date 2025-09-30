package com.example.home.presentation

import com.example.model.enums.ScreenState
import com.example.util.UiText

data class EmployeeHomeUiState(
    val isDarkTheme: Boolean = false,
    val showPermissionCard: Boolean = true,
    val isPermissionGranted: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,
)
