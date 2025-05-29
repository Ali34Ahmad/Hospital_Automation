package com.example.reset_password.validator

import com.example.util.UiText

data class ResetPasswordValidationResult(
    val passwordError: UiText? = null,
) {
    fun hasErrors(): Boolean {
        return listOfNotNull(
            passwordError,
        ).isNotEmpty()
    }
}