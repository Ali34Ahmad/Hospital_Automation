package com.example.domain.use_cases.children

import com.example.domain.repositories.ChildRepository

data class SearchForChildrenAddedByEmployeeUseCase(
    private val repository: ChildRepository
){
    suspend operator fun invoke(name:String,page: Int,limit: Int) = repository
        .searchChildrenAddedByEmployeeByName(
            name = name,
            page = page,
            limit = limit
        )
}
