package com.example.employment_requests.di

import com.example.employment_requests.presentation.RequestsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employmentRequestsModule= module {
    viewModelOf(::RequestsViewModel)
}