package com.example.data.mapper.auth

import com.example.data.mapper.enums.toRoleDto
import com.example.model.auth.verify_otp.VerifyEmailOtpRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpResponse
import com.example.network.model.request.auth.VerifyEmailOtpRequestDto
import com.example.network.model.response.auth.VerifyEmailOtpResponseDto

fun VerifyEmailOtpRequest.toVerifyEmailOtpRequestDto(): VerifyEmailOtpRequestDto {
    return VerifyEmailOtpRequestDto(
        email = this.email,
        otp = this.otp,
    )
}

fun VerifyEmailOtpResponseDto.toVerifyEmailOtpResponse(): VerifyEmailOtpResponse {
    return VerifyEmailOtpResponse(
        message = this.message
    )
}