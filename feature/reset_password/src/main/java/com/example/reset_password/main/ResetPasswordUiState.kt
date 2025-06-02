package com.example.reset_password.main

import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.Error

data class ResetPasswordUiState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val passwordError: UiText? = null,
    val isResetPasswordButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)