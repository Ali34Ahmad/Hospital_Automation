package com.example.data.mapper.work_request

import com.example.model.work_request.RequestState
import com.example.model.work_request.RequestType
import com.example.model.work_request.WorkRequestData
import com.example.network.model.dto.work_request.WorkRequestDto
import com.example.network.model.enums.RequestStateDto
import com.example.network.model.enums.RequestTypeDto

fun WorkRequestDto.toWorkRequestData() = WorkRequestData(
    requestId = requestId,
    requestType = requestType.toRequestType(),
    state = state.toRequestState(),
    requestDateTime = requestDateTime,
    employeeId = employeeId,
    clinicId = clinicId
)

fun RequestStateDto.toRequestState() =
    when(this){
        RequestStateDto.PENDING -> RequestState.PENDING
        RequestStateDto.REJECTED -> RequestState.REJECTED
        RequestStateDto.ACCEPTED -> RequestState.ACCEPTED
    }

fun RequestTypeDto.toRequestType() =
    when(this){
        RequestTypeDto.EMPLOYEE -> RequestType.EMPLOYEE
        RequestTypeDto.DOCTOR -> RequestType.DOCTOR
    }

fun RequestType.toRequestTypeDto() =
    when(this){
        RequestType.EMPLOYEE -> RequestTypeDto.EMPLOYEE
        RequestType.DOCTOR -> RequestTypeDto.DOCTOR
    }