package com.example.data.mapper.employee_management

import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.account_management.DeactivateUserAccountResponse
import com.example.network.model.request.DeactivateAccountRequestDto
import com.example.network.model.response.profile.DeactivateUserAccountResponseDto

fun DeactivateUserAccountRequest.toDeactivateUserAccountRequestDto() =
    DeactivateAccountRequestDto(
        deactivationReason = this.deactivationReason
    )

fun DeactivateUserAccountResponseDto.toDeactivateUserAccountResponse() =
    DeactivateUserAccountResponse(
        updatedData = this.updatedData
    )

