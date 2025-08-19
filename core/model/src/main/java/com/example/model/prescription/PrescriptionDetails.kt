package com.example.model.prescription

import com.example.model.medicine.DetailedPrescriptionMedicine
import com.example.model.user.UserMainInfo

data class PrescriptionDetails(
    val prescription: Prescription,
    val doctorMainInfo: UserMainInfo,
    val patientMainInfo: UserMainInfo?,
    val childMainInfo: UserMainInfo?,
    val medicines: List<DetailedPrescriptionMedicine>,
)
