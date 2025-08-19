package com.example.domain.di.domainModules

import com.example.domain.di.accountManagementUseCasesModule
import com.example.domain.di.auth.authUseCasesModule
import com.example.domain.di.auth.singup.doctorSignUpModule
import com.example.domain.di.basic_account_creating.addResidentialAddressUseCaseModule
import com.example.domain.di.basic_account_creating.uploadEmploymentDocumentsUseCasesModule
import com.example.domain.di.basic_account_creating.uploadProfileImageUseCasesModule
import com.example.domain.di.doctor.doctorProfileUseCasesModule
import com.example.domain.di.downloaderUseCaseModule
import com.example.domain.di.employmentHistoryUseCasesModule
import com.example.domain.di.getAdminProfileByIdUseCaseModule
import com.example.domain.di.medical_record.medicalRecordModule
import com.example.domain.di.medicine.medicinesUseCasesModule
import com.example.domain.di.pharmacy.pharmacyUseCasesModule
import com.example.domain.di.prescription.prescriptionUseCasesModule
import com.example.domain.di.vaccine.vaccinesUseCasesModules
import com.example.domain.di.validatorUseCasesModule
import org.koin.dsl.module

val doctorDomainModule = module {
    includes(
        sharedDomainModule,
        pharmacyUseCasesModule,
        medicinesUseCasesModule,
        prescriptionUseCasesModule,
        authUseCasesModule,
        doctorSignUpModule,
        addResidentialAddressUseCaseModule,
        getAdminProfileByIdUseCaseModule,
        downloaderUseCaseModule,
        accountManagementUseCasesModule,
        doctorProfileUseCasesModule,
        employmentHistoryUseCasesModule,
        uploadEmploymentDocumentsUseCasesModule,
        uploadProfileImageUseCasesModule,
        validatorUseCasesModule,
        vaccinesUseCasesModules,
        medicalRecordModule,
    )
}
