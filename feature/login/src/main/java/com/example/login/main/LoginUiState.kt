package com.example.login.main

import com.example.util.UiText
import com.example.utility.network.Error

data class LoginUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isLoginButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessfulLogin: Boolean = false,
    val isOtpCodeSent: Boolean = false,
)