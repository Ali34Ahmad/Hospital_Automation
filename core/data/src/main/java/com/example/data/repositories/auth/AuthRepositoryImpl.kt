package com.example.data.repositories.auth

import com.example.data.mapper.auth.toLoginRequestDto
import com.example.data.mapper.auth.toLoginResponse
import com.example.data.mapper.auth.toLogoutResponse
import com.example.data.mapper.auth.toRegistrationRequestDto
import com.example.data.mapper.auth.toRegistrationResponse
import com.example.data.mapper.auth.toResetPasswordRequestDto
import com.example.data.mapper.auth.toResetPasswordResponse
import com.example.data.mapper.auth.toSendOtpRequestDto
import com.example.data.mapper.auth.toSendOtpResponse
import com.example.data.mapper.auth.toVerifyEmailOtpRequestDto
import com.example.data.mapper.auth.toVerifyEmailOtpResponse
import com.example.domain.repositories.AuthRepository
import com.example.model.auth.login.LoginRequest
import com.example.model.auth.login.LoginResponse
import com.example.model.auth.logout.LogoutResponse
import com.example.model.auth.reset_password.ResetPasswordRequest
import com.example.model.auth.reset_password.ResetPasswordResponse
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.send_otp.SendOtpResponse
import com.example.model.auth.signup.RegistrationResponse
import com.example.model.auth.signup.SignUpCredentials
import com.example.model.auth.verify_otp.VerifyEmailOtpRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpResponse
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
) : AuthRepository {
    override suspend fun signup(signUpCredentials: SignUpCredentials): Result<RegistrationResponse, rootError> =
        authApiService.signup(
            registrationRequestDto = signUpCredentials.toRegistrationRequestDto()
        ).map { registrationResponseDto ->
            registrationResponseDto.toRegistrationResponse()
        }

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, rootError> =
        authApiService.login(
            loginRequestDto = loginRequest.toLoginRequestDto()
        ).map { loginResponseDto ->
            loginResponseDto.toLoginResponse()
        }

    override suspend fun verifyEmail(verifyEmailOtpRequest: VerifyEmailOtpRequest):
            Result<VerifyEmailOtpResponse, rootError> =
        authApiService.verifyEmail(
            verifyEmailOtpRequestDto = verifyEmailOtpRequest.toVerifyEmailOtpRequestDto()
        ).map { verifyEmailOtpResponseDto ->
            verifyEmailOtpResponseDto.toVerifyEmailOtpResponse()
        }

    override suspend fun sendOtpCodeToEmail(sendOtpCodeRequest: SendOtpRequest): Result<SendOtpResponse, rootError> =
        authApiService.sendOtpCodeToEmail(
            sendOtpCodeRequest = sendOtpCodeRequest.toSendOtpRequestDto()
        ).map { sendOtpResponseDto ->
            sendOtpResponseDto.toSendOtpResponse()
        }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Result<ResetPasswordResponse, rootError> =
        authApiService.resetPassword(
            resetPasswordRequestDto = resetPasswordRequest.toResetPasswordRequestDto()
        ).map { resetPasswordResponseDto ->
            resetPasswordResponseDto.toResetPasswordResponse()
        }

    override suspend fun logout(): Result<LogoutResponse, rootError> =
        authApiService.logout()
            .map { logoutResponseDto ->
                logoutResponseDto.toLogoutResponse()
            }
}