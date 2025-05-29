package com.example.domain.use_cases.auth

import com.example.domain.repositories.AuthRepository
import com.example.model.auth.reset_password.ResetPasswordRequest
import com.example.model.auth.reset_password.ResetPasswordResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class ResetPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        resetPasswordRequest: ResetPasswordRequest
    ): Result<ResetPasswordResponse, rootError> {
        // e.g., ensure new password and confirm password match, and meet complexity rules
        // if (resetPasswordRequest.newPassword != resetPasswordRequest.confirmPassword) {
        //    return Result.Error(rootError(message = "Passwords do not match"))
        // }
        return authRepository.resetPassword(resetPasswordRequest)
    }
}