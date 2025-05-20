package com.example.domain.use_cases.children

import com.example.domain.repositories.ChildRepository
import com.example.model.child.ChildFullData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

data class GetChildByIdUseCase(
    private val repository: ChildRepository
) {
    suspend operator fun invoke(childId: Int): Result<ChildFullData, NetworkError> {
         return repository.getChildById(childId)
    }
}