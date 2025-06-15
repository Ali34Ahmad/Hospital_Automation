package com.example.domain.di.domainModules

import com.example.domain.di.basic_account_creating.addResidentialAddressUseCaseModule
import com.example.domain.di.auth.authUseCasesModule
import com.example.domain.di.auth.singup.baseSignUpModule
import com.example.domain.di.children.childDomainModule
import com.example.domain.di.downloaderUseCaseModule
import com.example.domain.di.employeeAccountManagementUseCasesModule
import com.example.domain.di.employeeProfileUseCasesModule
import com.example.domain.di.employmentHistoryUseCasesModule
import com.example.domain.di.getAdminProfileByIdUseCaseModule
import com.example.domain.di.basic_account_creating.uploadEmploymentDocumentsUseCasesModule
import com.example.domain.di.basic_account_creating.uploadEmployeeProfileImageUseCasesModule
import com.example.domain.di.user.userDomainModule
import com.example.domain.di.userPreferencesUseCasesModule
import com.example.domain.di.validatorUseCasesModule
import org.koin.dsl.module

val employeeDomainModule = module {
    includes(
        userDomainModule,
        childDomainModule,
        authUseCasesModule,
        baseSignUpModule,
        addResidentialAddressUseCaseModule,
        getAdminProfileByIdUseCaseModule,
        downloaderUseCaseModule,
        employeeAccountManagementUseCasesModule,
        employeeProfileUseCasesModule,
        employmentHistoryUseCasesModule,
        uploadEmploymentDocumentsUseCasesModule,
        uploadEmployeeProfileImageUseCasesModule,
        validatorUseCasesModule,

        userPreferencesUseCasesModule,
    )
}