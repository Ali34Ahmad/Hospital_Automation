package com.example.login.validator

import com.example.util.UiText

data class LoginValidationResult(
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
) {
    fun hasErrors(): Boolean {
        return listOfNotNull(
            emailError,
            passwordError,
        ).isNotEmpty()
    }
}