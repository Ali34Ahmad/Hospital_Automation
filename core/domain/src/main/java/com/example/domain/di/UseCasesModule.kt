package com.example.domain.di

import org.koin.dsl.module

val  useCasesModule= module {
    includes(
        addResidentialAddressModule,
        getAdminProfileByIdUseCaseModule,
        authUseCasesModule,
        employeeAccountManagementUseCasesModule,
        employeeProfileUseCasesModule,
        employmentHistoryUseCasesModule,
        uploadEmployeeDocumentsUseCasesModule,
        uploadEmployeeProfileImageUseCasesModule,
    )
}