package com.example.model.work_request

import com.example.model.user.UserMainInfo
import java.time.LocalDateTime

data class SingleRequestResponse(
    val id: Int,
    val userMainInfo: UserMainInfo,
    val requestType: RequestType,
    val state: RequestState,
    val clinicMainInfo:ClinicMainInfo?,
    val requestingDateTime: LocalDateTime,
    val pharmacyId:Int?,
)

data class ClinicMainInfo(
    val clinicId:Int,
    val name:String,
)