package com.example.data.mapper.employee_management

import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.account_management.DeactivateMyEmployeeAccountResponse
import com.example.network.model.request.DeactivateMyEmployeeAccountRequestDto
import com.example.network.model.response.profile.DeactivateMyEmployeeAccountResponseDto

fun DeactivateMyEmployeeAccountRequest.toDeactivateMyEmployeeAccountRequestDto() =
    DeactivateMyEmployeeAccountRequestDto(
        deactivationReason = this.deactivationReason
    )

fun DeactivateMyEmployeeAccountResponseDto.toDeactivateMyEmployeeAccountResponse() =
    DeactivateMyEmployeeAccountResponse(
        updatedData = this.updatedData
    )

