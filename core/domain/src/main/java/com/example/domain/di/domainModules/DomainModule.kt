package com.example.domain.di.domainModules

import com.example.domain.di.accountManagementUseCasesModule
import com.example.domain.di.admin.clinic.adminClinicUseCases
import com.example.domain.di.admin.doctor.adminDoctorUseCases
import com.example.domain.di.admin.employee.adminEmployeeUseCases
import com.example.domain.di.admin.pharmacy.adminPharmacyUseCases
import com.example.domain.di.appointments.appointmentUseCasesModule
import com.example.domain.di.auth.authUseCasesModule
import com.example.domain.di.basic_account_creating.addResidentialAddressUseCaseModule
import com.example.domain.di.basic_account_creating.uploadEmploymentDocumentsUseCasesModule
import com.example.domain.di.basic_account_creating.uploadProfileImageUseCasesModule
import com.example.domain.di.children.childDomainModule
import com.example.domain.di.clinics.clinicsUseCasesModule
import com.example.domain.di.doctor.doctorProfileUseCasesModule
import com.example.domain.di.downloaderUseCaseModule
import com.example.domain.di.employeeProfileUseCasesModule
import com.example.domain.di.employmentHistoryUseCasesModule
import com.example.domain.di.getAdminProfileByIdUseCaseModule
import com.example.domain.di.medical_record.medicalRecordUseCasesModule
import com.example.domain.di.medicine.medicinesUseCasesModule
import com.example.domain.di.pharmacy.pharmacyUseCasesModule
import com.example.domain.di.prescription.prescriptionUseCasesModule
import com.example.domain.di.user.userDomainModule
import com.example.domain.di.userPreferencesUseCasesModule
import com.example.domain.di.vaccine.vaccinesUseCasesModule
import com.example.domain.di.validatorUseCasesModule
import com.example.domain.di.work_request.workRequestUseCasesModule
import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    includes(
        authUseCasesModule,
        validatorUseCasesModule,
        addResidentialAddressUseCaseModule,
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
        getAdminProfileByIdUseCaseModule,
        uploadEmploymentDocumentsUseCasesModule,
        medicalRecordUseCasesModule,
        childDomainModule,
        userDomainModule,
        userPreferencesUseCasesModule,
        clinicsUseCasesModule,
        appointmentUseCasesModule,
        workRequestUseCasesModule,
        downloaderUseCaseModule,
        medicinesUseCasesModule,
    )
    singleOf(::CheckEmployeePermissionUseCase)
}