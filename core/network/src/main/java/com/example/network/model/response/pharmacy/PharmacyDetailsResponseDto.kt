package com.example.network.model.response.pharmacy

import com.example.network.model.dto.workday.WorkDayDto
import com.example.network.serializer.LocalDateSerializer
import com.example.network.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime


@Serializable
data class PharmacyDetailsResponseDto(
    val data: PharmacyDataDto
)

@Serializable
data class PharmacyDataDto(
    val pharmacyId: Int,
    @SerialName("ph_name")
    val phName: String,
    @SerialName("address_governorate")
    val addressGovernorate: String,
    @SerialName("address_city")
    val addressCity: String,
    @SerialName("address_region")
    val addressRegion: String,
    @SerialName("address_street")
    val addressStreet: String,
    @SerialName("address_note")
    val addressNote: String?,
    val phoneNumber: String,
    @SerialName("is_deactivated")
    val isDeactivated: Boolean?, // Can be null
    @SerialName("deactivation_reason")
    val deactivationReason: String?,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("contract_start_date")
    val contractStartDate:LocalDate? = null,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("contract_end_date")
    val contractEndDate: LocalDate? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt:LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime,
    @SerialName("deactivated_by")
    val deactivatedBy: Int?,
    @SerialName("pharmacist_id")
    val pharmacistId: Int,
    @SerialName("user")
    val userWithAddress: UserWithAddressDto,
    @SerialName("work_days")
    val workDays:List<WorkDayDto>
)

@Serializable
data class UserWithAddressDto(
    val userId: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("middle_name")
    val middleName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("imgurl")
    val imageUrl: String,
    @SerialName("address_governorate")
    val addressGovernorate: String,
    @SerialName("address_city")
    val addressCity: String,
    @SerialName("address_region")
    val addressRegion: String,
    @SerialName("address_street")
    val addressStreet: String,
    @SerialName("address_note")
    val addressNote: String?,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("is_resigned")
    val isResigned: Boolean,
    @SerialName("accepted_by")
    val acceptedBy: Int?,
)
