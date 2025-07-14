package com.example.domain.use_cases.pharmacy

import com.example.domain.repositories.pharmacy.PharmacyRepository

class GetPharmaciesByMedicinesIdUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(medicineId: Int) = repository
        .getPharmaciesByMedicineId(medicineId = medicineId)
}
