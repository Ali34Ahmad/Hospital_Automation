package com.example.add_residential_address

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val addResidentialAddressModule = module {
    viewModel {
        AddResidentialAddressViewModel(
            get()
        )
    }
}