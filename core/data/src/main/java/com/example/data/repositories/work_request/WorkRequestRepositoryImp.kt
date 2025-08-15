package com.example.data.repositories.work_request

import com.example.data.mapper.work_request.toRequestTypeDto
import com.example.data.mapper.work_request.toWorkRequestData
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.work_request.WorkRequestRepository
import com.example.model.work_request.RequestType
import com.example.model.work_request.WorkRequestData
import com.example.network.model.response.work_request.WorkRequestResponse
import com.example.network.remote.work_request.WorkRequestApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map

data class WorkRequestRepositoryImp(
    private val apiService: WorkRequestApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
): WorkRequestRepository{
    override suspend fun sendWorkRequest(
        requestType: RequestType,
        clinicId: Int,
    ): Result<WorkRequestData, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork { token->
            apiService.sendWorkRequest(
                token = token,
                clinicId = clinicId,
                requestType = requestType.toRequestTypeDto()
            ).map {response : WorkRequestResponse ->
                response.request.toWorkRequestData()
            }
        }

}
