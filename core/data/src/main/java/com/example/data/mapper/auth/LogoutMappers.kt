package com.example.data.mapper.auth

import com.example.data.mapper.enums.toRoleDto
import com.example.model.auth.logout.LogoutRequest
import com.example.model.auth.logout.LogoutResponse
import com.example.network.model.request.auth.LogoutRequestDto
import com.example.network.model.response.auth.LogoutResponseDto

fun LogoutRequest.toLogoutRequestDto() =
    LogoutRequestDto(
        role = this.role.toRoleDto(),
    )

fun LogoutResponseDto.toLogoutResponse() =
    LogoutResponse(
        message = this.message
    )

