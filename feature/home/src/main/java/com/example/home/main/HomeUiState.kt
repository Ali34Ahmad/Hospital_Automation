package com.example.home.main

import com.example.model.enums.ScreenState
import com.example.utility.network.Error

data class HomeUiState(
    val selectedDrawerIndex: Int? = null,
    val isDarkTheme: Boolean = false,
    val showPermissionCard: Boolean = true,
    val isPermissionGranted: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)
