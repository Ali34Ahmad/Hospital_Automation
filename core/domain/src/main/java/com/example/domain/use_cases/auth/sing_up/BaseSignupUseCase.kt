package com.example.domain.use_cases.auth.sing_up

import com.example.domain.repositories.auth.AuthRepository
import com.example.model.auth.signup.BaseRegistrationRequest
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class BaseSignupUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        baseRegistrationRequest: BaseRegistrationRequest
    ): Result<BaseRegistrationResponse, NetworkError> {
        return authRepository.generalSignup(baseRegistrationRequest)
    }
}