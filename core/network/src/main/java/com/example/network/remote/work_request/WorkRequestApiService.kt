package com.example.network.remote.work_request

import com.example.network.model.enums.RequestTypeDto
import com.example.network.model.response.work_request.WorkRequestResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface WorkRequestApiService {
    suspend fun sendWorkRequest(
        token: String,
        clinicId: Int,
        requestType: RequestTypeDto
    ): Result<WorkRequestResponse,NetworkError>
}