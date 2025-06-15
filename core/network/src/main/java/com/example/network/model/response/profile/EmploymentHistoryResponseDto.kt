package com.example.network.model.response.profile

import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class EmploymentHistoryResponseDto(
    val currentUser: UserDetailsDto,

    @SerialName("fileSize")
    val employeeDocumentsFileSize: Long?,

    @SerialName("resigned_by")
    val resignedBy: UserReferenceDto?,

    @SerialName("suspended_by")
    val suspendedBy: UserReferenceDto?,

    @SerialName("accepted_by")
    val acceptedBy: UserReferenceDto?
)

@Serializable
data class UserDetailsDto(
    @SerialName("userId")
    val userId: Int,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("middle_name")
    val middleName: String?,

    @SerialName("last_name")
    val lastName: String,


    @SerialName("is_resigned")
    val isResigned: Boolean,

    @SerialName("accepted_by")
    val acceptedBy: Int?,

    @SerialName("suspended_by")
    val suspendedBy: Int?,

    @SerialName("resigned_by")
    val resignedBy: Int?,

    @SerialName("imgurl")
    val imageUrl: String?,

    @Serializable(with = LocalDateSerializer::class)
    @SerialName("work_start_date")
    val workStartDate: LocalDate?,

    @Serializable(with = LocalDateSerializer::class)
    @SerialName("work_end_date")
    val workEndDate: LocalDate?,

    @SerialName("medical_license_img_url")
    val documentsFileUrl: String?,
)

@Serializable
data class UserReferenceDto(
    @SerialName("userId")
    val userId: Int,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("middle_name")
    val middleName: String?,

    @SerialName("last_name")
    val lastName: String
)
