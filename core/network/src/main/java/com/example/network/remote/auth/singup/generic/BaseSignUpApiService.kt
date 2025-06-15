package com.example.network.remote.auth.singup.generic

import com.example.network.model.request.auth.signup.BaseRegistrationRequestDto
import com.example.network.model.response.auth.signup.BaseRegistrationResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface BaseSignUpApiService {
    suspend fun signup(
        baseRegistrationRequestDto: BaseRegistrationRequestDto,
    ): Result<BaseRegistrationResponseDto, rootError>
}