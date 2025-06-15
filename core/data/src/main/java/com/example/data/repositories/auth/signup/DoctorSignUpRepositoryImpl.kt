package com.example.data.repositories.auth.signup

import com.example.data.mapper.auth.singup.toBaseRegistrationResponse
import com.example.data.mapper.auth.singup.toDoctorRegistrationRequestDto
import com.example.domain.repositories.auth.singup.DoctorSignUpRepository
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.model.auth.signup.DoctorRegistrationRequest
import com.example.network.remote.auth.singup.doctor.DoctorSignUpApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class DoctorSignUpRepositoryImpl(
    private val doctorSignUpApiService: DoctorSignUpApiService,
) : DoctorSignUpRepository {
    override suspend fun signup(doctorRegistrationRequest: DoctorRegistrationRequest,
    ): Result<BaseRegistrationResponse, rootError> =
        doctorSignUpApiService.signup(
            doctorRegistrationRequestDto = doctorRegistrationRequest.toDoctorRegistrationRequestDto()
        ).map { registrationResponseDto ->
            registrationResponseDto.toBaseRegistrationResponse()
        }
}
