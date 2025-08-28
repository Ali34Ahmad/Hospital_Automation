package com.example.network.remote.work_request

import com.example.network.model.enums.RequestStateDto
import com.example.network.model.enums.RequestTypeDto
import com.example.network.model.response.vaccine.GetAllVaccinesResponseDto
import com.example.network.model.response.work_request.ChangeRequestStateResponseDto
import com.example.network.model.response.work_request.GetRequestsResponseDto
import com.example.network.model.response.work_request.WorkRequestResponseDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface WorkRequestApiService {
    suspend fun sendWorkRequest(
        token: String,
        clinicId: Int,
        requestType: RequestTypeDto
    ): Result<WorkRequestResponseDto, NetworkError>

    suspend fun getRequests(
        token: String,
        page: Int,
        limit: Int,
    ): Result<GetRequestsResponseDto, NetworkError>

    suspend fun changeWorkRequestState(
        token: String,
        requestId:Int,
        state: RequestStateDto,
    ): Result<ChangeRequestStateResponseDto,NetworkError>
}