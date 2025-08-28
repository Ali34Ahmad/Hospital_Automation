package com.example.domain.di.domainModules

import com.example.domain.di.accountManagementUseCasesModule
import com.example.domain.di.appointments.appointmentUseCasesModule
import com.example.domain.di.children.childDomainModule
import com.example.domain.di.clinics.clinicsUseCasesModule
import com.example.domain.di.downloaderUseCaseModule
import com.example.domain.di.medicine.medicinesUseCasesModule
import com.example.domain.di.user.userDomainModule
import com.example.domain.di.userPreferencesUseCasesModule
import com.example.domain.di.validatorUseCasesModule
import com.example.domain.di.work_request.workRequestUseCasesModule
import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val sharedDomainModule = module {
    includes(
        userPreferencesUseCasesModule,
        accountManagementUseCasesModule,
        clinicsUseCasesModule,
        appointmentUseCasesModule,
        workRequestUseCasesModule,
        userDomainModule,
        childDomainModule,
        downloaderUseCaseModule,
        validatorUseCasesModule,
        medicinesUseCasesModule,
        )
    singleOf(::CheckEmployeePermissionUseCase)
}