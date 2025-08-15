package com.example.domain.di.admin.doctor

import com.example.domain.use_cases.admin.doctor.GetDoctorsByClinicUseCase
import com.example.domain.use_cases.admin.doctor.GetDoctorsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val adminDoctorUseCases = module {
    singleOf(::GetDoctorsUseCase)
    singleOf(::GetDoctorsByClinicUseCase)
}