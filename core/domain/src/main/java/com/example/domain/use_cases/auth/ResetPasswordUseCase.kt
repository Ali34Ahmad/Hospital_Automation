package com.example.domain.use_cases.auth

import com.example.domain.repositories.auth.AuthRepository
import com.example.model.auth.reset_password.ResetPasswordRequest
import com.example.model.auth.reset_password.ResetPasswordResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class ResetPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        resetPasswordRequest: ResetPasswordRequest
    ): Result<ResetPasswordResponse, NetworkError> {
        return authRepository.resetPassword(resetPasswordRequest)
    }
}