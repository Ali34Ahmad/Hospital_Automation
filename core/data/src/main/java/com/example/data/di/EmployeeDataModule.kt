package com.example.data.di

import com.example.data.repositories.admin_profile.AdminProfileRepositoryImpl
import com.example.data.repositories.auth.AuthRepositoryImpl
import com.example.data.repositories.auth.signup.BaseSignUpRepositoryImpl
import com.example.data.repositories.child.ChildRepositoryImp
import com.example.data.repositories.employee_profile.EmployeeProfileRepositoryImpl
import com.example.data.repositories.employment_history.EmploymentHistoryRepositoryImpl
import com.example.data.repositories.residential_address.AddResidentialAddressRepositoryImpl
import com.example.data.repositories.upload_child_file.UploadChildDocumentsRepositoryImpl
import com.example.data.repositories.upload_employee_file.UploadEmploymentDocumentsRepositoryImpl
import com.example.data.repositories.upload_employee_image.UploadEmployeeProfileImageRepositoryImpl
import com.example.data.repositories.user.UserRepositoryImp
import com.example.domain.di.domainModules.employeeDomainModule
import com.example.domain.repositories.AddResidentialAddressRepository
import com.example.domain.repositories.ChildRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.repositories.auth.AuthRepository
import com.example.domain.repositories.auth.singup.BaseSignUpRepository
import com.example.domain.repositories.file.UploadChildDocumentsRepository
import com.example.domain.repositories.file.UploadEmployeeProfileImageRepository
import com.example.domain.repositories.file.UploadEmploymentDocumentsRepository
import com.example.domain.repositories.profile.AdminProfileRepository
import com.example.domain.repositories.profile.EmployeeProfileRepository
import com.example.domain.repositories.profile.EmploymentHistoryRepository
import com.example.network.di.employeeNetworkModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val employeeAppDataModule = module {
    includes(employeeNetworkModule, employeeDomainModule,commonDataModule)


    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    singleOf(::BaseSignUpRepositoryImpl) { bind<BaseSignUpRepository>() }

    singleOf(::UserRepositoryImp) { bind<UserRepository>() }
    singleOf(::ChildRepositoryImp) { bind<ChildRepository>() }

    singleOf(::EmployeeProfileRepositoryImpl) { bind<EmployeeProfileRepository>() }

    singleOf(::AddResidentialAddressRepositoryImpl) { bind<AddResidentialAddressRepository>() }

    singleOf(::UploadEmploymentDocumentsRepositoryImpl) { bind<UploadEmploymentDocumentsRepository>() }

    singleOf(::UploadChildDocumentsRepositoryImpl) { bind<UploadChildDocumentsRepository>() }

    singleOf(::UploadEmployeeProfileImageRepositoryImpl) { bind<UploadEmployeeProfileImageRepository>() }

    singleOf(::EmploymentHistoryRepositoryImpl) { bind<EmploymentHistoryRepository>() }

    singleOf(::AdminProfileRepositoryImpl) { bind<AdminProfileRepository>() }

}