package com.example.data.repositories.auth.signup

import com.example.data.mapper.auth.singup.toBaseRegistrationRequestDto
import com.example.data.mapper.auth.singup.toBaseRegistrationResponse
import com.example.domain.repositories.auth.singup.BaseSignUpRepository
import com.example.model.auth.signup.BaseRegistrationRequest
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.network.remote.auth.singup.generic.BaseSignUpApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class BaseSignUpRepositoryImpl(
    private val baseSignUpApiService: BaseSignUpApiService,
) : BaseSignUpRepository {
    override suspend fun signup(baseRegistrationRequest: BaseRegistrationRequest,
    ): Result<BaseRegistrationResponse, rootError> =
        baseSignUpApiService.signup(
            baseRegistrationRequestDto = baseRegistrationRequest.toBaseRegistrationRequestDto()
        ).map { registrationResponseDto ->
            registrationResponseDto.toBaseRegistrationResponse()
        }
}
