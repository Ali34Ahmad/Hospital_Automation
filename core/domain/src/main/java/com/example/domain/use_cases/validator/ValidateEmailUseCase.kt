package com.example.domain.use_cases.validator

import com.example.utility.validation.validator.EmailValidator
import com.example.utility.validation.ValidatorErrorMessage

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidatorErrorMessage.Email? {
        return EmailValidator.validate(email)
    }
}