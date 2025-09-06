package com.example.network.di

import android.app.DownloadManager
import com.example.network.downloader.DownloadCompletedReceiver
import com.example.network.downloader.FileDownloaderService
import com.example.network.downloader.FileDownloaderServiceImpl
import com.example.network.remote.account_management.AccountManagementApiService
import com.example.network.remote.account_management.AccountManagementApiServiceImpl
import com.example.network.remote.add_residential_address.AddResidentialAddressApiService
import com.example.network.remote.add_residential_address.AddResidentialAddressApiServiceImpl
import com.example.network.remote.admin.clinic.AdminClinicApiService
import com.example.network.remote.admin.clinic.AdminClinicApiServiceImp
import com.example.network.remote.admin.doctor.AdminDoctorApiService
import com.example.network.remote.admin.doctor.AdminDoctorApiServiceImp
import com.example.network.remote.admin.employee.AdminEmployeeApiService
import com.example.network.remote.admin.employee.AdminEmployeeApiServiceImp
import com.example.network.remote.admin.pharmacy.AdminPharmacyApiService
import com.example.network.remote.admin.pharmacy.AdminPharmacyApiServiceImp
import com.example.network.remote.admin_profile.AdminProfileApiService
import com.example.network.remote.admin_profile.AdminProfileApiServiceImpl
import com.example.network.remote.appointment.AppointmentsApiService
import com.example.network.remote.appointment.AppointmentsApiServiceImp
import com.example.network.remote.appointment_type.AppointmentTypeApiService
import com.example.network.remote.appointment_type.AppointmentTypeApiServiceImp
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.auth.singup.doctor.DoctorSignUpApiService
import com.example.network.remote.auth.singup.doctor.DoctorSignUpApiServiceImpl
import com.example.network.remote.auth.singup.generic.BaseSignUpApiService
import com.example.network.remote.auth.singup.generic.BaseSignUpApiServiceImpl
import com.example.network.remote.child.ChildApiService
import com.example.network.remote.child.ChildApiServiceImpl
import com.example.network.remote.clinic.ClinicApiService
import com.example.network.remote.clinic.ClinicApiServiceImp
import com.example.network.remote.doctor.profile.DoctorProfileApiService
import com.example.network.remote.doctor.profile.DoctorProfileApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiServiceImpl
import com.example.network.remote.employment_history.EmploymentHistoryApiService
import com.example.network.remote.employment_history.EmploymentHistoryApiServiceImpl
import com.example.network.remote.medical_record.MedicalRecordsApiService
import com.example.network.remote.medical_record.MedicalRecordsApiServiceImpl
import com.example.network.remote.medicine.MedicineApiService
import com.example.network.remote.medicine.MedicineApiServiceImp
import com.example.network.remote.pharmacy.PharmacyApiService
import com.example.network.remote.pharmacy.PharmacyApiServiceImp
import com.example.network.remote.prescription.PrescriptionApiService
import com.example.network.remote.prescription.PrescriptionApiServiceImp
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
import com.example.network.remote.user.UserApiService
import com.example.network.remote.user.UserApiServiceImpl
import com.example.network.remote.vaccine.VaccineApiService
import com.example.network.remote.vaccine.VaccineApiServiceImpl
import com.example.network.remote.work_request.WorkRequestApiService
import com.example.network.remote.work_request.WorkRequestApiServiceImp
import com.example.network.remote.workday.WorkDayApiService
import com.example.network.remote.workday.WorkDayApiServiceImp
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

    // Admin & Shared
    singleOf(::AdminDoctorApiServiceImp) { bind<AdminDoctorApiService>() }
    singleOf(::AdminEmployeeApiServiceImp) { bind<AdminEmployeeApiService>() }
    singleOf(::AdminClinicApiServiceImp) { bind<AdminClinicApiService>() }
    singleOf(::AdminPharmacyApiServiceImp) { bind<AdminPharmacyApiService>() }
    singleOf(::AdminProfileApiServiceImpl) { bind<AdminProfileApiService>() }

    // Doctor
    singleOf(::DoctorSignUpApiServiceImpl) { bind<DoctorSignUpApiService>() }
    singleOf(::MedicalRecordsApiServiceImpl) { bind<MedicalRecordsApiService>() }
    singleOf(::AppointmentTypeApiServiceImp) {bind<AppointmentTypeApiService>()}
    singleOf(::WorkDayApiServiceImp) {bind<WorkDayApiService>()}

    // Employee
    singleOf(::UploadChildDocumentsApiImpl) { bind<UploadChildDocumentsApi>() }

    // Shared Services
    singleOf(::AddResidentialAddressApiServiceImpl) { bind<AddResidentialAddressApiService>() }
    singleOf(::UploadEmployeeProfileImageApiImpl) { bind<UploadEmployeeProfileImageApi>() }
    singleOf(::AuthApiServiceImpl) { bind<AuthApiService>() }
    singleOf(::BaseSignUpApiServiceImpl) { bind<BaseSignUpApiService>() }
    singleOf(::VaccineApiServiceImpl) { bind<VaccineApiService>() }
    singleOf(::UploadImageApiImpl) { bind<UploadImageApi>() }
    single<FileReader> { FileReader(androidApplication()) }
    singleOf(::DoctorProfileApiServiceImpl) { bind<DoctorProfileApiService>() }
    singleOf(::EmployeeProfileApiServiceImpl) { bind<EmployeeProfileApiService>() }
    singleOf(::PharmacyApiServiceImp) { bind<PharmacyApiService>() }
    singleOf(::EmploymentHistoryApiServiceImpl) { bind<EmploymentHistoryApiService>() }
    singleOf(::PrescriptionApiServiceImp) { bind<PrescriptionApiService>() }
    singleOf(::AccountManagementApiServiceImpl) { bind<AccountManagementApiService>() }
    singleOf(::UploadFileApiServiceImpl) { bind<UploadFileApiService>() }
    singleOf(::UploadEmploymentDocumentsApiImpl) { bind<UploadEmploymentDocumentsApi>() }
    single<DownloadManager> { androidContext().getSystemService(DownloadManager::class.java) }
    singleOf(::FileDownloaderServiceImpl) { bind<FileDownloaderService>() }
    singleOf(::DownloadCompletedReceiver)
    singleOf(::ChildApiServiceImpl) { bind<ChildApiService>() }
    singleOf(::UserApiServiceImpl) { bind<UserApiService>() }
    singleOf(::ClinicApiServiceImp) { bind<ClinicApiService>() }
    singleOf(::AppointmentsApiServiceImp) { bind<AppointmentsApiService>() }
    singleOf(::WorkRequestApiServiceImp) { bind<WorkRequestApiService>() }
    singleOf(::MedicineApiServiceImp) { bind<MedicineApiService>() }

}
