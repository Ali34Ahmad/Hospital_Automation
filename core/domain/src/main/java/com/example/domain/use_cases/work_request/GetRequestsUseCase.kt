package com.example.domain.use_cases.work_request

import androidx.paging.PagingData
import com.example.domain.repositories.work_request.WorkRequestRepository
import com.example.model.work_request.SingleRequestResponse
import kotlinx.coroutines.flow.Flow

class GetRequestsUseCase(
    private val workRequestRepository: WorkRequestRepository,
) {
    suspend operator fun invoke(): Flow<PagingData<SingleRequestResponse>> {
        return workRequestRepository.getRequests()
    }
}