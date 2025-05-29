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
        // e.g., validate email format
        // if (!android.util.Patterns.EMAIL_ADDRESS.matcher(sendOtpCodeRequest.email).matches()) {
        //     return Result.Error(rootError(message = "Invalid email format"))
        // }
        return authRepository.sendOtpCodeToEmail(sendOtpCodeRequest)
    }
}