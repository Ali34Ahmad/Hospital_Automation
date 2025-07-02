package com.example.data.mapper.employee_management

import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.network.model.response.profile.CheckPermissionResponseDto

fun CheckPermissionResponseDto.toCheckEmployeePermissionResponse() =
    CheckEmployeePermissionResponse(
        permissionGranted = this.permissionGranted
    )