package com.example.network.remote.prescription

import com.example.network.model.dto.prescription.PrescriptionMedicineDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.prescription.AddPrescriptionResponse
import com.example.network.model.response.prescription.GetAllPrescriptionsResponseDto
import com.example.network.model.response.prescription.PrescriptionDetailsWithMedicinesDto
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

    suspend fun getAllPrescriptions(
        token: String,
        page: Int,
        limit: Int,
        role: RoleDto,
        patientId: Int?,
        childId: Int?,
        name:String?,
        doctorId: Int?,
    ) : Result<GetAllPrescriptionsResponseDto, NetworkError>

    suspend fun getPrescriptionDetailsById(
        token: String,
        id:Int,
        role: RoleDto
    ) : Result<PrescriptionDetailsWithMedicinesDto, NetworkError>

}