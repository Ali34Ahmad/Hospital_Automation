package com.example.children_search.di

import com.example.children_search.presentation.ChildrenSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val childrenSearchModule = module {
    viewModelOf(::ChildrenSearchViewModel)
}