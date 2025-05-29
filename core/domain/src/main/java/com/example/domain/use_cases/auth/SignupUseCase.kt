package com.example.domain.use_cases.auth

import com.example.domain.repositories.AuthRepository
import com.example.model.auth.signup.RegistrationResponse
import com.example.model.auth.signup.SignUpCredentials
import com.example.utility.network.Result
import com.example.utility.network.rootError

class SignupUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        registrationRequest: SignUpCredentials
    ): Result<RegistrationResponse, rootError> {
        // You could add validation logic for SignUpCredentials here before calling the repository
        // For example:
        // if (registrationRequest.password.length < 8) {
        //     return Result.Error(rootError(message = "Password must be at least 8 characters"))
        // }
        return authRepository.signup(registrationRequest)
    }
}