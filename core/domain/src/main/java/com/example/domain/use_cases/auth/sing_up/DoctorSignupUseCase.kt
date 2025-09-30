package com.example.domain.use_cases.auth.sing_up

import com.example.domain.repositories.auth.AuthRepository
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.model.auth.signup.DoctorRegistrationRequest
import com.example.utility.network.Result
import com.example.utility.network.NetworkError

class DoctorSignupUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        doctorRegistrationRequest: DoctorRegistrationRequest
    ): Result<BaseRegistrationResponse, NetworkError> {
        return authRepository.doctorSignup(doctorRegistrationRequest)
    }
}