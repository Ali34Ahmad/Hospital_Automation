package com.example.model.account_management

import com.example.model.enums.Role

data class DeactivateMyEmployeeAccountRequest(
    val deactivationReason: String,
    val role: Role,
)
