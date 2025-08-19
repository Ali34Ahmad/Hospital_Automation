package com.example.network.remote.auth.singup.doctor

import android.util.Log
import com.example.network.model.request.auth.signup.DoctorRegistrationRequestDto
import com.example.network.model.response.auth.signup.BaseRegistrationResponseDto
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType


class DoctorSignUpApiServiceImpl(
    private val client: HttpClient,
) : DoctorSignUpApiService {
    override suspend fun signup(doctorRegistrationRequestDto: DoctorRegistrationRequestDto): Result<BaseRegistrationResponseDto, rootError> =
        try {
            val response = client.post(ApiRoutes.getSingUpEndpointFor(doctorRegistrationRequestDto.role)) {
                contentType(ContentType.Application.Json)
                setBody(doctorRegistrationRequestDto)
            }

            when (response.status.value) {
                in 200..299 -> {
                    val doctorRegistrationResponseDto: BaseRegistrationResponseDto = response.body()
                    Log.v("Sign Up:", "Successfully signed up")
                    Result.Success(data = doctorRegistrationResponseDto)
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
}