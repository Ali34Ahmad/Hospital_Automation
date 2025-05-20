package com.example.domain.use_cases.children

import com.example.domain.repositories.ChildRepository
import com.example.model.child.ChildFullData

data class AddChildUseCase(
    private val repository: ChildRepository
){
    suspend operator fun invoke(
        guardianId: Int,
        child: ChildFullData
        ) =
        repository.addChild(
            guardianId = guardianId,
            child = child
        )
}