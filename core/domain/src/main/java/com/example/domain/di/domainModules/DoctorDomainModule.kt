package com.example.domain.di.domainModules

import com.example.domain.di.appointments.appointmentUseCasesModule
import com.example.domain.di.auth.authUseCasesModule
import com.example.domain.di.auth.singup.doctorSignUpModule
import com.example.domain.di.basic_account_creating.addResidentialAddressUseCaseModule
import com.example.domain.di.basic_account_creating.uploadProfileImageUseCasesModule
import com.example.domain.di.basic_account_creating.uploadEmploymentDocumentsUseCasesModule
import com.example.domain.di.downloaderUseCaseModule
import com.example.domain.di.accountManagementUseCasesModule
import com.example.domain.di.clinics.clinicsUseCasesModule
import com.example.domain.di.doctor.doctorProfileUseCasesModule
import com.example.domain.di.employmentHistoryUseCasesModule
import com.example.domain.di.getAdminProfileByIdUseCaseModule
import com.example.domain.di.userPreferencesUseCasesModule
import com.example.domain.di.validatorUseCasesModule
import com.example.domain.di.work_request.workRequestUseCasesModule

import org.koin.dsl.module

val doctorDomainModule = module {
    includes(
        sharedDomainModule,
        authUseCasesModule,
        doctorSignUpModule,
        appointmentUseCasesModule,
        userPreferencesUseCasesModule,
        addResidentialAddressUseCaseModule,
        getAdminProfileByIdUseCaseModule,
        downloaderUseCaseModule,
        accountManagementUseCasesModule,
        doctorProfileUseCasesModule,
        employmentHistoryUseCasesModule,
        uploadEmploymentDocumentsUseCasesModule,
        uploadProfileImageUseCasesModule,
        validatorUseCasesModule,
        userPreferencesUseCasesModule,
        clinicsUseCasesModule,
        workRequestUseCasesModule
    )
}
