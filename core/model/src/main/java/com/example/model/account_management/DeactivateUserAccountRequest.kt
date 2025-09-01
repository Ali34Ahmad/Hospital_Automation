package com.example.model.account_management

import com.example.model.enums.Role

data class DeactivateUserAccountRequest(
    val deactivationReason: String,
    val role: Role,
    val userId:Int?,
)
