package com.example.domain.repositories.prescription

import com.example.model.prescription.PrescriptionMedicineData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface PrescriptionRepository {
    suspend fun addPrescription(
        childId: Int?,
        patientId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineData>
    ) : Result<Int, NetworkError>
}