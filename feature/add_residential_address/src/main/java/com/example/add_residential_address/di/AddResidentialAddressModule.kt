package com.example.add_residential_address.di

import com.example.add_residential_address.presentation.AddResidentialAddressViewModel
import com.example.add_residential_address.validator.AddResidentialAddressValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val addResidentialAddressModule = module {
    viewModelOf(::AddResidentialAddressViewModel)

    singleOf(::AddResidentialAddressValidator)
}