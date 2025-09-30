package com.example.network.remote.auth

import com.example.network.model.enums.RoleDto
import com.example.network.model.request.auth.LoginRequestDto
import com.example.network.model.request.auth.ResetPasswordRequestDto
import com.example.network.model.request.auth.SendOtpRequestDto
import com.example.network.model.request.auth.VerifyEmailOtpRequestDto
import com.example.network.model.request.auth.signup.BaseRegistrationRequestDto
import com.example.network.model.request.auth.signup.DoctorRegistrationRequestDto
import com.example.network.model.response.auth.LoginResponseDto
import com.example.network.model.response.auth.LogoutResponseDto
import com.example.network.model.response.auth.ResetPasswordResponseDto
import com.example.network.model.response.auth.SendOtpResponseDto
import com.example.network.model.response.auth.VerifyEmailOtpResponseDto
import com.example.network.model.response.auth.signup.BaseRegistrationResponseDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AuthApiService {
    suspend fun generalSignup(
        baseRegistrationRequestDto: BaseRegistrationRequestDto,
    ): Result<BaseRegistrationResponseDto, NetworkError>

    suspend fun doctorSignup(
        doctorRegistrationRequestDto: DoctorRegistrationRequestDto,
    ): Result<BaseRegistrationResponseDto, NetworkError>

    suspend fun login(
        loginRequestDto: LoginRequestDto,
        role: RoleDto
    ): Result<LoginResponseDto, NetworkError>


    suspend fun verifyEmail(
        verifyEmailOtpRequestDto: VerifyEmailOtpRequestDto,
        role: RoleDto
    ): Result<VerifyEmailOtpResponseDto, NetworkError>

    suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequestDto,
        role: RoleDto
    ): Result<SendOtpResponseDto, NetworkError>

    suspend fun resetPassword(
        resetPasswordRequestDto: ResetPasswordRequestDto,
        role: RoleDto
    ): Result<ResetPasswordResponseDto, NetworkError>

    suspend fun logout(
        token:String,
        role: RoleDto,
    ): Result<LogoutResponseDto, NetworkError>

}