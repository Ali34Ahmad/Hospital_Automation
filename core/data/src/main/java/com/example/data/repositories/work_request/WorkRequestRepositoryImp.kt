package com.example.data.repositories.work_request

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.work_request.toChangeRequestStateResponse
import com.example.data.mapper.work_request.toRequestStateDto
import com.example.data.mapper.work_request.toRequestTypeDto
import com.example.data.mapper.work_request.toWorkRequestData
import com.example.data.paging_sources.work_request.WorkRequestResponsePagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.work_request.WorkRequestRepository
import com.example.domain.use_cases.work_request.ChangeRequestStateUseCase
import com.example.model.work_request.ChangeRequestStateResponse
import com.example.model.work_request.RequestState
import com.example.model.work_request.SingleRequestResponse
import com.example.model.work_request.RequestType
import com.example.model.work_request.WorkRequestData
import com.example.network.model.response.work_request.ChangeRequestStateResponseDto
import com.example.network.model.response.work_request.WorkRequestResponseDto
import com.example.network.remote.work_request.WorkRequestApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow

data class WorkRequestRepositoryImp(
    private val apiService: WorkRequestApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : WorkRequestRepository {
    override suspend fun sendWorkRequest(
        requestType: RequestType,
        clinicId: Int,
    ): Result<WorkRequestData, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork { token ->
            apiService.sendWorkRequest(
                token = token,
                clinicId = clinicId,
                requestType = requestType.toRequestTypeDto()
            ).map { response: WorkRequestResponseDto ->
                response.request.toWorkRequestData()
            }
        }

    override suspend fun getRequests(): Flow<PagingData<SingleRequestResponse>> =
        userPreferencesRepository.executeFlowWithValidToken { token ->
            Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE
                ),
                pagingSourceFactory = {
                    WorkRequestResponsePagingSource(
                        token = token,
                        requestApi = apiService
                    )
                }
            ).flow
        }

    override suspend fun changeRequestState(
        requestId: Int,
        state: RequestState
    ): Result<ChangeRequestStateResponse, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork { token ->
            apiService.changeWorkRequestState(
                token = token,
                requestId = requestId,
                state = state.toRequestStateDto()
            ).map { response: ChangeRequestStateResponseDto ->
                response.toChangeRequestStateResponse()
            }
        }

}
