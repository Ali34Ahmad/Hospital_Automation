package com.example.network.remote.auth

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.request.LoginRequestDto
import com.example.network.model.request.RegistrationRequestDto
import com.example.network.model.request.ResetPasswordRequestDto
import com.example.network.model.request.SendOtpRequestDto
import com.example.network.model.request.VerifyEmailOtpRequestDto
import com.example.network.model.response.LoginResponseDto
import com.example.network.model.response.LogoutResponseDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.RegistrationResponseDto
import com.example.network.model.response.ResetPasswordResponseDto
import com.example.network.model.response.SendOtpResponseDto
import com.example.network.model.response.VerifyEmailOtpResponseDto
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
import kotlinx.coroutines.flow.first

class AuthApiServiceImpl(
    private val client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
) : AuthApiService {
    override suspend fun signup(registrationRequestDto: RegistrationRequestDto): Result<RegistrationResponseDto, rootError> =
        try {
            val response = client.post(ApiRoutes.SIGNUP_EMPLOYEE) {
                contentType(ContentType.Application.Json)
                setBody(registrationRequestDto)
            }

            when (response.status.value) {
                in 200..299 -> {
                    val registrationResponseDto: RegistrationResponseDto = response.body()
                    Log.v("Sign Up:", "Successfully signed up")
                    Result.Success(data = registrationResponseDto)
                }

                else -> {
                    val errorBody = response.bodyAsText()
                    Log.e("Sign Up out of 2xx range:", errorBody)
                    Result.Error(error = NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Sign Up Exception:", e.message ?: "Unknown")
            Result.Error(error = NetworkError.UNKNOWN)
        }


    override suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginResponseDto, rootError> = try {
        val response: HttpResponse = client.post(ApiRoutes.LOGIN_EMPLOYEE) {
            contentType(ContentType.Application.Json)
            setBody(loginRequestDto)
        }
        when (response.status.value) {
            in 200..299 -> {
                Log.v("Login:", "Successfully logged in")
                val responseBody: LoginResponseDto = response.body()
                userPreferencesRepository.updateToken(responseBody.token)
                Log.v("MyToken",userPreferencesRepository.userPreferencesFlow.first().token.toString())
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

    override suspend fun verifyEmail(verifyEmailOtpRequestDto: VerifyEmailOtpRequestDto): Result<VerifyEmailOtpResponseDto, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.VERIFY_OTP) {
                contentType(ContentType.Application.Json)
                setBody(verifyEmailOtpRequestDto)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: VerifyEmailOtpResponseDto = response.body()
                    Log.v("Verify Email:", "Successfully verified ${verifyEmailOtpRequestDto.email} ${verifyEmailOtpRequestDto.otp}")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("Verify Email out of 200 range::", errorBody.message +"${verifyEmailOtpRequestDto.email} ${verifyEmailOtpRequestDto.otp}")
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Verify Email Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun sendOtpCodeToEmail(sendOtpCodeRequest: SendOtpRequestDto): Result<SendOtpResponseDto, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.SEND_OTP) {
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
                    Log.e("Send OTP Code: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Send OTP Code Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun resetPassword(resetPasswordRequestDto: ResetPasswordRequestDto): Result<ResetPasswordResponseDto, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.RESET_PASSWORD) {
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

    override suspend fun logout(): Result<LogoutResponseDto, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.LOGOUT_EMPLOYEE) {
                contentType(ContentType.Application.Json)
                val token=userPreferencesRepository.userPreferencesFlow.first().token
                if (token.isNullOrBlank()){
                    return@post
                }
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