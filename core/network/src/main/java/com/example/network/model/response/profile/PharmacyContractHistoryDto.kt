package com.example.network.model.response.profile

import com.example.network.model.response.user.UserMainInfoDto
import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate


@Serializable
data class PharmacyContractHistoryDto(
    @SerialName("current_pharmacy")
    val pharmacyInHistoryDto: PharmacyInHistoryDto? = null,
    @SerialName("accepted_by")
    val acceptedBy: UserMainInfoDto? = null,
    @SerialName("fileSize")
    val fileSize: Long? = null,
    @SerialName("deactivatedBy")
    val deactivatedBy: UserMainInfoDto? = null
)

@Serializable
data class PharmacyInHistoryDto(
    @SerialName("pharmacyId")
    val id: Int? = null,
    @SerialName("ph_name")
    val phName: String? = null,

    @SerialName("is_deactivated")
    val isDeactivated: Boolean? = null,
    @SerialName("deactivation_reason")
    val deactivationReason: String? = null,

    @Serializable(with = LocalDateSerializer::class)
    @SerialName("contract_start_date")
    val contractStartDate: LocalDate? = null,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("contract_end_date")
    val contractEndDate: LocalDate? = null,

    @SerialName("deactivated_by")
    val deactivatedBy: String? = null,
    @SerialName("pharmacist_id")
    val pharmacistId: Int? = null,
    @SerialName("user")
    val pharmacist: PharmacistInHistoryDataDto? = null
)

@Serializable
data class PharmacistInHistoryDataDto(
    @SerialName("userId")
    val userId: Int? = null,
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("middle_name")
    val middleName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("accepted_by")
    val acceptedBy: Int? = null,
    @SerialName("medical_license_img_url")
    val pharmacyFileUrl: String? = null
)