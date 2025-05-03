package com.example.network.remote.auth

import com.example.network.model.request.LoginRequest
import com.example.network.model.request.RegistrationRequest
import com.example.network.model.request.VerifyEmailOtpRequest
import com.example.network.model.response.LoginResponse
import com.example.network.model.response.RegistrationResponse
import com.example.network.model.response.VerifyEmailOtpResponse
import com.example.network.utility.Resource

interface AuthApiService {
    suspend fun signup(
        registrationRequest: RegistrationRequest
    ):Resource<RegistrationResponse>

    suspend fun login(
        loginRequest: LoginRequest
    ):Resource<LoginResponse>

    suspend fun verifyEmail(
        verifyEmailOtpRequest: VerifyEmailOtpRequest
    ):Resource<VerifyEmailOtpResponse>
}