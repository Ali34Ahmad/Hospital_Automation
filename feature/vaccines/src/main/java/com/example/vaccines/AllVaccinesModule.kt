package com.example.vaccines

import com.example.vaccines.main.AllVaccinesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val allVaccinesModule= module {
    viewModelOf(::AllVaccinesViewModel)
}