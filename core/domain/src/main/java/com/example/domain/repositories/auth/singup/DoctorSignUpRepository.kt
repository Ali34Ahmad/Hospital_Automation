package com.example.domain.repositories.auth.singup

import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.model.auth.signup.DoctorRegistrationRequest
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface DoctorSignUpRepository {
    suspend fun signup(
        doctorRegistrationRequest: DoctorRegistrationRequest,
    ): Result<BaseRegistrationResponse, rootError>
}