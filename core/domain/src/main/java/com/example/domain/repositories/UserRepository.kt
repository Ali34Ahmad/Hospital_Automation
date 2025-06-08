package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.model.guardian.GuardianData
import com.example.model.guardian.GuardianFullData
import com.example.model.guardian.PagedData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow


interface UserRepository {

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

    /**
     * Returns the responsible guardians for a given child.
     * @param childId: The id of the child who you ask for his guardians.
     * @author Ali Mansoura.
     */
    suspend fun getGuardiansByChildId(
        childId: Int
    ): Result<List<GuardianData>, NetworkError>


    suspend fun getGuardiansByNamePagingData(query: String): Flow<PagingData<GuardianData>>

}