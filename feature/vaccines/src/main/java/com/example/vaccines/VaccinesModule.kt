package com.example.vaccines

import com.example.vaccines.main.VaccinesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val vaccinesModule= module {
    viewModelOf(::VaccinesViewModel)
}