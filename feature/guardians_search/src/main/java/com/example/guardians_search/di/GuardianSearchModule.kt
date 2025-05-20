package com.example.guardians_search.di

import com.example.domain.use_cases.users.GetGuardiansByNameUseCase
import com.example.guardians_search.GuardiansSearchViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val guardiansSearchModule = module {
    viewModelOf(::GuardiansSearchViewModel)
    singleOf(::GetGuardiansByNameUseCase)
}