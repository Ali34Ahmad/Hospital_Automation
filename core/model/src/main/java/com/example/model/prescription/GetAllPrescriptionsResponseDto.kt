package com.example.model.prescription

import com.example.model.user.UserMainInfo
import java.time.LocalDateTime

data class GetAllPrescriptionsResponse(
    val data: List<PrescriptionDataFlat>,
    val doctorMainInfoDto: UserMainInfo,
)


data class PrescriptionDataFlat(
    val prescriptionId: Int,
    val code: String, // JSON has "15984" (string)
    val note: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val clinicId: Int?,
    val doctorId: Int?,
    val patientId: Int?, // Corresponds to user_id if this is for the adult patient
    val childId: Int?,

    val medicinesCount: Int,

    // Flattened Patient (User) Details
    val userId: Int?, // This is the actual patient_id from the flattened structure
    val userFirstName: String?,
    val userMiddleName: String?,
    val userLastName: String?,

    // Flattened Child Details
    // child_id is already captured above
    val childFirstName: String?,
    val childMiddleName: String?, // Or childFatherName: String?
    val childLastName: String?
    // Note: Child's imgurl and specialty are not in this flattened structure
)
