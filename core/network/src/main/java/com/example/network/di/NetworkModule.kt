package com.example.network.di

import com.example.network.remote.account_management.EmployeeAccountManagementApiService
import com.example.network.remote.account_management.EmployeeAccountManagementApiServiceImpl
import com.example.network.remote.add_residential_address.AddResidentialAddressApi
import com.example.network.remote.add_residential_address.AddResidentialAddressApiImpl
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.child.ChildApiService
import com.example.network.remote.child.ChildApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiServiceImpl
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApi
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApiImpl
import com.example.network.remote.upload_employee_profile_image.UploadEmployeeProfileImageApi
import com.example.network.remote.upload_employee_profile_image.UploadEmployeeProfileImageApiImpl
import com.example.network.remote.user.UserApiService
import com.example.network.remote.user.UserApiServiceImpl
import com.example.network.utility.file.FileReader
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
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
            install(HttpTimeout) {
                requestTimeoutMillis = 100 * 1000
                connectTimeoutMillis = 100 * 1000
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    //child api service
    single<ChildApiService> { ChildApiServiceImpl(get()) }

    //user api service
    single<UserApiService> { UserApiServiceImpl(get()) }

    //Auth api service
    single<AuthApiService> { AuthApiServiceImpl(get(), get()) }

    single<UploadEmployeeDocumentsApi> { UploadEmployeeDocumentsApiImpl(get(), get(), get()) }

    single<UploadEmployeeProfileImageApi> { UploadEmployeeProfileImageApiImpl(get(), get(), get()) }

    single<FileReader> { FileReader(androidApplication()) }

    single<AddResidentialAddressApi> { AddResidentialAddressApiImpl(get(), get()) }

    single<EmployeeProfileApiService> { EmployeeProfileApiServiceImpl(get(), get()) }

    single<EmployeeAccountManagementApiService> {
        EmployeeAccountManagementApiServiceImpl(
            get(),
            get()
        )
    }


    single<UserApiService> { UserApiServiceImpl(get()) }

    single<AddResidentialAddressApi> { AddResidentialAddressApiImpl(get(), get()) }
    single<AuthApiService> { AuthApiServiceImpl(get(), get()) }
    single<EmployeeProfileApiService> { EmployeeProfileApiServiceImpl(get(), get()) }
}
