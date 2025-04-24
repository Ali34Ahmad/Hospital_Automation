package com.example.model

import com.example.constants.enums.RequestState
import com.example.constants.enums.RequestType
import java.time.LocalDateTime

data class Request(
    val id: Int,
    val employeeId: Int,
    val employeeName:String,
    val requestType: RequestType,
    val state: RequestState,
    val clinicId: Int,
    val clinicName:String,
    val requestingDateTime: LocalDateTime,
    val responseDateTime: LocalDateTime? = null,
)