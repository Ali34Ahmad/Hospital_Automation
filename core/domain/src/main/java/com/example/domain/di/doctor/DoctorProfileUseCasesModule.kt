package com.example.domain.di.doctor

import com.example.domain.use_cases.doctor.profile.GetCurrentDoctorProfileUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val doctorProfileUseCasesModule = module {
    singleOf(::GetCurrentDoctorProfileUseCase)
}