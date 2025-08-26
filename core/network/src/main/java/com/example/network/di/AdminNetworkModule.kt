package com.example.network.di

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
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.auth.singup.generic.BaseSignUpApiService
import com.example.network.remote.auth.singup.generic.BaseSignUpApiServiceImpl
import com.example.network.remote.doctor.profile.DoctorProfileApiService
import com.example.network.remote.doctor.profile.DoctorProfileApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiServiceImpl
import com.example.network.remote.employment_history.EmploymentHistoryApiService
import com.example.network.remote.employment_history.EmploymentHistoryApiServiceImpl
import com.example.network.remote.pharmacy.PharmacyApiService
import com.example.network.remote.pharmacy.PharmacyApiServiceImp
import com.example.network.remote.prescription.PrescriptionApiService
import com.example.network.remote.prescription.PrescriptionApiServiceImp
import com.example.network.remote.upload_image.UploadImageApi
import com.example.network.remote.upload_image.UploadImageApiImpl
import com.example.network.remote.upload_profile_image.UploadEmployeeProfileImageApi
import com.example.network.remote.upload_profile_image.UploadEmployeeProfileImageApiImpl
import com.example.network.remote.vaccine.VaccineApiService
import com.example.network.remote.vaccine.VaccineApiServiceImpl
import com.example.network.utility.file.FileReader
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val adminNetworkModule = module {
    includes(sharedNetworkModule)
    singleOf(::AdminDoctorApiServiceImp){bind<AdminDoctorApiService>()}
    singleOf(::AdminEmployeeApiServiceImp){bind<AdminEmployeeApiService>()}
    singleOf(::AdminClinicApiServiceImp){bind<AdminClinicApiService>()}
    singleOf(::AdminPharmacyApiServiceImp){bind<AdminPharmacyApiService>()}
    singleOf(::AddResidentialAddressApiServiceImpl) { bind<AddResidentialAddressApiService>() }
    singleOf(::UploadEmployeeProfileImageApiImpl) { bind<UploadEmployeeProfileImageApi>() }

    singleOf(::AuthApiServiceImpl) { bind<AuthApiService>() }
    singleOf(::BaseSignUpApiServiceImpl) { bind<BaseSignUpApiService>() }
    singleOf(::VaccineApiServiceImpl) { bind<VaccineApiService>() }
    singleOf(::UploadImageApiImpl) { bind<UploadImageApi>() }
    single<FileReader> { FileReader(androidApplication()) }
    singleOf(::DoctorProfileApiServiceImpl) { bind<DoctorProfileApiService>() }
    singleOf(::EmployeeProfileApiServiceImpl) { bind<EmployeeProfileApiService>() }
    singleOf(::PharmacyApiServiceImp){bind<PharmacyApiService>()}
    singleOf(::EmploymentHistoryApiServiceImpl) { bind<EmploymentHistoryApiService>() }
    singleOf(::PrescriptionApiServiceImp){bind<PrescriptionApiService>()}

}