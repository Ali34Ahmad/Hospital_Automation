package com.example.domain.use_cases.work_day

import com.example.domain.repositories.WorkDayRepository

class DeleteWorkDayUseCase(
    private val repository: WorkDayRepository
) {
    suspend operator fun invoke(
        id: Int
    ) = repository
        .deleteWorkDay(
            id = id
        )
}