package com.example.model.guardian

data class GuardianFullData(
    val userId: Int,
    val email: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val phoneNumber: String,
    val addressGovernorate: String?,
    val addressCity: String?,
    val addressRegion: String?,
    val addressStreet: String?,
    val addressNote: String?,
    val gender: String?,
    val imgUrl: String?,
    val isSuspended: Boolean = false,
    val suspendingReason: String? = null,
){
    val fullName: String
        get() = "$firstName $lastName"
    val fullAddress : String
        get() = "$addressCity-$addressRegion-$addressStreet"
}