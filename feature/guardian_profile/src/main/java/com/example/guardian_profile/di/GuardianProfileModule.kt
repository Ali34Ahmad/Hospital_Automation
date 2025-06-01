package com.example.guardian_profile.di

import com.example.domain.use_cases.users.AddGuardianToChildUseCase
import com.example.domain.use_cases.users.GetGuardianByIdUseCase
import com.example.guardian_profile.presentation.GuardianProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val guardianProfileModule = module {
    singleOf(::AddGuardianToChildUseCase)
    singleOf(::GetGuardianByIdUseCase)
    viewModelOf(::GuardianProfileViewModel)
}