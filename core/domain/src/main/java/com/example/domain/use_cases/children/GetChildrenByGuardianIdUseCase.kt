package com.example.domain.use_cases.children

import com.example.domain.repositories.ChildRepository

data class GetChildrenByGuardianIdUseCase(
    private val repository: ChildRepository
){
    suspend operator fun invoke(
        guardianId: Int
    ) = repository.getChildrenByGuardianId(guardianId)
}
