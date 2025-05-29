package com.example.domain.use_cases.validator

import com.example.utility.validation.validator.TextValidator
import com.example.utility.validation.ValidatorErrorMessage
import com.example.utility.validation.validator.ConfirmPasswordValidator
import com.example.utility.validation.validator.PasswordValidator

class ValidateConfirmPasswordUseCase {
    operator fun invoke(text: String,password: String): ValidatorErrorMessage.ConfirmPassword? {
        return ConfirmPasswordValidator.validate(text,password)
    }
}