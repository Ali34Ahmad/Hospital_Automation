package com.example.admin_profile

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val adminProfileModule = module {
    viewModelOf(::AdminProfileViewModel)
}