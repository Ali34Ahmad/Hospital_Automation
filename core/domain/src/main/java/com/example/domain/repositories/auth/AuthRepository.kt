package com.example.domain.repositories.auth

import com.example.model.auth.login.LoginRequest
import com.example.model.auth.login.LoginResponse
import com.example.model.auth.logout.LogoutRequest
import com.example.model.auth.logout.LogoutResponse
import com.example.model.auth.reset_password.ResetPasswordRequest
import com.example.model.auth.reset_password.ResetPasswordResponse
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.send_otp.SendOtpResponse
import com.example.model.auth.signup.BaseRegistrationRequest
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.model.auth.signup.DoctorRegistrationRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

interface AuthRepository {
    suspend fun generalSignup(
        baseRegistrationRequest: BaseRegistrationRequest,
    ): Result<BaseRegistrationResponse, NetworkError>

    suspend fun doctorSignup(
        doctorRegistrationRequest: DoctorRegistrationRequest,
    ): Result<BaseRegistrationResponse, NetworkError>

    suspend fun login(
        loginRequest: LoginRequest
    ): Result<LoginResponse, NetworkError>

    suspend fun verifyEmail(
        verifyEmailOtpRequest: VerifyEmailOtpRequest
    ): Result<VerifyEmailOtpResponse, NetworkError>

    suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequest
    ): Result<SendOtpResponse, NetworkError>

    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest
    ): Result<ResetPasswordResponse, NetworkError>

    suspend fun logout(
        logoutRequest: LogoutRequest,
    ): Result<LogoutResponse, NetworkError>

}