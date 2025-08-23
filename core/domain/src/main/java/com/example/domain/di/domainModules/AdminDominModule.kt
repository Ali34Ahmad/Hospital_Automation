package com.example.domain.di.domainModules

import com.example.domain.di.admin.clinic.adminClinicUseCases
import com.example.domain.di.admin.doctor.adminDoctorUseCases
import com.example.domain.di.admin.employee.adminEmployeeUseCases
import com.example.domain.di.admin.pharmacy.adminPharmacyUseCases
import com.example.domain.di.auth.authUseCasesModule
import com.example.domain.di.auth.singup.baseSignUpModule
import com.example.domain.di.basic_account_creating.addResidentialAddressUseCaseModule
import com.example.domain.di.basic_account_creating.uploadProfileImageUseCasesModule
import com.example.domain.di.vaccine.vaccinesUseCasesModule
import com.example.domain.di.validatorUseCasesModule
import org.koin.dsl.module

val adminDomainModule = module {
    includes(
        baseSignUpModule,
        authUseCasesModule,
        validatorUseCasesModule,
        addResidentialAddressUseCaseModule,
        sharedDomainModule,
        adminDoctorUseCases,
        adminEmployeeUseCases,
        adminClinicUseCases,
        adminPharmacyUseCases,
        uploadProfileImageUseCasesModule,
        vaccinesUseCasesModule,
    )
}