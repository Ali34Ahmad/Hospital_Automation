package com.example.network.remote.auth

import com.example.network.model.request.LoginRequestDto
import com.example.network.model.request.RegistrationRequestDto
import com.example.network.model.request.ResetPasswordRequestDto
import com.example.network.model.request.SendOtpRequestDto
import com.example.network.model.request.VerifyEmailOtpRequestDto
import com.example.network.model.response.LoginResponseDto
import com.example.network.model.response.LogoutResponseDto
import com.example.network.model.response.RegistrationResponseDto
import com.example.network.model.response.ResetPasswordResponseDto
import com.example.network.model.response.SendOtpResponseDto
import com.example.network.model.response.VerifyEmailOtpResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AuthApiService {
    suspend fun signup(
        registrationRequestDto: RegistrationRequestDto
    ): Result<RegistrationResponseDto, rootError >

    suspend fun login(
        loginRequestDto: LoginRequestDto
    ): Result<LoginResponseDto, rootError>


    suspend fun verifyEmail(
        verifyEmailOtpRequestDto: VerifyEmailOtpRequestDto
    ): Result<VerifyEmailOtpResponseDto, rootError>

    suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequestDto
    ): Result<SendOtpResponseDto, rootError>

    suspend fun resetPassword(
        resetPasswordRequestDto: ResetPasswordRequestDto
    ): Result<ResetPasswordResponseDto, rootError>

    suspend fun logout(
    ): Result<LogoutResponseDto, rootError>

}