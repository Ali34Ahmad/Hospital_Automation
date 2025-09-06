package com.example.network.remote.workday

import com.example.network.model.dto.workday.WorkDaySummaryDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface WorkDayApiService {
    suspend fun createWorkDay(
        token: String,
        workday: WorkDaySummaryDto,
    ): Result<Unit, NetworkError>

    suspend fun updateWorkDay(
        token: String,
        workday: WorkDaySummaryDto,
        id: Int
    ): Result<Unit, NetworkError>

    suspend fun deleteWorkDay(
        token: String,
        id: Int
    ): Result<Unit, NetworkError>
}