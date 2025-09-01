package com.example.admin_app.main

import com.example.model.enums.Role
import com.example.model.role_config.RoleAppConfig
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AppViewModel)
    factory { RoleAppConfig(role = Role.ADMIN) }
}