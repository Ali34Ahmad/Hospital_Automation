package com.example.domain.use_cases.children

import com.example.domain.repositories.ChildRepository

data class GetChildrenByNameUseCase(
    private val repository: ChildRepository
){
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        name: String
    )=
        repository.getChildrenByName(
            page = page,
            limit = limit,
            name = name
        )

}