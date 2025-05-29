package com.example.model.auth.signup



data class RegistrationResponse(
    val data: UserData
)

data class UserData(
    val userId: Int,
    val role: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val middleName: String?, // Can be null
    val verifiedResetPassword: Boolean,
    val verifiedAccount: Boolean,
    val phoneNumber: String,
    val addressGovernorate: String?, // Can be null
    val addressCity: String?, // Can be null
    val addressRegion: String?, // Can be null
    val addressStreet: String?, // Can be null
    val addressNote: String?, // Can be null
    val specialty: String?, // Can be null
    val imageUrl: String?, // Can be null
    val medicalLicenseImgUrl: String?, // Can be null
    val gender: String,
    val isSuspended: Boolean,
    val suspendingReason: String?, // Can be null
    val isResigned: Boolean,
    val workStartDate: String?,
    val workEndDate: String?,
    val createdAt: String,
    val updatedAt: String,
    val clinicId: Int?,
    val resignedBy: Int?,
    val suspendedBy: Int?,
    val acceptedBy: Int?
)