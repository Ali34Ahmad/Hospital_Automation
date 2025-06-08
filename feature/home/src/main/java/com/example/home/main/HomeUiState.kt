package com.example.home.main

import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.Error

data class HomeUiState(
    val isDarkTheme: Boolean = false,
    val showPermissionCard: Boolean = true,
    val isPermissionGranted: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,
)
