package com.example.login.main

import com.example.model.enums.ScreenState
import com.example.util.UiText

data class LoginUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val showPassword: Boolean = false,
    val passwordError: UiText? = null,
    val isLoginButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)