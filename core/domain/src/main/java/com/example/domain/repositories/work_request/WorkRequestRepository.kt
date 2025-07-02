package com.example.domain.repositories.work_request

import com.example.model.work_request.RequestType
import com.example.model.work_request.WorkRequestData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface WorkRequestRepository {
    suspend fun sendWorkRequest(
        requestType: RequestType,
        clinicId: Int,
    ): Result<WorkRequestData,NetworkError>
}