package com.example.reset_password.presentation

import com.example.model.enums.ScreenState
import com.example.util.UiText

data class ResetPasswordUiState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val passwordError: UiText? = null,
    val isResetPasswordButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)