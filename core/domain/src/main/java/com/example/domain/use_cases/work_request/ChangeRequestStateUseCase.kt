package com.example.domain.use_cases.work_request

import androidx.paging.PagingData
import com.example.domain.repositories.work_request.WorkRequestRepository
import com.example.model.work_request.ChangeRequestStateResponse
import com.example.model.work_request.RequestState
import com.example.model.work_request.SingleRequestResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow

class ChangeRequestStateUseCase(
    private val workRequestRepository: WorkRequestRepository,
) {
    suspend operator fun invoke(
        requestId: Int,
        state: RequestState
    ): Result<ChangeRequestStateResponse, NetworkError> {
        return workRequestRepository.changeRequestState(requestId,state)
    }
}