package com.example.domain.repositories

import com.example.model.auth.login.LoginRequest
import com.example.model.auth.login.LoginResponse
import com.example.model.auth.logout.LogoutRequest
import com.example.model.auth.logout.LogoutResponse
import com.example.model.auth.reset_password.ResetPasswordRequest
import com.example.model.auth.reset_password.ResetPasswordResponse
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.send_otp.SendOtpResponse
import com.example.model.auth.signup.RegistrationResponse
import com.example.model.auth.signup.SignUpCredentials
import com.example.model.auth.verify_otp.VerifyEmailOtpRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface AuthRepository {
    suspend fun login(
        loginRequest: LoginRequest
    ): Result<LoginResponse, rootError>

    suspend fun verifyEmail(
        verifyEmailOtpRequest: VerifyEmailOtpRequest
    ): Result<VerifyEmailOtpResponse, rootError>

    suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequest
    ): Result<SendOtpResponse, rootError>

    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest
    ): Result<ResetPasswordResponse, rootError>

    suspend fun logout(
        logoutRequest: LogoutRequest,
    ): Result<LogoutResponse, rootError>

}