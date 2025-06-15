package com.example.network.remote.auth

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.auth.LoginRequestDto
import com.example.network.model.request.auth.LogoutRequestDto
import com.example.network.model.request.auth.ResetPasswordRequestDto
import com.example.network.model.request.auth.SendOtpRequestDto
import com.example.network.model.request.auth.VerifyEmailOtpRequestDto
import com.example.network.model.response.auth.LoginResponseDto
import com.example.network.model.response.auth.LogoutResponseDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.auth.ResetPasswordResponseDto
import com.example.network.model.response.auth.SendOtpResponseDto
import com.example.network.model.response.auth.VerifyEmailOtpResponseDto
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApiServiceImpl(
    private val client: HttpClient,
) : AuthApiService {
    override suspend fun login(
        loginRequestDto: LoginRequestDto,
        role: RoleDto,
    ): Result<LoginResponseDto, rootError> =
        try {
            val response: HttpResponse =
                client.post(ApiRoutes.loginEndpointFor(role)) {
                    contentType(ContentType.Application.Json)
                    setBody(loginRequestDto)
                }
            when (response.status.value) {
                in 200..299 -> {
                    Log.v("Login:", "Successfully logged in")
                    val responseBody: LoginResponseDto = response.body()
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("Login out of 200 range:", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }

        } catch (e: Exception) {
            Log.e("Login Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun verifyEmail(
        verifyEmailOtpRequestDto: VerifyEmailOtpRequestDto,
        role: RoleDto
    ): Result<VerifyEmailOtpResponseDto, rootError> =
        try {
            val response: HttpResponse =
                client.post(ApiRoutes.verifyEmailEndpointFor(role)) {
                    contentType(ContentType.Application.Json)
                    setBody(verifyEmailOtpRequestDto)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: VerifyEmailOtpResponseDto = response.body()
                    Log.v(
                        "Verify Email:",
                        "Successfully verified ${verifyEmailOtpRequestDto.email} ${verifyEmailOtpRequestDto.otp}"
                    )
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e(
                        "Verify Email out of 200 range::",
                        errorBody.message + "${verifyEmailOtpRequestDto.email} ${verifyEmailOtpRequestDto.otp}"
                    )
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Verify Email Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun sendOtpCodeToEmail(
        sendOtpCodeRequest: SendOtpRequestDto,
        role: RoleDto
    ): Result<SendOtpResponseDto, rootError> =
        try {
            val response: HttpResponse =
                client.post(ApiRoutes.sendOtpToEmailEndpointFor(role)) {
                    contentType(ContentType.Application.Json)
                    setBody(sendOtpCodeRequest)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: SendOtpResponseDto = response.body()
                    Log.v("Send OTP Code:In Range 2xx", "Successfully Sent")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    val errorBodyText: String = response.bodyAsText()
                    Log.e("Send OTP Code: Out of Range 2xx", errorBody.message)
                    Log.e("Send OTP Code: Out of Range 2xx", errorBodyText)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Send OTP Code Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun resetPassword(
        resetPasswordRequestDto: ResetPasswordRequestDto,
        role: RoleDto
    ): Result<ResetPasswordResponseDto, rootError> =
        try {
            val response: HttpResponse =
                client.post(ApiRoutes.resetPasswordEndPointFor(role)) {
                    contentType(ContentType.Application.Json)
                    setBody(resetPasswordRequestDto)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: ResetPasswordResponseDto = response.body()
                    Log.v("Reset password: In Range 2xx", "Successfully Reset Password")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("Reset password: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Reset password Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun logout(
        token: String,
        role: RoleDto
    ): Result<LogoutResponseDto, rootError> =
        try {
            val response: HttpResponse =
                client.post(ApiRoutes.logoutEndPointFor(role)) {
                    contentType(ContentType.Application.Json)
                    bearerAuth(token)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: LogoutResponseDto = response.body()
                    Log.v("Logout: In Range 2xx", "Successfully Logged out ${responseBody.message}")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("Logout: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Logout Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

}