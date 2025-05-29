package com.example.data.mapper.auth

import com.example.model.auth.reset_password.ResetPasswordRequest
import com.example.model.auth.reset_password.ResetPasswordResponse
import com.example.network.model.request.ResetPasswordRequestDto
import com.example.network.model.response.ResetPasswordResponseDto

fun ResetPasswordRequest.toResetPasswordRequestDto() =
    ResetPasswordRequestDto(
        email = this.email,
        password = this.password
    )

fun ResetPasswordResponseDto.toResetPasswordResponse() =
    ResetPasswordResponse(
        message = this.message,
        updatedData = this.updatedData
    )