package com.example.data.mapper.employee_management

import com.example.model.account_management.ReactivateMyEmployeeAccountResponse
import com.example.network.model.response.profile.ReactivateMyEmployeeAccountResponseDto

fun ReactivateMyEmployeeAccountResponseDto.toReactivateMyEmployeeAccountResponse()=
    ReactivateMyEmployeeAccountResponse(
        updatedData = this.updatedData
    )
