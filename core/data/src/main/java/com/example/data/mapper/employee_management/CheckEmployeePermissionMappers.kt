package com.example.data.mapper.employee_management

import com.example.model.account_management.CheckEmployeePermissionResponse
import com.example.network.model.response.profile.CheckEmployeePermissionResponseDto

fun CheckEmployeePermissionResponseDto.toCheckEmployeePermissionResponse() =
    CheckEmployeePermissionResponse(
        permissionGranted = this.permissionGranted
    )