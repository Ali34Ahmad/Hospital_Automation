package com.example.data.di

import com.example.data.repositories.admin.clinic.AdminClinicRepositoryImp
import com.example.data.repositories.admin.doctor.AdminDoctorRepositoryImp
import com.example.data.repositories.admin.employee.AdminEmployeeRepositoryImp
import com.example.data.repositories.admin.pharmacy.AdminPharmacyRepositoryImp
import com.example.data.repositories.auth.AuthRepositoryImpl
import com.example.data.repositories.auth.signup.BaseSignUpRepositoryImpl
import com.example.data.repositories.residential_address.AddResidentialAddressRepositoryImpl
import com.example.data.repositories.upload_employee_image.UploadEmployeeProfileImageRepositoryImpl
import com.example.data.repositories.vaccine.VaccineRepositoryImpl
import com.example.domain.di.domainModules.adminDomainModule
import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.domain.repositories.admin.pharmacy.AdminPharmacyRepository
import com.example.domain.repositories.admin.clinic.AdminClinicRepository
import com.example.domain.repositories.admin.doctor.AdminDoctorRepository
import com.example.domain.repositories.admin.employee.AdminEmployeeRepository
import com.example.domain.repositories.auth.AuthRepository
import com.example.domain.repositories.auth.singup.BaseSignUpRepository
import com.example.domain.repositories.file.UploadEmployeeProfileImageRepository
import com.example.domain.repositories.vaccine.VaccineRepository
import com.example.network.di.adminNetworkModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val adminDataModule = module {
    includes(
        commonDataModule,
        adminDomainModule,
        adminNetworkModule,
    )

    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    singleOf(::BaseSignUpRepositoryImpl) { bind<BaseSignUpRepository>() }
    singleOf(::AddResidentialAddressRepositoryImpl) { bind<AddResidentialAddressRepository>() }
    singleOf(::UploadEmployeeProfileImageRepositoryImpl) { bind<UploadEmployeeProfileImageRepository>() }

    singleOf(::AdminDoctorRepositoryImp) { bind<AdminDoctorRepository>() }
    singleOf(::AdminEmployeeRepositoryImp){bind<AdminEmployeeRepository>()}
    singleOf(::AdminClinicRepositoryImp){bind<AdminClinicRepository>()}
    singleOf(::AdminPharmacyRepositoryImp){bind<AdminPharmacyRepository>()}
    singleOf(::VaccineRepositoryImpl) { bind<VaccineRepository>() }
}