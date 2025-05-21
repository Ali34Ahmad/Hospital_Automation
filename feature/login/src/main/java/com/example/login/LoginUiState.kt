package com.example.login

import com.example.utility.network.Error

data class LoginUiState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoginButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessfulLogin: Boolean = false,
    val isOtpCodeSent: Boolean = false,
)