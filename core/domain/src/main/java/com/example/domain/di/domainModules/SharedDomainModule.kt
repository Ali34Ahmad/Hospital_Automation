package com.example.domain.di.domainModules

import com.example.domain.di.appointments.appointmentUseCasesModule
import com.example.domain.di.clinics.clinicsUseCasesModule
import com.example.domain.di.userPreferencesUseCasesModule
import com.example.domain.di.work_request.workRequestUseCasesModule
import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val sharedDomainModule = module {
    includes(
        userPreferencesUseCasesModule,
        //shared between doctor and admin
        clinicsUseCasesModule,
        appointmentUseCasesModule,
        workRequestUseCasesModule,
        )
    singleOf(::CheckEmployeePermissionUseCase)
}