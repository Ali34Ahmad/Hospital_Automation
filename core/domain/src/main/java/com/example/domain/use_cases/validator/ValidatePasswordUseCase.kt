package com.example.domain.use_cases.validator

import com.example.utility.validation.validator.TextValidator
import com.example.utility.validation.ValidatorErrorMessage
import com.example.utility.validation.validator.PasswordValidator

class ValidatePasswordUseCase {
    operator fun invoke(text: String): ValidatorErrorMessage.Password? {
        return PasswordValidator.validate(text)
    }
}