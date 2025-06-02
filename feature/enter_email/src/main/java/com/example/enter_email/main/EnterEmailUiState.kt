package com.example.enter_email.main

import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.Error

data class EnterEmailUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val isSendOtpCodeButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)