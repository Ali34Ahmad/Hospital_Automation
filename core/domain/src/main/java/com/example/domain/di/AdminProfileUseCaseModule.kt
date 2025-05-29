package com.example.domain.di

import com.example.domain.use_cases.admin_profile.GetAdminProfileByIdUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val getAdminProfileByIdUseCaseModule= module {
    singleOf(::GetAdminProfileByIdUseCase)
}