package com.example.network.remote.auth

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.request.LoginRequest
import com.example.network.model.request.RegistrationRequest
import com.example.network.model.request.ResetPasswordRequest
import com.example.network.model.request.SendOtpRequest
import com.example.network.model.request.VerifyEmailOtpRequest
import com.example.network.model.response.CheckEmployeePermissionResponse
import com.example.network.model.response.LoginResponse
import com.example.network.model.response.LogoutResponse
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.RegistrationResponse
import com.example.network.model.response.ResetPasswordResponse
import com.example.network.model.response.VerifyEmailOtpResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
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
    override suspend fun signup(registrationRequest: RegistrationRequest): Result<RegistrationResponse, rootError> =
        try {
            val response = client.post(ApiRoutes.SIGNUP_EMPLOYEE) {
                contentType(ContentType.Application.Json)
                setBody(registrationRequest)
            }

            when (response.status.value) {
                in 200..299 -> {
                    val registrationResponse: RegistrationResponse = response.body()
                    Log.v("Sign Up:", "Successfully signed up")
                    Result.Success(data = registrationResponse)
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


    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, rootError> = try {
        val response: HttpResponse = client.post(ApiRoutes.LOGIN_EMPLOYEE) {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }
        when (response.status.value) {
            in 200..299 -> {
                Log.v("Login:", "Successfully logged in")
                val responseBody: LoginResponse = response.body()
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

    override suspend fun verifyEmail(verifyEmailOtpRequest: VerifyEmailOtpRequest): Result<VerifyEmailOtpResponse, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.VERIFY_OTP) {
                contentType(ContentType.Application.Json)
                setBody(verifyEmailOtpRequest)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: VerifyEmailOtpResponse = response.body()
                    Log.v("Verify Email:", "Successfully verified ${verifyEmailOtpRequest.email} ${verifyEmailOtpRequest.otp}")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("Verify Email out of 200 range::", errorBody.message +"${verifyEmailOtpRequest.email} ${verifyEmailOtpRequest.otp}")
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Verify Email Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun sendOtpCodeToEmail(sendOtpCodeRequest: SendOtpRequest): Result<VerifyEmailOtpResponse, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.SEND_OTP) {
                contentType(ContentType.Application.Json)
                setBody(sendOtpCodeRequest)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: VerifyEmailOtpResponse = response.body()
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

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Result<ResetPasswordResponse, rootError> =
        try {
            val response: HttpResponse = client.post(ApiRoutes.RESET_PASSWORD) {
                contentType(ContentType.Application.Json)
                setBody(resetPasswordRequest)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: ResetPasswordResponse = response.body()
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

    override suspend fun checkEmployeePermission(): Result<CheckEmployeePermissionResponse, rootError> =
        try {
            val response: HttpResponse = client.get(ApiRoutes.CHECK_EMPLOYEE_PERMISSION) {
                contentType(ContentType.Application.Json)
                val token=userPreferencesRepository.userPreferencesFlow.first().token
                if (token.isNullOrBlank()){
                    return@get
                }
                bearerAuth(token)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: CheckEmployeePermissionResponse = response.body()
                    Log.v("Check Employee Permission: In Range 2xx", "Successfully Checked ${responseBody.permissionGranted}")
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: String = response.body()
                    Log.e("Check Employee Permission: Out of Range 2xx", errorBody)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Check Employee Permission Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun logout(): Result<LogoutResponse, rootError> =
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
                    val responseBody: LogoutResponse = response.body()
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