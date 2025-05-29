package com.example.domain.repositories

import com.example.model.guardian.GuardianData
import com.example.model.guardian.GuardianFullData
import com.example.model.guardian.PagedData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result


interface UserRepository {
    suspend fun getGuardiansByName(
        page: Int,
        limit: Int,
        name: String
    ): Result<PagedData<GuardianData>, NetworkError>

    suspend fun getGuardianById(
        id: Int
    ): Result<GuardianFullData, NetworkError>

    /**
     * this network call will add a guardian to the child,
     * and return the id of the relation.
     * @author Ali Mansoura
     */
    suspend fun addGuardianToChild(
        childId: Int,
        userId: Int
    ): Result<Int, NetworkError>
}