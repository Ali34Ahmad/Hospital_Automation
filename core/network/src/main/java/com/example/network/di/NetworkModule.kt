package com.example.network.di

import android.app.DownloadManager
import com.example.network.downloader.DownloadCompletedReceiver
import com.example.network.downloader.FileDownloaderService
import com.example.network.downloader.FileDownloaderServiceImpl
import com.example.network.remote.account_management.EmployeeAccountManagementApiService
import com.example.network.remote.account_management.EmployeeAccountManagementApiServiceImpl
import com.example.network.remote.add_residential_address.AddResidentialAddressApiService
import com.example.network.remote.add_residential_address.AddResidentialAddressApiServiceImpl
import com.example.network.remote.admin_profile.AdminProfileApiService
import com.example.network.remote.admin_profile.AdminProfileApiServiceImpl
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.child.ChildApiService
import com.example.network.remote.child.ChildApiServiceImpl
import com.example.network.remote.doctor.DoctorApiService
import com.example.network.remote.doctor.DoctorApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiServiceImpl
import com.example.network.remote.employment_history.EmploymentHistoryApiService
import com.example.network.remote.employment_history.EmploymentHistoryApiServiceImpl
import com.example.network.remote.upload_child_document.UploadChildDocumentsApi
import com.example.network.remote.upload_child_document.UploadChildDocumentsApiImpl
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApi
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApiImpl
import com.example.network.remote.upload_employee_profile_image.UploadImageApi
import com.example.network.remote.upload_employee_profile_image.UploadImageApiImpl
import com.example.network.remote.upload_file.UploadFileApiService
import com.example.network.remote.upload_file.UploadFileApiServiceImpl
import com.example.network.remote.upload_image.UploadEmployeeProfileImageApi
import com.example.network.remote.upload_image.UploadEmployeeProfileImageApiImpl
import com.example.network.remote.user.UserApiService
import com.example.network.remote.user.UserApiServiceImpl
import com.example.network.utility.file.FileReader
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val networkModule = module {

    //ktor client
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
    //doctor api service
    singleOf(::DoctorApiServiceImpl){bind<DoctorApiService>()}
    //child api service
    singleOf(::ChildApiServiceImpl) { bind<ChildApiService>() }

    singleOf(::UserApiServiceImpl) { bind<UserApiService>() }

    singleOf(::UploadEmployeeDocumentsApiImpl) { bind<UploadEmployeeDocumentsApi>() }

    singleOf(::UploadEmployeeProfileImageApiImpl) { bind<UploadEmployeeProfileImageApi>() }

    single<FileReader> { FileReader(androidApplication()) }

    singleOf(::EmployeeAccountManagementApiServiceImpl) {
        bind<EmployeeAccountManagementApiService>()
    }

    singleOf(::AddResidentialAddressApiServiceImpl) { bind<AddResidentialAddressApiService>() }

    singleOf(::AuthApiServiceImpl) { bind<AuthApiService>() }

    singleOf(::EmployeeProfileApiServiceImpl) { bind<EmployeeProfileApiService>() }

    singleOf(::EmploymentHistoryApiServiceImpl) { bind<EmploymentHistoryApiService>() }

    singleOf(::AdminProfileApiServiceImpl) { bind<AdminProfileApiService>() }

    singleOf(::ChildApiServiceImpl) { bind<ChildApiService>() }

    singleOf(::UploadChildDocumentsApiImpl) { bind<UploadChildDocumentsApi>() }

    singleOf(::UploadFileApiServiceImpl) { bind<UploadFileApiService>() }

    singleOf(::UploadImageApiImpl) { bind<UploadImageApi>() }

    single<DownloadManager> {
        androidContext().getSystemService(DownloadManager::class.java)
    }

    singleOf(::FileDownloaderServiceImpl) { bind<FileDownloaderService>() }
    singleOf(::DownloadCompletedReceiver)

}
