package com.example.admin_profile.di

import com.example.admin_profile.presentation.AdminProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val adminProfileModule = module {
    viewModelOf(::AdminProfileViewModel)
}