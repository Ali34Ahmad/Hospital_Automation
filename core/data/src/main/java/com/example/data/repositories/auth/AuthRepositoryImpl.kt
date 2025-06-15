package com.example.data.repositories.auth

import com.example.data.mapper.auth.toLoginRequestDto
import com.example.data.mapper.auth.toLoginResponse
import com.example.data.mapper.auth.toLogoutResponse
import com.example.data.mapper.auth.singup.toRegistrationRequestDto
import com.example.data.mapper.auth.singup.toRegistrationResponse
import com.example.data.mapper.auth.toLogoutRequestDto
import com.example.data.mapper.auth.toResetPasswordRequestDto
import com.example.data.mapper.auth.toResetPasswordResponse
import com.example.data.mapper.auth.toSendOtpRequestDto
import com.example.data.mapper.auth.toSendOtpResponse
import com.example.data.mapper.auth.toVerifyEmailOtpRequestDto
import com.example.data.mapper.auth.toVerifyEmailOtpResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.AuthRepository
import com.example.domain.repositories.local.UserPreferencesRepository
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
import com.example.network.model.request.auth.LogoutRequestDto
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.onSuccess
import com.example.utility.network.rootError
import kotlin.math.log

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, rootError> =
        authApiService.login(
            loginRequestDto = loginRequest.toLoginRequestDto(),
            role = loginRequest.role.toRoleDto(),
        ).map { loginResponseDto ->
            loginResponseDto.toLoginResponse()
        }.onSuccess { loginResponseDto ->
            userPreferencesRepository.updateToken(loginResponseDto.token)
        }

    override suspend fun verifyEmail(verifyEmailOtpRequest: VerifyEmailOtpRequest):
            Result<VerifyEmailOtpResponse, rootError> =
        authApiService.verifyEmail(
            verifyEmailOtpRequestDto = verifyEmailOtpRequest.toVerifyEmailOtpRequestDto(),
            role = verifyEmailOtpRequest.role.toRoleDto()
        ).map { verifyEmailOtpResponseDto ->
            verifyEmailOtpResponseDto.toVerifyEmailOtpResponse()
        }

    override suspend fun sendOtpCodeToEmail(sendOtpCodeRequest: SendOtpRequest): Result<SendOtpResponse, rootError> =
        authApiService.sendOtpCodeToEmail(
            sendOtpCodeRequest = sendOtpCodeRequest.toSendOtpRequestDto(),
            role = sendOtpCodeRequest.role.toRoleDto()
        ).map { sendOtpResponseDto ->
            sendOtpResponseDto.toSendOtpResponse()
        }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Result<ResetPasswordResponse, rootError> =
        authApiService.resetPassword(
            resetPasswordRequestDto = resetPasswordRequest.toResetPasswordRequestDto(),
            role = resetPasswordRequest.role.toRoleDto(),
        ).map { resetPasswordResponseDto ->
            resetPasswordResponseDto.toResetPasswordResponse()
        }

    override suspend fun logout(
        logoutRequest: LogoutRequest,
    ): Result<LogoutResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            authApiService.logout(
                token = token,
                role = logoutRequest.role.toRoleDto(),
            )
                .map { logoutResponseDto ->
                    logoutResponseDto.toLogoutResponse()
                }
                .onSuccess {
                    userPreferencesRepository.updateToken(null)
                }
        }
}