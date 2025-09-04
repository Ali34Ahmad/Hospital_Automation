package com.example.data.repositories.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.user.toGuardianData
import com.example.data.mapper.user.toGuardianFullData
import com.example.data.paging_sources.guardian.GuardiansPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.UserRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.guardian.GuardianData
import com.example.model.guardian.GuardianFullData
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.user.UserApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.UpdatedIds
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first


internal class UserRepositoryImp(
    private val userApiService: UserApiService,
    private val userPreferences: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig
): UserRepository  {
    override suspend fun getGuardianById(
        id: Int
    ): Result<GuardianFullData, NetworkError> =
        userPreferences.executeWithValidTokenNetwork { token ->
            userApiService.getUserProfile(
                token = token,
                id = id,
                roleDto = roleAppConfig.role.toRoleDto()
            ).map { data->
                data.user.toGuardianFullData()
            }

    }

    override suspend fun deactivateUser(
        userId: Int,
        deactivationReason: String,
    ): Result<UpdatedIds, NetworkError> =
       userPreferences.executeWithValidTokenNetwork { token ->
           userApiService.deactivateUser(
               token = token,
               userId = userId,
               deactivationReason = deactivationReason
           ).map { it.updatedData }
       }

    override suspend fun reactivateUser(
        userId: Int
    ): Result<UpdatedIds, NetworkError> =
        userPreferences.executeWithValidTokenNetwork { token ->
            userApiService.reactivateUser(
                token = token,
                userId = userId
            ).map { it.updatedData }
        }

    override suspend fun addGuardianToChild(
        childId: Int,
        userId: Int,
    ): Result<Int, NetworkError> {
        val token: String? = userPreferences.userPreferencesDataStoreFlow.first().token
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

    override suspend fun getGuardiansByChildId(childId: Int): Result<List<GuardianData>, NetworkError> =
        userPreferences.executeWithValidTokenNetwork { token ->
            userApiService
                .getGuardiansByChildId(
                    token = token,
                    childId = childId,
                    roleDto = roleAppConfig.role.toRoleDto(),
                ).map { response ->
                    response.data.map { userDto ->
                        userDto.toGuardianData()
                    }
                }
        }

    override suspend fun getGuardiansByNamePagingData(
        query: String
    ): Flow<PagingData<GuardianData>> =
        userPreferences.executeFlowWithValidToken{ token->
         Pager(
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








