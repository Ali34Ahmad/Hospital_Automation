package com.example.network.di

import com.example.network.remote.admin.clinic.AdminClinicApiService
import com.example.network.remote.admin.clinic.AdminClinicApiServiceImp
import com.example.network.remote.admin.doctor.AdminDoctorApiService
import com.example.network.remote.admin.doctor.AdminDoctorApiServiceImp
import com.example.network.remote.admin.employee.AdminEmployeeApiService
import com.example.network.remote.admin.employee.AdminEmployeeApiServiceImp
import com.example.network.remote.admin.pharmacy.AdminPharmacyApiService
import com.example.network.remote.admin.pharmacy.AdminPharmacyApiServiceImp
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
}