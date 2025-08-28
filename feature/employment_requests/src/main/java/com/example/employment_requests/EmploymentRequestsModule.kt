package com.example.employment_requests

import com.example.employment_requests.main.RequestsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val employmentRequestsModule= module {
    viewModelOf(::RequestsViewModel)
}