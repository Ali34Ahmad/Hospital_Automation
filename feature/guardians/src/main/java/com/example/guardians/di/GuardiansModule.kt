package com.example.guardians.di

import com.example.domain.use_cases.users.GetGuardiansByChildIdUseCase
import com.example.guardians.presentation.GuardiansViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val guardiansModule = module {
    singleOf(::GetGuardiansByChildIdUseCase)
    viewModelOf(::GuardiansViewModel)
}