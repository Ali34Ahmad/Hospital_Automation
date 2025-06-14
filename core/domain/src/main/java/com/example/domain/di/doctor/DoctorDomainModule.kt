package com.example.domain.di.doctor

import com.example.domain.use_cases.doctor.appointment.GetAppointmentsFlowUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val doctorModule = module {
    singleOf(::GetAppointmentsFlowUseCase)
}