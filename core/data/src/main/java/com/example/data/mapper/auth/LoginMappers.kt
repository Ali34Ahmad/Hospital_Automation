package com.example.data.mapper.auth

import com.example.data.mapper.enums.toRoleDto
import com.example.model.auth.login.LoginRequest
import com.example.model.auth.login.LoginResponse
import com.example.network.model.request.auth.LoginRequestDto
import com.example.network.model.response.auth.LoginResponseDto

fun LoginRequest.toLoginRequestDto() =
    LoginRequestDto(
        email = this.email,
        password = this.password,
    )

fun LoginResponseDto.toLoginResponse() =
    LoginResponse(
        message = this.message,
        token = this.token
    )

