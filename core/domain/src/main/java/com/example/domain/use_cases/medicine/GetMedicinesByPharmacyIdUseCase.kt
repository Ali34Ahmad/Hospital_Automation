package com.example.domain.use_cases.medicine

import com.example.domain.repositories.MedicineRepository

class GetMedicinesByPharmacyIdUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(
        name: String,
        pharmacyId: Int,
    ) = repository.searchForMedicinesByPharmacyId(
        name = name,
        pharmacyId = pharmacyId
    )
}