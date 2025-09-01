package com.example.data.mapper.employee_management

import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.network.model.request.DeactivateMyEmployeeAccountRequestDto
import com.example.network.model.response.profile.DeactivateMyEmployeeAccountResponseDto

fun DeactivateUserAccountRequest.toDeactivateMyEmployeeAccountRequestDto() =
    DeactivateMyEmployeeAccountRequestDto(
        deactivationReason = this.deactivationReason
    )

fun DeactivateMyEmployeeAccountResponseDto.toDeactivateMyEmployeeAccountResponse() =
    DeactivateUserAccountResponse(
        updatedData = this.updatedData
    )

