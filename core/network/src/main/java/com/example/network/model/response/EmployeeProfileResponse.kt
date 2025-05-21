package com.example.network.model.response

import com.example.network.constants.Gender
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeProfileResponse(
    val profile: EmployeeProfile
)

@Serializable
data class EmployeeProfile(
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
//    val specialty: String?,
    @SerialName("imgurl")
    val imageUrl: String?,
    @SerialName("medical_license_img_url")
    val documentsUrl: String?,
    val gender: Gender?,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("suspending_reason")
    val suspendingReason: String?,
    @SerialName("is_resigned")
    val isResigned: Boolean,
//    @SerialName("work_start_date")
//    val workStartDate: String?,
//    @SerialName("work_end_date")
//    val workEndDate: String?,
    val createdAt: String,
    val updatedAt: String,
//    @SerialName("clinic_id")
//    val clinicId: Int?,
    @SerialName("resigned_by")
    val resignedBy: Int?,
    @SerialName("suspended_by")
    val suspendedBy: Int?,
    @SerialName("accepted_by")
    val acceptedBy: Int?
)