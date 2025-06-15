package com.example.network.remote.auth

import com.example.network.model.enums.RoleDto
import com.example.network.model.request.auth.LoginRequestDto
import com.example.network.model.request.auth.LogoutRequestDto
import com.example.network.model.request.auth.signup.BaseRegistrationRequestDto
import com.example.network.model.request.auth.ResetPasswordRequestDto
import com.example.network.model.request.auth.SendOtpRequestDto
import com.example.network.model.request.auth.VerifyEmailOtpRequestDto
import com.example.network.model.response.auth.LoginResponseDto
import com.example.network.model.response.auth.LogoutResponseDto
import com.example.network.model.response.auth.signup.BaseRegistrationResponseDto
import com.example.network.model.response.auth.ResetPasswordResponseDto
import com.example.network.model.response.auth.SendOtpResponseDto
import com.example.network.model.response.auth.VerifyEmailOtpResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AuthApiService {
    suspend fun login(
        loginRequestDto: LoginRequestDto,
        role: RoleDto
    ): Result<LoginResponseDto, rootError>


    suspend fun verifyEmail(
        verifyEmailOtpRequestDto: VerifyEmailOtpRequestDto,
        role: RoleDto
    ): Result<VerifyEmailOtpResponseDto, rootError>

    suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequestDto,
        role: RoleDto
    ): Result<SendOtpResponseDto, rootError>

    suspend fun resetPassword(
        resetPasswordRequestDto: ResetPasswordRequestDto,
        role: RoleDto
    ): Result<ResetPasswordResponseDto, rootError>

    suspend fun logout(
        token:String,
        role: RoleDto,
    ): Result<LogoutResponseDto, rootError>

}