package com.example.data.mapper.work_request

import android.util.Log
import com.example.data.mapper.user.toUserMainInfo
import com.example.model.user.FullName
import com.example.model.user.UserMainInfo
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
import kotlin.Int

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

fun  SingleRequestResponseDto.toSingleRequestResponse(): SingleRequestResponse {
    val pharmacyId=if (user.pharmacies.isNullOrEmpty()) null
    else
        user.pharmacies?.get(0)?.pharmacyId
    return SingleRequestResponse(
        id = requestId,
        userMainInfo = UserMainInfo(
            id = user.id,
            fullName = FullName(user.firstName,user.middleName,user.lastName),
            imageUrl = user.imageUrl,
            subInfo = user.subInfo
        ),
        requestType = requestType.toRequestType(),
        state = state.toRequestState(),
        clinicMainInfo = clinic?.toClinicMainInfo(),
        requestingDateTime = requestDataTime,
        pharmacyId =pharmacyId,
    )
}
fun ClinicMainInfoDto.toClinicMainInfo()=
    ClinicMainInfo(
        clinicId = clinicId,
        name = name
    )

fun ChangeRequestStateResponseDto.toChangeRequestStateResponse()=
    ChangeRequestStateResponse(
        updatedRequest = updatedRequest
    )