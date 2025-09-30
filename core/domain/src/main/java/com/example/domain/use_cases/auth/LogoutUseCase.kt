package com.example.domain.use_cases.auth

import com.example.domain.repositories.auth.AuthRepository
import com.example.model.auth.logout.LogoutRequest
import com.example.model.auth.logout.LogoutResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(logoutRequest: LogoutRequest): Result<LogoutResponse, NetworkError> {
        val result = authRepository.logout(logoutRequest = logoutRequest)
        return result
    }
}