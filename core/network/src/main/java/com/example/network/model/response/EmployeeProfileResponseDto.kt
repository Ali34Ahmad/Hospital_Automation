package com.example.network.model.response

import com.example.network.model.enums.GenderDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeProfileResponseDto(
    val profile: EmployeeProfileDto
)

@Serializable
data class EmployeeProfileDto(
    val userId: Int,
    val role: String,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("middle_name")
    val middleName: String?,
    @SerialName("verified_reset_password")
    val verifiedResetPassword: Boolean,
    @SerialName("verified_account")
    val verifiedAccount: Boolean,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("address_governorate")
    val addressGovernorate: String?,
    @SerialName("address_city")
    val addressCity: String?,
    @SerialName("address_region")
    val addressRegion: String?,
    @SerialName("address_street")
    val addressStreet: String?,
    @SerialName("address_note")
    val addressNote: String?,
    @SerialName("imgurl")
    val imageUrl: String?,
    @SerialName("medical_license_img_url")
    val documentsUrl: String?,
    val gender: GenderDto?,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("suspending_reason")
    val suspendingReason: String?,
    @SerialName("is_resigned")
    val isResigned: Boolean,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("resigned_by")
    val resignedBy: Int?,
    @SerialName("suspended_by")
    val suspendedBy: Int?,
    @SerialName("accepted_by")
    val acceptedBy: Int?
)