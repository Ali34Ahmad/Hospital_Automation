package com.example.email_verification.otp_verification.validator

import com.example.util.UiText

data class OtpValidationResult(
    val otpError: UiText? = null
) {
    fun hasErrors(): Boolean {
        return otpError != null
    }
}