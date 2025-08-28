package com.example.domain.use_cases.prescription

import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.model.prescription.PrescriptionMedicineData

class AddPrescriptionUseCase(
    private val repository: PrescriptionRepository
) {
    suspend operator fun invoke(
        childId: Int?,
        patientId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineData>
    ) = repository.addPrescription(
        childId = childId,
        patientId = patientId,
        note= note,
        medicines = medicines
    )
}