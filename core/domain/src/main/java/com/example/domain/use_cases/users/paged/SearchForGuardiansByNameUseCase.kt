package com.example.domain.use_cases.users.paged

import androidx.paging.PagingData
import com.example.domain.repositories.UserRepository
import com.example.model.guardian.GuardianData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchForGuardiansByNameUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<GuardianData>> =
            repository.getGuardiansByNamePagingData(
                query = query
            )

}