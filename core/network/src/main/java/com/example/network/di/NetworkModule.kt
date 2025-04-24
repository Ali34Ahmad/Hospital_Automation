package com.example.network.di

import com.example.network.remote.add_residential_address.AddResidentialAddressApi
import com.example.network.remote.add_residential_address.AddResidentialAddressApiImpl
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.child.ChildApiService
import com.example.network.remote.child.ChildApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApi
import com.example.network.remote.employee_profile.EmployeeProfileApiImpl
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApi
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApiImpl
import com.example.network.remote.user.UserApiImpl
import com.example.network.remote.user.UserApiService
import com.example.network.utility.file.FileReader
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val networkModule = module {
    //ktor client
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

        }
    }

    //child api service impl
    single<ChildApiService> { ChildApiServiceImpl(get()) }
    single<AuthApiService> { AuthApiServiceImpl(get()) }
    single<AddResidentialAddressApi> { AddResidentialAddressApiImpl(get()) }
    single<EmployeeProfileApi> { EmployeeProfileApiImpl(get()) }

    single<FileReader> {
        FileReader(
            context = androidContext(),
            dispatcher = Dispatchers.IO
        )
    }
    single<UploadEmployeeDocumentsApi> { UploadEmployeeDocumentsApiImpl(get(), get()) }
    single<UserApiService> { UserApiImpl(get()) }

}