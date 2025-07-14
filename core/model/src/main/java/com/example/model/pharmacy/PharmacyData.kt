package com.example.model.pharmacy

import com.example.model.auth.signup.UserData
import com.example.model.guardian.GuardianData
import java.time.LocalDate

data class PharmacyData(
    val pharmacyId: Int,
    val name: String,
    val addressGovernorate: String,
    val addressCity: String,
    val addressRegion: String,
    val addressStreet: String,
    val addressNote: String,
    val phoneNumber: Int,
    val isDeactivated: Boolean? = null,
    val deactivationReason: String? = null,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val deactivatedBy: Int,
    val pharmacist: PharmacistData
)
