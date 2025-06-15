package com.example.domain.di.basic_account_creating

import com.example.domain.use_cases.add_residential_address.AddResidentialAddressUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val addResidentialAddressUseCaseModule= module {
    singleOf(::AddResidentialAddressUseCase)
}