package com.example.employees_search.di

import com.example.employees_search.presentation.EmployeeSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employeesSearchModule = module {
    viewModelOf(::EmployeeSearchViewModel)
}