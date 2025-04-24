package com.example.network.remote.auth

import com.example.network.model.request.LoginRequest
import com.example.network.model.request.RegistrationRequest
import com.example.network.model.request.VerifyEmailOtpRequest
import com.example.network.model.response.LoginResponse
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.RegistrationResponse
import com.example.network.model.response.VerifyEmailOtpResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AuthApiServiceImpl(
    private val client: HttpClient
) : AuthApiService {
    override suspend fun signup(registrationRequest: RegistrationRequest): Resource<RegistrationResponse> = try {
            val response = client.post(ApiRoutes.SIGNUP_EMPLOYEE) {
                contentType(ContentType.Application.Json)
                setBody(registrationRequest)
            }

            when (response.status) {
                HttpStatusCode.OK, HttpStatusCode.Created -> {
                    val registrationResponse: RegistrationResponse = response.body()
                    Resource.Success(data = registrationResponse)
                }

                else -> {
                    val errorBody = response.body<NetworkMessage>()
                    Resource.Error(message = errorBody.message)
                }
            }
        } catch (e: Exception) {
            Resource.Error(message = "Network error during signup: ${e.localizedMessage}")
        }


    override suspend fun login(loginRequest: LoginRequest): Resource<LoginResponse> = try {
        val response: HttpResponse = client.post(ApiRoutes.LOGIN_EMPLOYEE) {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }
        when (response.status) {
            HttpStatusCode.OK -> {
                val loginResponse: LoginResponse = response.body()
                Resource.Success(data = loginResponse)
            }

            else -> {
                val errorBody: NetworkMessage = response.body()
                Resource.Error(message = errorBody.message)
            }
        }

    } catch (e: Exception) {
        Resource.Error(message = "Network error during login: ${e.localizedMessage}")
    }

    override suspend fun verifyEmail(verifyEmailOtpRequest: VerifyEmailOtpRequest): Resource<VerifyEmailOtpResponse> =try{
        val response: HttpResponse = client.post(ApiRoutes.VERIFY_OTP) {
            contentType(ContentType.Application.Json)
            setBody(verifyEmailOtpRequest)
        }
        when (response.status) {
            HttpStatusCode.OK -> {
                val verifyEmailOtpResponse: VerifyEmailOtpResponse = response.body()
                Resource.Success(data = verifyEmailOtpResponse)
            }

            else -> {
                val errorBody: NetworkMessage = response.body()
                Resource.Error(message = errorBody.message)

            }
        }
    }catch (e:Exception){
        Resource.Error(message = "Network error during OTP verification: ${e.localizedMessage}")
    }

}