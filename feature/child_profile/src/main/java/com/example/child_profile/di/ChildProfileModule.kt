package com.example.child_profile.di

import com.example.child_profile.presentation.ChildProfileViewModel
import com.example.domain.use_cases.children.GetChildByIdUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val childProfileModule = module {
    viewModelOf(::ChildProfileViewModel)
}