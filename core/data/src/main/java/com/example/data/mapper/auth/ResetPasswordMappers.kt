package com.example.data.mapper.auth

import com.example.data.mapper.enums.toRoleDto
import com.example.model.auth.reset_password.ResetPasswordRequest
import com.example.model.auth.reset_password.ResetPasswordResponse
import com.example.network.model.request.auth.ResetPasswordRequestDto
import com.example.network.model.response.auth.ResetPasswordResponseDto

fun ResetPasswordRequest.toResetPasswordRequestDto() =
    ResetPasswordRequestDto(
        email = this.email,
        password = this.password,
    )

fun ResetPasswordResponseDto.toResetPasswordResponse() =
    ResetPasswordResponse(
        message = this.message,
        updatedData = this.updatedData
    )