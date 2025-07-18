package com.example.domain.use_cases.medicine

import com.example.domain.repositories.MedicineRepository

class GetMedicineByIdUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(medicineId: Int) = repository
        .getMedicineById(medicineId = medicineId)
}