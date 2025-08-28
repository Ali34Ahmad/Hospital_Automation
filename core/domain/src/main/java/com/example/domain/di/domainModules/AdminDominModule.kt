package com.example.domain.di.domainModules

import com.example.domain.di.accountManagementUseCasesModule
import com.example.domain.di.admin.clinic.adminClinicUseCases
import com.example.domain.di.admin.doctor.adminDoctorUseCases
import com.example.domain.di.admin.employee.adminEmployeeUseCases
import com.example.domain.di.admin.pharmacy.adminPharmacyUseCases
import com.example.domain.di.auth.authUseCasesModule
import com.example.domain.di.auth.singup.baseSignUpModule
import com.example.domain.di.basic_account_creating.addResidentialAddressUseCaseModule
import com.example.domain.di.basic_account_creating.uploadProfileImageUseCasesModule
import com.example.domain.di.doctor.doctorProfileUseCasesModule
import com.example.domain.di.employeeProfileUseCasesModule
import com.example.domain.di.employmentHistoryUseCasesModule
import com.example.domain.di.pharmacy.pharmacyUseCasesModule
import com.example.domain.di.prescription.prescriptionUseCasesModule
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
        accountManagementUseCasesModule,
        doctorProfileUseCasesModule,
        employeeProfileUseCasesModule,
        pharmacyUseCasesModule,
        employmentHistoryUseCasesModule,
        prescriptionUseCasesModule,
    )
}