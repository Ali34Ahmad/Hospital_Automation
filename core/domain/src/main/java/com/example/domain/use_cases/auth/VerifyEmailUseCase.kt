package com.example.domain.use_cases.auth

import com.example.domain.repositories.AuthRepository
import com.example.model.auth.verify_otp.VerifyEmailOtpRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class VerifyEmailUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        verifyEmailOtpRequest: VerifyEmailOtpRequest
    ): Result<VerifyEmailOtpResponse, rootError> {
        return authRepository.verifyEmail(verifyEmailOtpRequest)
    }
}