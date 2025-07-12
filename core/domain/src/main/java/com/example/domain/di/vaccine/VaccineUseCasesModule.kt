package com.example.domain.di.vaccine

import com.example.domain.use_cases.vaccine.AddNewVaccineUseCase
import com.example.domain.use_cases.vaccine.GetGenericVaccinationTableUseCase
import com.example.domain.use_cases.vaccine.GetVaccineByIdUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val vaccineUseCase= module {
    singleOf(::AddNewVaccineUseCase)

    singleOf(::GetVaccineByIdUseCase)

    singleOf(::GetGenericVaccinationTableUseCase)
}