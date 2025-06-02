package com.example.admin_profile

import com.example.admin_profile.main.AdminProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val adminProfileModule = module {
    viewModelOf(::AdminProfileViewModel)
}