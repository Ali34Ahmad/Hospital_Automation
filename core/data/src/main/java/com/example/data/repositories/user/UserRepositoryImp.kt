package com.example.data.repositories.user

import com.example.data.mapper.user.toGuardianData
import com.example.datastore.repositories.UserPreferenceRepository
import com.example.domain.repositories.UserRepository
import com.example.model.guardian.GuardianData
import com.example.model.guardian.PagedData
import com.example.network.remote.user.UserApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.collectLatest


internal class UserRepositoryImp(
    private val userApiService: UserApiService,
    private val dataStore: UserPreferenceRepository
): UserRepository  {

    override suspend fun getGuardiansByName(
        page: Int,
        limit: Int,
        name: String
    ): Result<PagedData<GuardianData>, NetworkError> {
        var token: String? = null
        dataStore.userPreferencesFlow.collectLatest{data->
            token = data.token
        }
        val response = userApiService.getUsersByName(
                token = token?:"",
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
}