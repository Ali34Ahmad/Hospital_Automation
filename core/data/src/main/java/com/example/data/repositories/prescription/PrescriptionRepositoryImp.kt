package com.example.data.repositories.prescription

import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.prescription.toPrescriptionMedicineDto
import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.model.prescription.PrescriptionMedicineData
import com.example.network.remote.prescription.PrescriptionApiService
import com.example.utility.network.map
import com.example.utility.network.onSuccess

class PrescriptionRepositoryImp(
    private val apiService: PrescriptionApiService,
)
    : PrescriptionRepository{
    override suspend fun addPrescription(
        childId: Int?,
        patientId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineData>,
    ) =
        apiService.addPrescription(
            token = FAKE_TOKEN,
            patientId = patientId,
            childId = childId,
            note = note,
            medicines = medicines.map { it.toPrescriptionMedicineDto() }
        ).map { it.prescription.id }
}