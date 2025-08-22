package com.example.domain.use_cases.users

import com.example.domain.repositories.UserRepository

data class DeactivateUserUseCase(
    private val repository: UserRepository
){
    suspend operator fun invoke(
        userId: Int,
        deactivationReason: String
    ) = repository.deactivateUser(
        userId = userId,
        deactivationReason = deactivationReason
    )
}
