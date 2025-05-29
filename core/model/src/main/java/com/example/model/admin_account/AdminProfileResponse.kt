package com.example.model.admin_account

import com.example.model.enums.Gender
import com.example.model.user.FullName

data class AdminProfileResponse(
    val admin: AdminProfile
)

data class AdminProfile(
    val userId: Int,
    val email: String,
    val fullName: FullName,
    val phoneNumber: String,
    val addressGovernorate: String?,
    val addressCity: String?,
    val addressRegion: String?,
    val addressStreet: String?,
    val addressNote: String?,
    val gender: Gender?,
    val isSuspended: Boolean,
    val isResigned: Boolean
)