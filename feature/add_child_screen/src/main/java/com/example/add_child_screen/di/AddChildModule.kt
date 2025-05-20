package com.example.add_child_screen.di

import com.example.add_child_screen.AddChildViewModel
import com.example.domain.use_cases.children.AddChildUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val addChildModule = module {
    singleOf(::AddChildUseCase)
    viewModelOf(::AddChildViewModel)
}