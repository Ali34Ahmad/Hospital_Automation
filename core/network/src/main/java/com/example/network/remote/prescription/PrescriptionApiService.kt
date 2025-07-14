package com.example.network.remote.prescription

import com.example.network.model.dto.prescription.PrescriptionMedicineDto
import com.example.network.model.response.prescription.AddPrescriptionResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface PrescriptionApiService {
    suspend fun addPrescription(
        token: String,
        patientId : Int?,
        childId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineDto>
    ) : Result<AddPrescriptionResponse, NetworkError>
}