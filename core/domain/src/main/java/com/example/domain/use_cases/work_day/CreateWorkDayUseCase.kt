package com.example.domain.use_cases.work_day

import com.example.domain.repositories.WorkDayRepository
import com.example.model.doctor.workday.WorkDaySummaryData

class CreateWorkDayUseCase(
    private val repository: WorkDayRepository
) {
    suspend operator fun invoke(
        workDay: WorkDaySummaryData
    ) = repository
        .createWorkDay(
            workDay = workDay
        )
}