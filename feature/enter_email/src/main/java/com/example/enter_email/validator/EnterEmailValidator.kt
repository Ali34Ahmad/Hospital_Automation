package com.example.enter_email.validator

import com.example.domain.use_cases.validator.ValidateEmailUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
import com.example.enter_email.main.EnterEmailUiState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.validation.ValidatorErrorMessage

class EnterEmailValidator(
    private val validateEmailUseCase: ValidateEmailUseCase,
){
    fun validate(state: EnterEmailUiState): EnterEmailValidationResult {
        val emailError = validateEmail(state.email)

        return EnterEmailValidationResult(
            emailError = emailError,
        )
    }
    
    private fun validateEmail(email: String): UiText? {
        return when (validateEmailUseCase(email)) {
            ValidatorErrorMessage.Email.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.Email.INVALID_EMAIL -> UiText.StringResource(R.string.invalid_email)
            null -> null
        }
    }
}