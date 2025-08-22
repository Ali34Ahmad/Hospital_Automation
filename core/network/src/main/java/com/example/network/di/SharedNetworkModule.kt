package com.example.network.di

import android.app.DownloadManager
import com.example.network.downloader.DownloadCompletedReceiver
import com.example.network.downloader.FileDownloaderService
import com.example.network.downloader.FileDownloaderServiceImpl
import com.example.network.remote.account_management.EmployeeAccountManagementApiService
import com.example.network.remote.account_management.EmployeeAccountManagementApiServiceImpl
import com.example.network.remote.appointment.AppointmentsApiService
import com.example.network.remote.appointment.AppointmentsApiServiceImp
import com.example.network.remote.child.ChildApiService
import com.example.network.remote.child.ChildApiServiceImpl
import com.example.network.remote.clinic.ClinicApiService
import com.example.network.remote.clinic.ClinicApiServiceImp
import com.example.network.remote.medicine.MedicineApiService
import com.example.network.remote.medicine.MedicineApiServiceImp
import com.example.network.remote.user.UserApiService
import com.example.network.remote.user.UserApiServiceImpl
import com.example.network.remote.vaccine.VaccineApiService
import com.example.network.remote.vaccine.VaccineApiServiceImpl
import com.example.network.remote.work_request.WorkRequestApiService
import com.example.network.remote.work_request.WorkRequestApiServiceImp
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val sharedNetworkModule = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
    //child api service
    singleOf(::ChildApiServiceImpl) { bind<ChildApiService>() }
    singleOf(::EmployeeAccountManagementApiServiceImpl) {
        bind<EmployeeAccountManagementApiService>()
    }
    //shared between doctor and admin
    singleOf(::ClinicApiServiceImp){bind<ClinicApiService>()}
    //appointment API service
    singleOf(::AppointmentsApiServiceImp){bind<AppointmentsApiService>()}
    //Work request
    singleOf(::WorkRequestApiServiceImp){bind<WorkRequestApiService>()}
    //shared between employee and admin
    singleOf(::UserApiServiceImpl) { bind<UserApiService>() }
    singleOf(::FileDownloaderServiceImpl) { bind<FileDownloaderService>() }
    single<DownloadManager> {
        androidContext().getSystemService(DownloadManager::class.java)
    }

    singleOf(::DownloadCompletedReceiver)

    singleOf(::VaccineApiServiceImpl) { bind<VaccineApiService>() }

    //Medicine API service
    singleOf(::MedicineApiServiceImp){bind<MedicineApiService>()}

}
