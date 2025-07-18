package com.example.network.model.dto.pharmacy

import com.example.network.model.dto.user.UserDto
import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class PharmacyDto(
    val pharmacyId: Int,
    val name: String,
    @SerialName("address_governorate")
    val addressGovernorate: String,
    @SerialName("address_city")
    val addressCity: String,
    @SerialName("address_region")
    val addressRegion: String,
    @SerialName("address_street")
    val addressStreet: String,
    @SerialName("address_note")
    val addressNote: String? = null,
    @SerialName("phoneNumber")
    val phone: String,
    @SerialName("is_deactivated")
    val isDeactivated: Boolean? = null,
    @SerialName("deactivation_reason")
    val deactivationReason: String? = null,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("contract_start_date")
    val startDate: LocalDate?,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("contract_end_date")
    val endDate: LocalDate?,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("deactivated_by")
    val deactivatedBy: Int? = null,
    @SerialName("pharmacist_id")
    val pharmacistId: Int,
    val user: UserDto
)