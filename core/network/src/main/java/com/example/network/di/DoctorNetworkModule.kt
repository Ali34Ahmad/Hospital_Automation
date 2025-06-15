package com.example.network.di

import com.example.network.remote.auth.singup.doctor.DoctorSignUpApiService
import com.example.network.remote.auth.singup.doctor.DoctorSignUpApiServiceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val doctorNetworkModule = module {
    singleOf(::DoctorSignUpApiServiceImpl) { bind<DoctorSignUpApiService>() }
}