package com.example.data.di

import com.example.data.repositories.auth.signup.DoctorSignUpRepositoryImpl
import com.example.datastore.di.dataStoreModule
import com.example.domain.di.domainModules.doctorDomainModule
import com.example.domain.repositories.auth.singup.DoctorSignUpRepository
import com.example.network.di.doctorNetworkModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val doctorDataModule = module {
    includes(doctorNetworkModule, dataStoreModule, doctorDomainModule)

    singleOf(::DoctorSignUpRepositoryImpl) { bind<DoctorSignUpRepository>() }
}