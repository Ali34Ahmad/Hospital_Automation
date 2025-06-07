package com.example.domain.di.user

import com.example.domain.use_cases.users.AddGuardianToChildUseCase
import com.example.domain.use_cases.users.GetGuardianByIdUseCase
import com.example.domain.use_cases.users.GetGuardiansByChildIdUseCase
import com.example.domain.use_cases.users.paged.SearchForGuardiansByNameUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userDomainModule = module {
    singleOf(::AddGuardianToChildUseCase)
    singleOf(::GetGuardianByIdUseCase)
    singleOf(::GetGuardiansByChildIdUseCase)

    // paging data flow
    singleOf(::SearchForGuardiansByNameUseCase)
}