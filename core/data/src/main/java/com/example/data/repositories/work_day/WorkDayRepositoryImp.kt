package com.example.data.repositories.work_day

import com.example.data.mapper.day_schedule.toWorkDaySummaryDto
import com.example.domain.repositories.WorkDayRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.doctor.workday.WorkDaySummaryData
import com.example.network.remote.workday.WorkDayApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

class WorkDayRepositoryImp(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val apiService: WorkDayApiService
): WorkDayRepository {
    override suspend fun createWorkDay(
        workDay: WorkDaySummaryData
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork {
            apiService.createWorkDay(
                workday = workDay.toWorkDaySummaryDto(),
                token = it,
            )
        }

    override suspend fun updateWorkDay(
        workDay: WorkDaySummaryData,
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork {
            apiService.updateWorkDay(
                token = it,
                workday = workDay.toWorkDaySummaryDto(),
                id = workDay.id
            )
        }

    override suspend fun deleteWorkDay(
        id: Int
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork {
            apiService.deleteWorkDay(
                token = it,
                id = id
            )
        }
}