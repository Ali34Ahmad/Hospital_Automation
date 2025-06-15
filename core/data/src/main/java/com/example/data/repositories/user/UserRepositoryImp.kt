package com.example.data.repositories.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.user.toGuardianData
import com.example.data.mapper.user.toGuardianFullData
import com.example.data.source.GuardiansPagingSource
import com.example.datastore.service.UserPreferencesService
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.UserRepository
import com.example.model.guardian.GuardianData
import com.example.model.guardian.GuardianFullData
import com.example.network.remote.user.UserApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first


internal class UserRepositoryImp(
    private val userApiService: UserApiService,
    private val dataStore: UserPreferencesService,
): UserRepository  {


    override suspend fun getGuardianById(id: Int): Result<GuardianFullData, NetworkError> {
        val token: String? = dataStore.userPreferencesDataStoreFlow.first().token
        if(token == null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)

        val response = userApiService.getUserProfile(
            token = token,
            id = id
        ).map { data->
            data.user.toGuardianFullData()
        }
        return response
    }

    override suspend fun addGuardianToChild(
        childId: Int,
        userId: Int,
    ): Result<Int, NetworkError> {
        val token: String? = dataStore.userPreferencesDataStoreFlow.first().token
        if(token == null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)
        return userApiService.addGuardianToChild(
            userId = userId,
            childId = childId,
            token = token
        ).map { data->
            data.childGuardianRelation.usersChildrenId
        }
    }

    override suspend fun getGuardiansByChildId(childId: Int): Result<List<GuardianData>, NetworkError> {
        val token: String? = dataStore.userPreferencesDataStoreFlow.first().token
        if(token == null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)

        return userApiService
            .getGuardiansByChildId(
                token = token,
                childId = childId
            ).map { response ->
                response.data.map { userDto ->
                    userDto.toGuardianData()
                }
            }
    }

    override suspend fun getGuardiansByNamePagingData(query: String): Flow<PagingData<GuardianData>> {
        val token = dataStore.userPreferencesDataStoreFlow.first().token
        return Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                GuardiansPagingSource(
                    userApiService = userApiService,
                    query = query,
                    token = token
                )
            }
        ).flow
    }



}








