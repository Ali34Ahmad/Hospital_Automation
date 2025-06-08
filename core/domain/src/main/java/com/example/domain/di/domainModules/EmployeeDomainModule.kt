package com.example.domain.di.domainModules

import com.example.domain.di.addResidentialAddressUseCaseModule
import com.example.domain.di.authUseCasesModule
import com.example.domain.di.children.childDomainModule
import com.example.domain.di.downloaderUseCaseModule
import com.example.domain.di.employeeAccountManagementUseCasesModule
import com.example.domain.di.employeeProfileUseCasesModule
import com.example.domain.di.employmentHistoryUseCasesModule
import com.example.domain.di.getAdminProfileByIdUseCaseModule
import com.example.domain.di.uploadEmployeeDocumentsUseCasesModule
import com.example.domain.di.uploadEmployeeProfileImageUseCasesModule
import com.example.domain.di.user.userDomainModule
import com.example.domain.di.validatorUseCasesModule
import org.koin.dsl.module

val employeeDomainModule = module {
    includes(
        userDomainModule,
        childDomainModule,
        authUseCasesModule,
        addResidentialAddressUseCaseModule,
        getAdminProfileByIdUseCaseModule,
        downloaderUseCaseModule,
        employeeAccountManagementUseCasesModule,
        employeeProfileUseCasesModule,
        employmentHistoryUseCasesModule,
        uploadEmployeeDocumentsUseCasesModule,
        uploadEmployeeProfileImageUseCasesModule,
        validatorUseCasesModule,
    )
}