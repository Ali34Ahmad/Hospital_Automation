package com.example.domain.di.appointment_type

import com.example.domain.use_cases.appointment_type.CreateAppointmentTypeUseCase
import com.example.domain.use_cases.appointment_type.DeleteAppointmentTypeUseCase
import com.example.domain.use_cases.appointment_type.UpdateAppointmentTypeUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val appointmentTypeUseCasesModule = module {
    singleOf(::CreateAppointmentTypeUseCase)
    singleOf(::UpdateAppointmentTypeUseCase)
    singleOf(::DeleteAppointmentTypeUseCase)
}