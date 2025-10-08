package com.example.domain.repositories

import com.example.model.doctor.workday.WorkDaySummaryData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface WorkDayRepository {
    suspend fun createWorkDay(
        workDay: WorkDaySummaryData,
    ): Result<Unit, NetworkError>

    suspend fun updateWorkDay(
        workDay: WorkDaySummaryData,
    ): Result<Unit, NetworkError>

    suspend fun deleteWorkDay(
        id: Int
    ): Result<Unit, NetworkError>
}
