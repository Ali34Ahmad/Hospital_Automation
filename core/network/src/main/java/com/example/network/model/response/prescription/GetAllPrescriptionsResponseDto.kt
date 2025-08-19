package com.example.network.model.response.prescription

import com.example.network.model.response.NetworkPagination
import com.example.network.model.response.user.UserMainInfoDto
import com.example.network.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class GetAllPrescriptionsResponseDto(
    val data: List<PrescriptionDataFlatDto>,
    @SerialName("doctor")
    val doctorMainInfoDto: UserMainInfoDto,
    val pagination: NetworkPagination
)


@Serializable
data class PrescriptionDataFlatDto(
    @SerialName("prescriptionsId")
    val prescriptionId: Int,
    @SerialName("code")
    val code: String, // JSON has "15984" (string)
    @SerialName("note")
    val note: String?,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime,
    @SerialName("clinic_id")
    val clinicId: Int?,
    @SerialName("doctor_id")
    val doctorId: Int?,
    @SerialName("patient_id")
    val patientId: Int?, // Corresponds to user_id if this is for the adult patient
    @SerialName("child_id")
    val childId: Int?,

    @SerialName("medicines_count")
    val medicinesCount: Int,

    // Flattened Patient (User) Details
    @SerialName("user_id")
    val userId: Int?, // This is the actual patient_id from the flattened structure
    @SerialName("user_first_name")
    val userFirstName: String?,
    @SerialName("user_middle_name")
    val userMiddleName: String?,
    @SerialName("user_last_name")
    val userLastName: String?,

    // Flattened Child Details
    // child_id is already captured above
    @SerialName("child_first_name")
    val childFirstName: String?,
    @SerialName("child_father_name") // Assuming this is middle name for consistency, or keep as father_name
    val childMiddleName: String?, // Or childFatherName: String?
    @SerialName("child_last_name")
    val childLastName: String?
    // Note: Child's imgurl and specialty are not in this flattened structure
)
