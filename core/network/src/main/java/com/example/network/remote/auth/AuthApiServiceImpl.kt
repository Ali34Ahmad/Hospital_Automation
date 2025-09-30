package com.example.network.remote.auth

import android.util.Log
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
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val AUTH_API_TAG = "AuthApi"

class AuthApiServiceImpl(
    private val client: HttpClient,
) : AuthApiService {
    override suspend fun generalSignup(
        baseRegistrationRequestDto: BaseRegistrationRequestDto
    ): Result<BaseRegistrationResponseDto, NetworkError> =
        doApiCall<BaseRegistrationResponseDto>(
            tag = AUTH_API_TAG
        ) {
            Log.d(AUTH_API_TAG, "generalSignup")

            client.post(ApiRoutes.getSingUpEndpointFor(baseRegistrationRequestDto.role)) {
                contentType(ContentType.Application.Json)
                setBody(baseRegistrationRequestDto)
            }
        }

    override suspend fun doctorSignup(
        doctorRegistrationRequestDto: DoctorRegistrationRequestDto
    ): Result<BaseRegistrationResponseDto, NetworkError> =
        doApiCall<BaseRegistrationResponseDto>(
            tag = AUTH_API_TAG
        ) {
            Log.d(AUTH_API_TAG, "doctorSignup")

            client.post(ApiRoutes.getSingUpEndpointFor(doctorRegistrationRequestDto.role)) {
                contentType(ContentType.Application.Json)
                setBody(doctorRegistrationRequestDto)
            }
        }

    override suspend fun login(
        loginRequestDto: LoginRequestDto,
        role: RoleDto,
    ): Result<LoginResponseDto, NetworkError> =
        doApiCall<LoginResponseDto>(
            tag = AUTH_API_TAG
        ) {
            Log.d(AUTH_API_TAG, "login")

            client.post(ApiRoutes.getLoginEndpointFor(role)) {
                contentType(ContentType.Application.Json)
                setBody(loginRequestDto)
            }
        }

    override suspend fun verifyEmail(
        verifyEmailOtpRequestDto: VerifyEmailOtpRequestDto,
        role: RoleDto
    ): Result<VerifyEmailOtpResponseDto, NetworkError> =
        doApiCall<VerifyEmailOtpResponseDto>(
            tag = AUTH_API_TAG
        ) {
            Log.d(AUTH_API_TAG, "verifyEmail")

            client.post(ApiRoutes.getVerifyEmailEndpointFor(role)) {
                contentType(ContentType.Application.Json)
                setBody(verifyEmailOtpRequestDto)
            }
        }

    override suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequestDto,
        role: RoleDto
    ): Result<SendOtpResponseDto, NetworkError> =
        doApiCall<SendOtpResponseDto>(
            tag = AUTH_API_TAG
        ) {
            Log.d(AUTH_API_TAG, "sendOtpCodeToEmail")

            client.post(ApiRoutes.getSendOtpToEmailEndpointFor(role)) {
                contentType(ContentType.Application.Json)
                setBody(sendOtpCodeRequest)
            }
        }

    override suspend fun resetPassword(
        resetPasswordRequestDto: ResetPasswordRequestDto,
        role: RoleDto
    ): Result<ResetPasswordResponseDto, NetworkError> =
        doApiCall<ResetPasswordResponseDto>(
            tag = AUTH_API_TAG
        ) {
            Log.d(AUTH_API_TAG, "sendOtpCodeToEmail")

            client.post(ApiRoutes.getResetPasswordEndPointFor(role)) {
                contentType(ContentType.Application.Json)
                setBody(resetPasswordRequestDto)
            }
        }

    override suspend fun logout(
        token: String,
        role: RoleDto
    ): Result<LogoutResponseDto, NetworkError> =
        doApiCall<LogoutResponseDto>(
            tag = AUTH_API_TAG
        ) {
            Log.d(AUTH_API_TAG, "logout")

            client.post(ApiRoutes.getLogoutEndPointFor(role)) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }
}