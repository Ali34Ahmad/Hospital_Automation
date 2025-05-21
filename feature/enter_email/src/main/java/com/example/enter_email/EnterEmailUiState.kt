package com.example.enter_email

import com.example.utility.network.Error

data class EnterEmailUiState(
    val email: String = "",
    val emailError: String? = null,
    val isSendOtpCodeButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessful: Boolean = false,
)