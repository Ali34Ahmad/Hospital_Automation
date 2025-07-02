package com.example.domain.use_cases.medicine

import com.example.domain.repositories.MedicineRepository


class GetMedicinesFlowUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(
        name: String?,
    )=
        repository.getMedicines(name)

}