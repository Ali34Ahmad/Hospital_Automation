package com.example.prescriptions.di

import com.example.prescriptions.presentation.PrescriptionsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val prescriptionsModule= module{
    viewModelOf(::PrescriptionsViewModel)
}