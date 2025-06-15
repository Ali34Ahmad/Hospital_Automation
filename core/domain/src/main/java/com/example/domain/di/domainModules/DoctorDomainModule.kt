package com.example.domain.di.domainModules

import com.example.domain.di.auth.singup.doctorSignUpModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val doctorDomainModule = module {
    includes(
        doctorSignUpModule,
    )
}