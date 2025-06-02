package com.example.email_verification.email_verified_successfully.main

import com.example.model.enums.ScreenState
import com.example.utility.network.Error

data class EmailVerifiedSuccessfullyUiState(
    val email: String = "",
    val password: String = "",
    val navigateToResetPassword: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)