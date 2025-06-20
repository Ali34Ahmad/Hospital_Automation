package com.example.network.di

import android.app.DownloadManager
import com.example.network.downloader.DownloadCompletedReceiver
import com.example.network.downloader.FileDownloaderService
import com.example.network.downloader.FileDownloaderServiceImpl
import com.example.network.remote.add_residential_address.AddResidentialAddressApiService
import com.example.network.remote.add_residential_address.AddResidentialAddressApiServiceImpl
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.auth.singup.doctor.DoctorSignUpApiService
import com.example.network.remote.auth.singup.doctor.DoctorSignUpApiServiceImpl
import com.example.network.remote.child.ChildApiService
import com.example.network.remote.child.ChildApiServiceImpl
import com.example.network.remote.doctor.profile.DoctorProfileApiService
import com.example.network.remote.doctor.profile.DoctorProfileApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiServiceImpl
import com.example.network.remote.upload_employee_documents.UploadEmploymentDocumentsApi
import com.example.network.remote.upload_employee_documents.UploadEmploymentDocumentsApiImpl
import com.example.network.remote.upload_file.UploadFileApiService
import com.example.network.remote.upload_file.UploadFileApiServiceImpl
import com.example.network.remote.upload_image.UploadImageApi
import com.example.network.remote.upload_image.UploadImageApiImpl
import com.example.network.remote.upload_profile_image.UploadEmployeeProfileImageApi
import com.example.network.remote.upload_profile_image.UploadEmployeeProfileImageApiImpl
import com.example.network.remote.user.UserApiService
import com.example.network.remote.user.UserApiServiceImpl
import com.example.network.remote.vaccine.VaccineApiService
import com.example.network.remote.vaccine.VaccineApiServiceImpl
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

val doctorNetworkModule = module {
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
    singleOf(::DoctorSignUpApiServiceImpl) { bind<DoctorSignUpApiService>() }

    singleOf(::UploadEmploymentDocumentsApiImpl) { bind<UploadEmploymentDocumentsApi>() }

    singleOf(::UploadEmployeeProfileImageApiImpl) { bind<UploadEmployeeProfileImageApi>() }

    single<FileReader> { FileReader(androidApplication()) }

    singleOf(::AddResidentialAddressApiServiceImpl) { bind<AddResidentialAddressApiService>() }

    singleOf(::AuthApiServiceImpl) { bind<AuthApiService>() }

    singleOf(::DoctorProfileApiServiceImpl) { bind<DoctorProfileApiService>() }

    singleOf(::UploadFileApiServiceImpl) { bind<UploadFileApiService>() }

    singleOf(::UploadImageApiImpl) { bind<UploadImageApi>() }

    single<DownloadManager> {
        androidContext().getSystemService(DownloadManager::class.java)
    }

    singleOf(::FileDownloaderServiceImpl) { bind<FileDownloaderService>() }
    singleOf(::DownloadCompletedReceiver)

    singleOf(::VaccineApiServiceImpl) { bind<VaccineApiService>() }
}