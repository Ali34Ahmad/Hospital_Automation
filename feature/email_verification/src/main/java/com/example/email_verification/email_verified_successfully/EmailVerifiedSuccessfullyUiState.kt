package com.example.email_verification.email_verified_successfully

import com.example.utility.network.Error

data class EmailVerifiedSuccessfullyUiState(
    val email: String = "",
    val password: String = "",
    val navigateToResetPassword: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessful: Boolean = false,
)