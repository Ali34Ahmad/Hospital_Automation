package com.example.domain.use_cases.auth

import com.example.domain.repositories.auth.AuthRepository
import com.example.model.auth.login.LoginRequest
import com.example.model.auth.login.LoginResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        loginRequest: LoginRequest
    ): Result<LoginResponse, rootError> {
        return authRepository.login(loginRequest)
    }
}