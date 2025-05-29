package com.example.enter_email.validator

import com.example.util.UiText

data class EnterEmailValidationResult(
    val emailError: UiText? = null,
) {
    fun hasErrors(): Boolean {
        return listOfNotNull(
            emailError,
        ).isNotEmpty()
    }
}