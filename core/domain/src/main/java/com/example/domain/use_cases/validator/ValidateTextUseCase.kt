package com.example.domain.use_cases.validator

import com.example.utility.validation.validator.TextValidator
import com.example.utility.validation.ValidatorErrorMessage

class ValidateTextUseCase {
    operator fun invoke(text: String): ValidatorErrorMessage.NormalText? {
        return TextValidator.validate(text)
    }
}