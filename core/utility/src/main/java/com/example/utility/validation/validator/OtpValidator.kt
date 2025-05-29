package com.example.utility.validation.validator // Your use cases package

import com.example.utility.validation.ValidatorErrorMessage

private const val REQUIRED_OTP_LENGTH: Int = 4

data object OtpValidator : Validator {
    override fun validate(value: String): ValidatorErrorMessage.OtpError?{

        val trimmedOtp = value.trim()

        if (trimmedOtp.isEmpty()) {
            return ValidatorErrorMessage.OtpError.EMPTY_FIELD
        }

        if (trimmedOtp.length != REQUIRED_OTP_LENGTH) {
            return ValidatorErrorMessage.OtpError.INVALID_LENGTH
        }

        if (!trimmedOtp.all { it.isDigit() }) {
            return ValidatorErrorMessage.OtpError.NON_DIGIT_CHARACTER
        }

        return null
    }

}
