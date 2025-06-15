package com.example.network.model.response.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    @SerialName("userId")
    val userId: Int,
    val role: String,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("middle_name")
    val middleName: String? = null,
    val password: String, // Note: Consider if you really need to expose this
    @SerialName("verified_reset_password")
    val verifiedResetPassword: Boolean,
    @SerialName("verified_account")
    val verifiedAccount: Boolean,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("address_governorate")
    val addressGovernorate: String? = null,
    @SerialName("address_city")
    val addressCity: String? = null,
    @SerialName("address_region")
    val addressRegion: String? = null,
    @SerialName("address_street")
    val addressStreet: String? = null,
    @SerialName("address_note")
    val addressNote: String? = null,
    val specialty: String? = null,
    val imgurl: String? = null,
    @SerialName("medical_license_img_url")
    val medicalLicenseImgUrl: String? = null,
    val gender: String? = null,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("suspending_reason")
    val suspendingReason: String? = null,
    @SerialName("is_resigned")
    val isResigned: Boolean,
    @SerialName("work_start_date")
    val workStartDate: String? = null,
    @SerialName("work_end_date")
    val workEndDate: String? = null,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("clinic_id")
    val clinicId: Int? = null,
    @SerialName("resigned_by")
    val resignedBy: Int? = null,
    @SerialName("suspended_by")
    val suspendedBy: Int? = null,
    @SerialName("accepted_by")
    val acceptedBy: Int? = null
)