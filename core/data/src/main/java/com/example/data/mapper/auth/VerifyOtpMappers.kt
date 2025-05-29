package com.example.data.mapper.auth

import com.example.model.auth.verify_otp.VerifyEmailOtpRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpResponse
import com.example.network.model.request.VerifyEmailOtpRequestDto
import com.example.network.model.response.VerifyEmailOtpResponseDto

fun VerifyEmailOtpRequest.toVerifyEmailOtpRequestDto(): VerifyEmailOtpRequestDto {
    return VerifyEmailOtpRequestDto(
        email = this.email,
        otp = this.otp
    )
}

fun VerifyEmailOtpResponseDto.toVerifyEmailOtpResponse(): VerifyEmailOtpResponse {
    return VerifyEmailOtpResponse(
        message = this.message
    )
}