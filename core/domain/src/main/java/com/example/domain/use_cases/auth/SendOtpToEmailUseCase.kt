package com.example.domain.use_cases.auth

import com.example.domain.repositories.AuthRepository
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.send_otp.SendOtpResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class SendOtpToEmailUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        sendOtpCodeRequest: SendOtpRequest
    ): Result<SendOtpResponse, rootError> {
        return authRepository.sendOtpCodeToEmail(sendOtpCodeRequest)
    }
}