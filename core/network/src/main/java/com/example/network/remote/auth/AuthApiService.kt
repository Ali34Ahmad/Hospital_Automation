package com.example.network.remote.auth

import com.example.network.model.request.LoginRequest
import com.example.network.model.request.RegistrationRequest
import com.example.network.model.request.ResetPasswordRequest
import com.example.network.model.request.SendOtpRequest
import com.example.network.model.request.VerifyEmailOtpRequest
import com.example.network.model.response.CheckEmployeePermissionResponse
import com.example.network.model.response.LoginResponse
import com.example.network.model.response.LogoutResponse
import com.example.network.model.response.RegistrationResponse
import com.example.network.model.response.ResetPasswordResponse
import com.example.network.model.response.VerifyEmailOtpResponse
import com.example.network.utility.Resource
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AuthApiService {
    suspend fun signup(
        registrationRequest: RegistrationRequest
    ): Result<RegistrationResponse, rootError >

    suspend fun login(
        loginRequest: LoginRequest
    ): Result<LoginResponse, rootError>


    suspend fun verifyEmail(
        verifyEmailOtpRequest: VerifyEmailOtpRequest
    ): Result<VerifyEmailOtpResponse, rootError>

    suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequest
    ): Result<VerifyEmailOtpResponse, rootError>

    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest
    ): Result<ResetPasswordResponse, rootError>

    suspend fun checkEmployeePermission(): Result<CheckEmployeePermissionResponse, rootError>

    suspend fun logout(
    ): Result<LogoutResponse, rootError>

}