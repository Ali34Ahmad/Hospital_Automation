package com.example.login.validator

import com.example.domain.use_cases.validator.ValidateEmailUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
import com.example.login.presentation.LoginUiState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.validation.ValidatorErrorMessage

class LoginValidator(
    private val validateTextUseCase: ValidateTextUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
){
    fun validate(state: LoginUiState): LoginValidationResult {
        val emailError = validateEmail(state.email)
        val passwordError = validateNormalText(state.password)

        return LoginValidationResult(
            emailError = emailError,
            passwordError = passwordError,
        )
    }
    
    private fun validateEmail(email: String): UiText? {
        return when (validateEmailUseCase(email)) {
            ValidatorErrorMessage.Email.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.Email.INVALID_EMAIL -> UiText.StringResource(R.string.invalid_email)
            null -> null
        }
    }

    private fun validateNormalText(text: String): UiText? {
        return when (validateTextUseCase(text)) {
            ValidatorErrorMessage.NormalText.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            null -> null
        }
    }
}