package com.example.network.remote.auth.singup.doctor

import com.example.network.model.request.auth.signup.DoctorRegistrationRequestDto
import com.example.network.model.response.auth.signup.BaseRegistrationResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface DoctorSignUpApiService {
    suspend fun signup(
        doctorRegistrationRequestDto: DoctorRegistrationRequestDto,
    ): Result<BaseRegistrationResponseDto, rootError>
}