package com.example.data.mapper.work_request

import com.example.data.mapper.user.toUserMainInfo
import com.example.model.work_request.ChangeRequestStateResponse
import com.example.model.work_request.ClinicMainInfo
import com.example.model.work_request.RequestState
import com.example.model.work_request.RequestType
import com.example.model.work_request.SingleRequestResponse
import com.example.model.work_request.WorkRequestData
import com.example.network.model.dto.work_request.WorkRequestDto
import com.example.network.model.enums.RequestStateDto
import com.example.network.model.enums.RequestTypeDto
import com.example.network.model.response.work_request.ChangeRequestStateResponseDto
import com.example.network.model.response.work_request.ClinicMainInfoDto
import com.example.network.model.response.work_request.SingleRequestResponseDto

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

fun RequestState.toRequestStateDto() =
    when(this){
        RequestState.PENDING -> RequestStateDto.PENDING
        RequestState.REJECTED -> RequestStateDto.REJECTED
        RequestState.ACCEPTED -> RequestStateDto.ACCEPTED
    }

fun RequestTypeDto.toRequestType() =
    when(this){
        RequestTypeDto.EMPLOYEE -> RequestType.EMPLOYEE
        RequestTypeDto.DOCTOR -> RequestType.DOCTOR
        RequestTypeDto.PHARMACIST -> RequestType.PHARMACIST
    }

fun RequestType.toRequestTypeDto() =
    when(this){
        RequestType.EMPLOYEE -> RequestTypeDto.EMPLOYEE
        RequestType.DOCTOR -> RequestTypeDto.DOCTOR
        RequestType.PHARMACIST -> RequestTypeDto.PHARMACIST
    }

fun  SingleRequestResponseDto.toSingleRequestResponse()=
    SingleRequestResponse(
        id = requestId,
        userMainInfo = user.toUserMainInfo(),
        requestType = requestType.toRequestType(),
        state = state.toRequestState(),
        clinicMainInfo = clinic?.toClinicMainInfo(),
        requestingDateTime = requestDataTime
    )

fun ClinicMainInfoDto.toClinicMainInfo()=
    ClinicMainInfo(
        clinicId = clinicId,
        name = name
    )

fun ChangeRequestStateResponseDto.toChangeRequestStateResponse()=
    ChangeRequestStateResponse(
        updatedRequest = updatedRequest
    )