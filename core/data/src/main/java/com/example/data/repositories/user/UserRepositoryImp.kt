package com.example.data.repositories.user

import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.user.toGuardianData
import com.example.data.mapper.user.toGuardianFullData
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.domain.repositories.UserRepository
import com.example.model.guardian.GuardianData
import com.example.model.guardian.GuardianFullData
import com.example.model.guardian.PagedData
import com.example.network.remote.user.UserApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.first


internal class UserRepositoryImp(
    private val userApiService: UserApiService,
    private val dataStore: UserPreferencesRepository
): UserRepository  {

    override suspend fun getGuardiansByName(
        page: Int,
        limit: Int,
        name: String
    ): Result<PagedData<GuardianData>, NetworkError> {
        val token: String? = dataStore.userPreferencesFlow.first().token
        if(token == null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)
        //token smart cast
        val response = userApiService.getUsersByName(
                token = token,
                page = page,
                limit = limit,
                name = name
            ).map{ usersResponse ->
                val guardians = usersResponse.data.map { user->
                    user.toGuardianData()
                }
                PagedData<GuardianData>(
                    page = usersResponse.pagination.page,
                    data = guardians
                )
            }
            return response
        }

    override suspend fun getGuardianById(id: Int): Result<GuardianFullData, NetworkError> {
//        val token: String? = dataStore.userPreferencesFlow.first().token
//        if(token == null)
//            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)
        val response = userApiService.getUserProfile(
            token = FAKE_TOKEN,
            id = id
        ).map { data->
            data.user.toGuardianFullData()
        }
        return response
    }

    override suspend fun addGuardianToChild(
        childId: Int,
        userId: Int,
    ): Result<Int, NetworkError> = userApiService.addGuardianToChild(
            userId = userId,
            childId = childId,
            token = FAKE_TOKEN
        ).map { data->
            data.childGuardianRelation.usersChildrenId
        }

}








