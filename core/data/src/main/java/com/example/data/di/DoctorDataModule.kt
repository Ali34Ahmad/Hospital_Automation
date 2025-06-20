package com.example.data.di

import com.example.data.repositories.admin_profile.AdminProfileRepositoryImpl
import com.example.data.repositories.auth.AuthRepositoryImpl
import com.example.data.repositories.auth.signup.BaseSignUpRepositoryImpl
import com.example.data.repositories.auth.signup.DoctorSignUpRepositoryImpl
import com.example.data.repositories.doctor.DoctorProfileRepositoryImpl
import com.example.data.repositories.download_file.DownloadFileRepositoryImpl
import com.example.data.repositories.employee_profile.EmployeeProfileRepositoryImpl
import com.example.data.repositories.employment_history.EmploymentHistoryRepositoryImpl
import com.example.data.repositories.residential_address.AddResidentialAddressRepositoryImpl
import com.example.data.repositories.upload_employee_file.UploadEmploymentDocumentsRepositoryImpl
import com.example.data.repositories.upload_employee_image.UploadEmployeeProfileImageRepositoryImpl
import com.example.data.repositories.user_preferences.UserPreferencesRepositoryImpl
import com.example.datastore.di.dataStoreModule
import com.example.domain.di.domainModules.doctorDomainModule
import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.domain.repositories.AdminProfileRepository
import com.example.domain.repositories.AuthRepository
import com.example.domain.repositories.DownloadFileRepository
import com.example.domain.repositories.EmployeeProfileRepository
import com.example.domain.repositories.EmploymentHistoryRepository
import com.example.domain.repositories.UploadEmployeeProfileImageRepository
import com.example.domain.repositories.UploadEmploymentDocumentsRepository
import com.example.domain.repositories.auth.singup.BaseSignUpRepository
import com.example.domain.repositories.auth.singup.DoctorSignUpRepository
import com.example.domain.repositories.doctor.DoctorProfileRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.network.di.doctorNetworkModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val doctorDataModule = module {
    includes(doctorNetworkModule, dataStoreModule, doctorDomainModule)

    singleOf(::DoctorSignUpRepositoryImpl) { bind<DoctorSignUpRepository>() }

    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    singleOf(::DoctorProfileRepositoryImpl) { bind<DoctorProfileRepository>() }

    singleOf(::AddResidentialAddressRepositoryImpl) { bind<AddResidentialAddressRepository>() }

    singleOf(::UploadEmploymentDocumentsRepositoryImpl) { bind<UploadEmploymentDocumentsRepository>() }

    singleOf(::UploadEmployeeProfileImageRepositoryImpl) { bind<UploadEmployeeProfileImageRepository>() }

    singleOf(::EmploymentHistoryRepositoryImpl) { bind<EmploymentHistoryRepository>() }

    singleOf(::AdminProfileRepositoryImpl) { bind<AdminProfileRepository>() }

    singleOf(::DownloadFileRepositoryImpl) { bind<DownloadFileRepository>() }

    singleOf(::UserPreferencesRepositoryImpl) { bind<UserPreferencesRepository>() }
}