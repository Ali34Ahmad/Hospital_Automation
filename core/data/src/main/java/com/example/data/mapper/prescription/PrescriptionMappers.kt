package com.example.data.mapper.prescription

import com.example.data.mapper.user.toUserMainInfo
import com.example.model.prescription.Prescription
import com.example.model.prescription.PrescriptionDetails
import com.example.model.prescription.PrescriptionMedicineData
import com.example.model.prescription.PrescriptionWithUser
import com.example.model.user.FullName
import com.example.model.user.UserMainInfo
import com.example.network.model.dto.prescription.PrescriptionMedicineDto
import com.example.network.model.response.prescription.PrescriptionDataFlatDto
import com.example.network.model.response.prescription.PrescriptionDetailsWithMedicinesDto

fun PrescriptionMedicineData.toPrescriptionMedicineDto() = PrescriptionMedicineDto(
    medicineId = medicineId,
    howToTake = howToTake,
)

fun PrescriptionMedicineDto.toPrescriptionMedicineData() = PrescriptionMedicineData(
    medicineId = medicineId,
    howToTake = howToTake
)

fun PrescriptionDetailsWithMedicinesDto.toPrescriptionDetails() =
    PrescriptionDetails(
        prescription = Prescription(
            patientId = prescriptionDto.patientId,
            childId = prescriptionDto.childId,
            doctorId = prescriptionDto.doctorId,
            code = prescriptionDto.code,
            numberOfMedicines = prescriptionDto.numberOfMedicines,
            createdAt = prescriptionDto.createdAt.toLocalDate(),
            id = prescriptionDto.id,
        ),
        doctorMainInfo = prescriptionDto.doctorMainInfoDto.toUserMainInfo(),
        patientMainInfo = prescriptionDto.patientMainInfoDto?.toUserMainInfo(),
        childMainInfo = prescriptionDto.childMainInfoDto?.toUserMainInfo(),
        medicines = medicinesDto.map { it.toPrescriptionMedicineMainInfo() }
    )


fun PrescriptionDataFlatDto.toPrescriptionWithUser(): PrescriptionWithUser {
    val child =
        childId?.let {
            UserMainInfo(
                id = childId,
                fullName = FullName(childFirstName ?: "", childMiddleName, childLastName ?: ""),
                imageUrl = null,
                subInfo = null
            )
        }
    val user =
        userId?.let {
            UserMainInfo(
                id = userId,
                fullName = FullName(userFirstName ?: "", userMiddleName, userLastName ?: ""),
                imageUrl = null,
                subInfo = null,
            )
        }

    return PrescriptionWithUser(
        prescription = Prescription(
            id = prescriptionId,
            patientId = patientId,
            childId = childId,
            doctorId = doctorId,
            code = code,
            numberOfMedicines = medicinesCount,
            createdAt = createdAt.toLocalDate()
        ),
        userMainInfo = user,
        childMainInfo = child
    )
}