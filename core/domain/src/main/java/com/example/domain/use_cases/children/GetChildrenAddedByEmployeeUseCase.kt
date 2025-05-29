package com.example.domain.use_cases.children

import com.example.domain.repositories.ChildRepository

data class GetChildrenAddedByEmployeeUseCase(
    private val repository: ChildRepository
){
    suspend operator fun invoke(
        page: Int,
        limit: Int
    ) = repository.getChildrenAddedByEmployee(page, limit)
}
