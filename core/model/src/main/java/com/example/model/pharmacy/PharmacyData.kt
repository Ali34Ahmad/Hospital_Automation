package com.example.model.pharmacy

import java.time.LocalDate

data class PharmacyData(
    val pharmacyId: Int,
    val name: String,
    val addressGovernorate: String,
    val addressCity: String,
    val addressRegion: String,
    val addressStreet: String,
    val addressNote: String?,
    val phoneNumber: String,
    val isDeactivated: Boolean,
    val deactivationReason: String?,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val deactivatedBy: Int?,
    val pharmacist: PharmacistData
)
