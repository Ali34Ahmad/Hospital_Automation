package com.example.domain.di.domainModules

import com.example.domain.di.admin.clinic.adminClinicUseCases
import com.example.domain.di.admin.doctor.adminDoctorUseCases
import com.example.domain.di.admin.employee.adminEmployeeUseCases
import com.example.domain.di.admin.pharmacy.adminPharmacyUseCases
import org.koin.dsl.module

val adminDomainModule = module {
    includes(
        sharedDomainModule,
        adminDoctorUseCases,
        adminEmployeeUseCases,
        adminClinicUseCases,
        adminPharmacyUseCases
    )
}