package com.example.reset_password.validator

import com.example.domain.use_cases.validator.ValidatePasswordUseCase
import com.example.reset_password.main.ResetPasswordUiState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.validation.ValidatorErrorMessage

class ResetPasswordValidator(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
){
    fun validate(state: ResetPasswordUiState): ResetPasswordValidationResult {
        val emailError = validatePassword(state.email)

        return ResetPasswordValidationResult(
            passwordError = emailError,
        )
    }
    
    private fun validatePassword(password: String): UiText? {
        return when (validatePasswordUseCase(password)) {
            ValidatorErrorMessage.Password.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.Password.LESS_THAN_EIGHT_CHARS -> UiText.StringResource(R.string.password_must_contains_more_than_eight_letters)
            null -> null
        }
    }
}