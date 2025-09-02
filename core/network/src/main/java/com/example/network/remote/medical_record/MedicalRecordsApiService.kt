package com.example.network.remote.medical_record

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.medical_record.GetAllMedicalRecordsResponseDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface MedicalRecordsApiService {
    suspend fun getAllMedicalRecordsForCurrentDoctor(
        token: String,
        page: Int,
        limit: Int,
        role: RoleDto,
        name:String?,
        doctorId:Int?,
    ) : Result<GetAllMedicalRecordsResponseDto, NetworkError>

}