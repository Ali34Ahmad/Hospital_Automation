package com.example.add_new_vaccine

import com.example.add_new_vaccine.main.AddNewVaccineViewModel
import com.example.add_new_vaccine.validator.AddNewVaccineValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val addNewVaccineModule = module {
    viewModelOf(::AddNewVaccineViewModel)

    singleOf(::AddNewVaccineValidator)
}
