package com.example.data.mapper.employee_management

import com.example.model.account_management.ReactivateUserAccountResponse
import com.example.network.model.response.profile.ReactivateUserAccountResponseDto

fun ReactivateUserAccountResponseDto.toReactivateUserAccountResponse()=
    ReactivateUserAccountResponse(
        updatedData = this.updatedData
    )
