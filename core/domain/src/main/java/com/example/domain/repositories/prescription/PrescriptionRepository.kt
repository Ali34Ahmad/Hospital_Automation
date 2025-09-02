package com.example.domain.repositories.prescription

import androidx.paging.PagingData
import com.example.model.prescription.PrescriptionDetails
import com.example.model.prescription.PrescriptionMedicineData
import com.example.model.prescription.PrescriptionWithUser
import com.example.model.user.UserMainInfo
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import kotlinx.coroutines.flow.Flow

interface PrescriptionRepository {
    suspend fun addPrescription(
        childId: Int?,
        patientId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineData>
    ) : Result<Int, NetworkError>

    suspend fun getPrescriptionsForDoctor(
        onMainUserInfoChanged: (UserMainInfo) -> Unit,
        patientId:Int?,
        childId:Int?,
        doctorId:Int?,
        name:String?,
    ): Flow<PagingData<PrescriptionWithUser>>

    suspend fun getPrescriptionDetailsById(id:Int): Result<PrescriptionDetails,rootError>
}