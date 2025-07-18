package com.example.pharmacies.di

import com.example.pharmacies.presentaion.PharmaciesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val  pharmaciesModule = module {
    viewModelOf(::PharmaciesViewModel)
}