package com.example.domain.use_cases.children.paged

import com.example.domain.repositories.ChildRepository

data class SearchForChildrenByNameUseCase(
    val childRepository: ChildRepository
){
    suspend operator fun invoke(name: String) =
        childRepository.searchForChildrenByName(name)
}
