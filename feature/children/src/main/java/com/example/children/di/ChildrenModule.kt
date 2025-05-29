package com.example.children.di

import com.example.children.presentation.ChildrenViewModel
import com.example.data.source.ChildrenAddedByEmployeePagingSource
import com.example.domain.use_cases.children.GetChildrenAddedByEmployeeUseCase
import com.example.domain.use_cases.children.GetChildrenByGuardianIdUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val childrenModule = module {
    singleOf(::GetChildrenAddedByEmployeeUseCase)
    singleOf(::GetChildrenByGuardianIdUseCase)
    viewModelOf(::ChildrenViewModel)
}