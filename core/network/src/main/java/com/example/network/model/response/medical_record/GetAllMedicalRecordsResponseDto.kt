package com.example.network.model.response.medical_record

import com.example.network.model.dto.medical_record.MedicalRecordDto
import com.example.network.model.response.NetworkPagination
import com.example.network.model.response.user.UserMainInfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllMedicalRecordsResponseDto(
    @SerialName("record")
    val data: List<MedicalRecordDto>,
    @SerialName("doctorInfo")
    val userMainInfoDto: UserMainInfoDto,
    val pagination: NetworkPagination
)
