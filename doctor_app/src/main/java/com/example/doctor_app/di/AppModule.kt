package com.example.doctor_app.di

import com.example.model.enums.Role
import com.example.model.role_config.RoleAppConfig
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val appModule = module {
    single{ RoleAppConfig(
        role = Role.DOCTOR
    ) }
}