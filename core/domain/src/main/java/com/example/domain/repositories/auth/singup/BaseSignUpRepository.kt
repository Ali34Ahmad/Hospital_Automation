package com.example.domain.repositories.auth.singup

import com.example.model.auth.signup.BaseRegistrationRequest
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface BaseSignUpRepository {
    suspend fun signup(
        baseRegistrationRequest: BaseRegistrationRequest,
    ): Result<BaseRegistrationResponse, rootError>
}