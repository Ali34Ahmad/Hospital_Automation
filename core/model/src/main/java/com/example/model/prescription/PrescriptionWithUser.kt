package com.example.model.prescription

import com.example.model.user.UserMainInfo

data class PrescriptionWithUser(
    val prescription: Prescription,
    val userMainInfo: UserMainInfo?,
    val childMainInfo: UserMainInfo?,
)