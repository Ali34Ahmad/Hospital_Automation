package com.example.domain.di.work_day

import com.example.domain.use_cases.work_day.CreateWorkDayUseCase
import com.example.domain.use_cases.work_day.DeleteWorkDayUseCase
import com.example.domain.use_cases.work_day.UpdateWorkDayUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val workDayUseCasesModule = module {
    singleOf(::CreateWorkDayUseCase)
    singleOf(::UpdateWorkDayUseCase)
    singleOf(::DeleteWorkDayUseCase)
}