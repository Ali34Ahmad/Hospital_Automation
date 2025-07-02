package com.example.domain.di.work_request

import com.example.domain.use_cases.work_request.SendDoctorWorkRequestUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val workRequestUseCasesModule = module {
    singleOf(::SendDoctorWorkRequestUseCase)
}