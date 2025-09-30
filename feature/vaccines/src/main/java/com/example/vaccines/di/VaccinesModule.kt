package com.example.vaccines.di

import com.example.vaccines.presentation.VaccinesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val vaccinesModule= module {
    viewModelOf(::VaccinesViewModel)
}