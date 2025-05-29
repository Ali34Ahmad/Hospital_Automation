package com.example.domain.use_cases.users

import com.example.domain.repositories.UserRepository

data class AddGuardianToChildUseCase(
    private val repository: UserRepository
){
    suspend operator fun invoke(childId: Int, userId: Int) = repository
        .addGuardianToChild(
            childId = childId,
            userId = userId
        )
}
