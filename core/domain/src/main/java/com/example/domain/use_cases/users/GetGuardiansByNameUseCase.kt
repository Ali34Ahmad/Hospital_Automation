package com.example.domain.use_cases.users

import com.example.domain.repositories.UserRepository

data class GetGuardiansByNameUseCase(
    private val userRepository : UserRepository
) {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        name: String
    ) =
        userRepository.getGuardiansByName(
            page = page,
            limit = limit,
            name = name
        )

}