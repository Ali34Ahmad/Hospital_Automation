package com.example.domain.use_cases.auth.sing_up

import com.example.domain.repositories.AuthRepository
import com.example.domain.repositories.auth.singup.BaseSignUpRepository
import com.example.domain.repositories.auth.singup.DoctorSignUpRepository
import com.example.model.auth.signup.BaseRegistrationRequest
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.model.auth.signup.DoctorRegistrationRequest
import com.example.model.auth.signup.RegistrationResponse
import com.example.model.auth.signup.SignUpCredentials
import com.example.utility.network.Result
import com.example.utility.network.rootError

class DoctorSignupUseCase(
    private val doctorSingUpRepository: DoctorSignUpRepository
) {
    suspend operator fun invoke(
        doctorRegistrationRequest: DoctorRegistrationRequest
    ): Result<BaseRegistrationResponse, rootError> {
        return doctorSingUpRepository.signup(doctorRegistrationRequest)
    }
}