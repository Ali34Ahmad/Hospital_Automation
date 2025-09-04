package com.example.network.di

import com.example.network.remote.account_management.AccountManagementApiService
import com.example.network.remote.account_management.AccountManagementApiServiceImpl
import com.example.network.remote.add_residential_address.AddResidentialAddressApiService
import com.example.network.remote.add_residential_address.AddResidentialAddressApiServiceImpl
import com.example.network.remote.admin_profile.AdminProfileApiService
import com.example.network.remote.admin_profile.AdminProfileApiServiceImpl
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.auth.singup.generic.BaseSignUpApiService
import com.example.network.remote.auth.singup.generic.BaseSignUpApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiServiceImpl
import com.example.network.remote.employment_history.EmploymentHistoryApiService
import com.example.network.remote.employment_history.EmploymentHistoryApiServiceImpl
import com.example.network.remote.upload_child_document.UploadChildDocumentsApi
import com.example.network.remote.upload_child_document.UploadChildDocumentsApiImpl
import com.example.network.remote.upload_employee_documents.UploadEmploymentDocumentsApi
import com.example.network.remote.upload_employee_documents.UploadEmploymentDocumentsApiImpl
import com.example.network.remote.upload_file.UploadFileApiService
import com.example.network.remote.upload_file.UploadFileApiServiceImpl
import com.example.network.remote.upload_image.UploadImageApi
import com.example.network.remote.upload_image.UploadImageApiImpl
import com.example.network.remote.upload_profile_image.UploadEmployeeProfileImageApi
import com.example.network.remote.upload_profile_image.UploadEmployeeProfileImageApiImpl
import com.example.network.utility.file.FileReader
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val employeeNetworkModule = module {



    singleOf(::UploadEmploymentDocumentsApiImpl) { bind<UploadEmploymentDocumentsApi>() }

    singleOf(::UploadEmployeeProfileImageApiImpl) { bind<UploadEmployeeProfileImageApi>() }

    single<FileReader> { FileReader(androidApplication()) }

    singleOf(::AccountManagementApiServiceImpl) {
        bind<AccountManagementApiService>()
    }

    singleOf(::AddResidentialAddressApiServiceImpl) { bind<AddResidentialAddressApiService>() }

    singleOf(::BaseSignUpApiServiceImpl) { bind<BaseSignUpApiService>() }

    singleOf(::AuthApiServiceImpl) { bind<AuthApiService>() }

    singleOf(::EmployeeProfileApiServiceImpl) { bind<EmployeeProfileApiService>() }

    singleOf(::EmploymentHistoryApiServiceImpl) { bind<EmploymentHistoryApiService>() }

    singleOf(::AdminProfileApiServiceImpl) { bind<AdminProfileApiService>() }

    singleOf(::UploadChildDocumentsApiImpl) { bind<UploadChildDocumentsApi>() }

    singleOf(::UploadFileApiServiceImpl) { bind<UploadFileApiService>() }

    singleOf(::UploadImageApiImpl) { bind<UploadImageApi>() }


}
