package com.example.network.model.request.auth

import android.annotation.SuppressLint
import com.example.network.model.enums.RoleDto
import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequestDto(
    val role: RoleDto,
)