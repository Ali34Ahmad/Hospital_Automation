package com.example.data.mapper.medical_record

import com.example.model.medical_record.MedicalRecord
import com.example.model.user.FullName
import com.example.network.model.dto.medical_record.MedicalRecordDto

fun MedicalRecordDto.toMedicalRecord()=
    MedicalRecord(
        patientId = patientId,
        patientImageUrl = patientImageUrl,
        childId = childId,
        fullName = FullName(firstName,middleName,lastName),
        numberOfAppointments = numberOfAppointments,
        numberOfPrescriptions = numberOfPrescriptions
    )