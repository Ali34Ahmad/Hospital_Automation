package com.example.data.repositories.child

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.child.toAddChildRequest
import com.example.data.mapper.child.toChildData
import com.example.data.mapper.child.toChildFullData
import com.example.data.source.childrenSearch.ChildrenByEmployeeSearchPagingSource
import com.example.data.source.childrenSearch.ChildrenSearchPagingSource
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.ChildRepository
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.guardian.PagedData
import com.example.network.remote.child.ChildApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ChildRepositoryImp(
    private val childApiService: ChildApiService,
    private val dataStore: UserPreferencesRepository
): ChildRepository {
    override suspend fun searchForChildrenByName(name: String): Flow<PagingData<ChildData>> {
        val token = dataStore.userPreferencesFlow.map { it.token }.first()

        return Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE
            ),
            pagingSourceFactory = { ChildrenSearchPagingSource(
                token = token,
                childApiService = childApiService,
                query = name,
            )}
        ).flow
    }

    override suspend fun getChildById(childId: Int): Result<ChildFullData, NetworkError> {
        val token = dataStore.userPreferencesFlow.first().token

        if (token==null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)

        return childApiService.getChildProfile(
            id = childId,
            token = token
        ).map { data->
            data.toChildFullData()
        }
    }

    override suspend fun addChild(
        guardianId: Int,
        child: ChildFullData,
    ): Result<ChildFullData, NetworkError> {
        val token = dataStore.userPreferencesFlow.first().token

        if (token==null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)

        return childApiService.addChild(
            token = token,
            guardianId = guardianId,
            child = child.toAddChildRequest()
        ).map {
            it.toChildFullData()
        }
    }


    override suspend fun getChildrenByGuardianId(guardianId: Int): Result<List<ChildFullData>, NetworkError> {
        val token = dataStore.userPreferencesFlow.first().token

        if (token==null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)

        val result  = childApiService.getChildrenByGuardianId(
            token = token,
            guardianId = guardianId
        ).map {
            it.data.map { it.child.toChildFullData() }
        }
        return result
    }

    override suspend fun searchForChildrenAddedByEmployeeByName(
        name: String
    ): Flow<PagingData<ChildData>> {
        val token = dataStore.userPreferencesFlow.map { it.token }.first()
        return Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE
            ),
            pagingSourceFactory = {
                ChildrenByEmployeeSearchPagingSource(
                    token = token,
                    query = name,
                    childApiService = childApiService
                )
            }
        ).flow
    }
}