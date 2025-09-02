package com.example.domain.use_cases.prescription

import androidx.paging.PagingData
import com.example.domain.repositories.prescription.PrescriptionRepository
import com.example.model.prescription.PrescriptionWithUser
import com.example.model.user.UserMainInfo
import kotlinx.coroutines.flow.Flow

class GetPrescriptionsUseCase(
    private val prescriptionRepository: PrescriptionRepository
) {
    suspend operator fun invoke(
        onMainUserInfoChanged: (UserMainInfo) -> Unit,
        patientId: Int?,
        childId: Int?,
        doctorId:Int?,
        name: String?,
    ): Flow<PagingData<PrescriptionWithUser>> {
        return prescriptionRepository.getPrescriptionsForDoctor(
            onMainUserInfoChanged = onMainUserInfoChanged,
            patientId = patientId,
            childId = childId,
            doctorId = doctorId,
            name = name,
        )
    }
}