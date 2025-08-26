package com.example.domain.di.vaccine

import com.example.domain.use_cases.prescription.AddNewVaccineUseCase
import com.example.domain.use_cases.vaccine.GetAllVaccinesUseCase
import com.example.domain.use_cases.vaccine.GetGenericVaccinationTableUseCase
import com.example.domain.use_cases.vaccine.GetVaccineByIdUseCase
import com.example.domain.use_cases.vaccine.GetVaccinesWithNoVisitNumberUserCase
import com.example.domain.use_cases.vaccine.UpdateVaccineVisitNumberUseCase
import com.example.domain.use_cases.vaccine.UpdateVaccinesVisitNumberUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val vaccinesUseCasesModule= module {
    singleOf(::AddNewVaccineUseCase)

    singleOf(::GetVaccineByIdUseCase)

    singleOf(::GetGenericVaccinationTableUseCase)
    singleOf(::UpdateVaccineVisitNumberUseCase)
    singleOf(::UpdateVaccinesVisitNumberUseCase)
    singleOf(::GetVaccinesWithNoVisitNumberUserCase)

    singleOf(::GetAllVaccinesUseCase)
}