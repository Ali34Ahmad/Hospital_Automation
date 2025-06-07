package com.example.guardians_search.di

import com.example.guardians_search.presentation.GuardiansSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val guardiansSearchModule = module {
    viewModelOf(::GuardiansSearchViewModel)
}