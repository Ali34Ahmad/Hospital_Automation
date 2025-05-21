package com.example.home

import com.example.utility.network.Error

data class HomeUiState(
    val selectedDrawerIndex: Int? = null,
    val isDarkTheme: Boolean = false,
    val showPermissionCard: Boolean = true,
    val isPermissionGranted: Boolean = false,
    val isLoading: Boolean = true,
    val error: Error? = null,
    val showErrorDialog: Boolean=false,
    val isSuccessful: Boolean=false,
)
