package com.example.domain.repositories

import com.example.model.guardian.GuardianData
import com.example.model.guardian.PagedData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result


interface UserRepository {
    suspend fun getGuardiansByName(
        page: Int,
        limit: Int,
        name: String
    ): Result<PagedData<GuardianData>, NetworkError>
}