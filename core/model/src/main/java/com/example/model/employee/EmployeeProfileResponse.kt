package com.example.model.employee

import com.example.model.address.Address
import com.example.model.enums.Gender
import com.example.model.enums.Role
import com.example.model.user.FullName

data class EmployeeProfileResponse(
    val profile: EmployeeProfile,
    val isAccessedByOwner: Boolean,
)

data class EmployeeProfile(
    val userId: Int,
    val role: Role,
    val email: String,
    val fullName: FullName,
    val verifiedResetPassword: Boolean?,
    val verifiedAccount: Boolean?,
    val phoneNumber: String,
    val address: Address,
    val imageUrl: String?,
    val documentsUrl: String?,
    val gender: Gender?,
    val isSuspended: Boolean,
    val suspendingReason: String?,
    val isResigned: Boolean,
    val createdAt: String?,
    val updatedAt: String?,
    val resignedBy: Int?,
    val suspendedBy: Int?,
    val acceptedBy: Int?
)
