package com.example.data.repositories.prescription

import com.example.data.mapper.prescription.toPrescriptionMedicineDto
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.model.prescription.PrescriptionMedicineData
import com.example.network.remote.prescription.PrescriptionApiService
import com.example.utility.network.map

class PrescriptionRepositoryImp(
    private val apiService: PrescriptionApiService,
    private val dataStore: UserPreferencesRepository
)
    : PrescriptionRepository{
    override suspend fun addPrescription(
        childId: Int?,
        patientId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineData>,
    ) = dataStore.executeWithValidToken { token->
        apiService.addPrescription(
            token = token,
            patientId = patientId,
            childId = childId,
            note = note,
            medicines = medicines.map { it.toPrescriptionMedicineDto() }
        ).map { it.prescription.id }
    }

}