package com.example.domain.use_cases.children.paged

import com.example.domain.repositories.ChildRepository

data class SearchForChildrenAddedByEmployeeByNameUseCase(
    private val childRepository: ChildRepository
){
    suspend operator fun invoke(
        query : String,
    ) =
        childRepository.searchForChildrenAddedByEmployeeByName(
            name = query
        )

}
