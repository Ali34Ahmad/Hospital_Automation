package com.example.network.model.response

import android.annotation.SuppressLint
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegistrationResponse(
    val data: UserData // This property will hold the nested user details
)


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class UserData(
    @SerialName("userId")
    val userId: Int,
    val role: String,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("middle_name")
    val middleName: String?, // Can be null
    @SerialName("verified_reset_password")
    val verifiedResetPassword: Boolean,
    @SerialName("verified_account")
    val verifiedAccount: Boolean,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("address_governorate")
    val addressGovernorate: String?, // Can be null
    @SerialName("address_city")
    val addressCity: String?, // Can be null
    @SerialName("address_region")
    val addressRegion: String?, // Can be null
    @SerialName("address_street")
    val addressStreet: String?, // Can be null
    @SerialName("address_note")
    val addressNote: String?, // Can be null
    val specialty: String?, // Can be null
    val imgurl: String?, // Can be null
    @SerialName("medical_license_img_url")
    val medicalLicenseImgUrl: String?, // Can be null
    val gender: String,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("suspending_reason")
    val suspendingReason: String?, // Can be null
    @SerialName("is_resigned")
    val isResigned: Boolean,
    @SerialName("work_start_date")
    val workStartDate: String?,
    @SerialName("work_end_date")
    val workEndDate: String?,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("clinic_id")
    val clinicId: Int?,
    @SerialName("resigned_by")
    val resignedBy: Int?,
    @SerialName("suspended_by")
    val suspendedBy: Int?,
    @SerialName("accepted_by")
    val acceptedBy: Int?
)

//data class RegistrationResponse(
//    @SerialName("is_resigned")
//    val isResigned: Boolean,
//    @SerialName("userId")
//    val userId: Int,
//    val role: String,
//    val email: String,
//    @SerialName("first_name")
//    val firstName: String,
//    @SerialName("last_name")
//    val lastName: String,
//    @SerialName("middle_name")
//    val middleName: String?, // Can be null
//    val password: String, // Note: You might not always want to expose the password in the response
//    @SerialName("phone_number")
//    val phoneNumber: String,
//    val gender: String,
//    val updatedAt: String,
//    val createdAt: String
//)
//
