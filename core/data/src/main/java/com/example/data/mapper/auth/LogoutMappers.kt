package com.example.data.mapper.auth

import com.example.model.auth.logout.LogoutResponse
import com.example.network.model.response.LogoutResponseDto

fun LogoutResponseDto.toLogoutResponse() =
    LogoutResponse(
        message = this.message
    )