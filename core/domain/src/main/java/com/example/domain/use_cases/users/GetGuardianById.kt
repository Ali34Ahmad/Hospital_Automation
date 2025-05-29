package com.example.domain.use_cases.users

import com.example.domain.repositories.UserRepository

data class GetGuardianByIdUseCase(
    private val repository: UserRepository
){
    suspend operator fun invoke(id: Int) = repository.getGuardianById(id)
}
