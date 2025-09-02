package com.example.domain.di.domainModules

import com.example.domain.di.auth.authUseCasesModule
import com.example.domain.di.auth.singup.baseSignUpModule
import com.example.domain.di.basic_account_creating.addResidentialAddressUseCaseModule
import com.example.domain.di.basic_account_creating.uploadEmploymentDocumentsUseCasesModule
import com.example.domain.di.basic_account_creating.uploadProfileImageUseCasesModule
import com.example.domain.di.employeeProfileUseCasesModule
import com.example.domain.di.employmentHistoryUseCasesModule
import com.example.domain.di.getAdminProfileByIdUseCaseModule
import org.koin.dsl.module

val employeeDomainModule = module {
    includes(
        domainModule,
        authUseCasesModule,
        baseSignUpModule,
        addResidentialAddressUseCaseModule,
        getAdminProfileByIdUseCaseModule,
        employeeProfileUseCasesModule,
        employmentHistoryUseCasesModule,
        uploadEmploymentDocumentsUseCasesModule,
        uploadProfileImageUseCasesModule,
    )
}