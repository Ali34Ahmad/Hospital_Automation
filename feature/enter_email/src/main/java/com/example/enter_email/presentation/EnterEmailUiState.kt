package com.example.enter_email.presentation

import com.example.model.enums.ScreenState
import com.example.util.UiText

data class EnterEmailUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val isSendOtpCodeButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)