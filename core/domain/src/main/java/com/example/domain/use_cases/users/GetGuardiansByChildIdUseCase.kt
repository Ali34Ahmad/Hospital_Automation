package com.example.domain.use_cases.users

import com.example.domain.repositories.UserRepository

data class GetGuardiansByChildIdUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        childId: Int
    ) = repository
        .getGuardiansByChildId(
            childId = childId
        )
}