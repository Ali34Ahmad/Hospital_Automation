package com.example.children_search.di

import com.example.children_search.presentation.ChildrenSearchViewModel
import com.example.domain.use_cases.children.GetChildrenByNameUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val childrenSearchModule = module {
    singleOf(::GetChildrenByNameUseCase)
    viewModelOf(::ChildrenSearchViewModel)
}