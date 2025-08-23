package com.example.data.repositories.child

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.child.toAddChildRequest
import com.example.data.mapper.child.toChildFullData
import com.example.data.mapper.enums.toRoleDto
import com.example.data.paging_sources.childrenSearch.ChildrenByEmployeeSearchPagingSource
import com.example.data.paging_sources.childrenSearch.ChildrenSearchPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.ChildRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.child.ChildApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow

class ChildRepositoryImp(
    private val childApiService: ChildApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig
): ChildRepository {
    override suspend fun searchForChildrenByName(
        name: String,
    ): Flow<PagingData<ChildData>> =
        userPreferencesRepository.executeFlowWithValidToken { token ->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE
                ),
                pagingSourceFactory = {
                    ChildrenSearchPagingSource(
                        token = token,
                        childApiService = childApiService,
                        query = name,
                    )
                }
            ).flow
        }

    override suspend fun getChildById(childId: Int): Result<ChildFullData, NetworkError> =

        childApiService.getChildProfile(
            id = childId,
            token = FAKE_TOKEN,
            role = roleAppConfig.role.toRoleDto()
        ).map { data->
            data.toChildFullData()
        }
//        userPreferencesRepository.executeWithValidTokenNetwork { token->
//        }

    override suspend fun addChild(
        guardianId: Int,
        child: ChildFullData,
    ): Result<ChildFullData, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork { token->
            childApiService.addChild(
                token = token,
                guardianId = guardianId,
                child = child.toAddChildRequest()
            ).map {
                it.toChildFullData()
            }
        }


    override suspend fun getChildrenByGuardianId(
        guardianId: Int,
    ): Result<List<ChildFullData>, NetworkError> =
      //  userPreferencesRepository.executeWithValidTokenNetwork { token->
             childApiService.getChildrenByGuardianId(
                token = FAKE_TOKEN,
                guardianId = guardianId,
                 role = roleAppConfig.role.toRoleDto()
            ).map { response->
                response.data.map { it.child.toChildFullData()  }
            }
        //}

    override suspend fun searchForChildrenAddedByEmployeeByName(
        name: String,
        employeeId: Int?
    ): Flow<PagingData<ChildData>> =
        //userPreferencesRepository.executeFlowWithValidToken {token->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE
                ),
                pagingSourceFactory = {
                    ChildrenByEmployeeSearchPagingSource(
                        token = FAKE_TOKEN,
                        query = name,
                        childApiService = childApiService,
                        roleAppConfig = roleAppConfig,
                        employeeId = employeeId
                    )
                }
            ).flow
        //}


}