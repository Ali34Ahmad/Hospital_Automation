package com.example.doctors.di

import com.example.doctors.presentation.DoctorsSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val doctorsSearchModule = module {
    viewModelOf(::DoctorsSearchViewModel)
}