package com.example.prescriptions

import com.example.prescriptions.main.PrescriptionsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val prescriptionsModule= module{
    viewModelOf(::PrescriptionsViewModel)
}