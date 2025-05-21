package com.example.reset_password

import com.example.utility.network.Error

data class ResetPasswordUiState(
    val email: String = "",
    val password: String = "",
    val passwordError: String? = null,
    val isResetPasswordButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessful: Boolean = false,
)