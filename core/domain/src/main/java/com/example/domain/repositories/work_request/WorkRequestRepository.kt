package com.example.domain.repositories.work_request

import androidx.paging.PagingData
import com.example.model.work_request.ChangeRequestStateResponse
import com.example.model.work_request.RequestState
import com.example.model.work_request.SingleRequestResponse
import com.example.model.work_request.RequestType
import com.example.model.work_request.WorkRequestData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow

interface WorkRequestRepository {
    suspend fun sendWorkRequest(
        requestType: RequestType,
        clinicId: Int,
    ): Result<WorkRequestData, NetworkError>

    suspend fun getRequests(): Flow<PagingData<SingleRequestResponse>>

    suspend fun changeRequestState(
        requestId: Int,
        state: RequestState
    ): Result<ChangeRequestStateResponse, NetworkError>
}