package com.example.domain.use_cases.validator

import com.example.utility.validation.ValidatorErrorMessage
import com.example.utility.validation.validator.PositiveNumberValidator

class ValidatePositiveNumberUseCase {
    operator fun invoke(text: String):ValidatorErrorMessage.PositiveNumber?{
        return PositiveNumberValidator.validate(text)
    }
}