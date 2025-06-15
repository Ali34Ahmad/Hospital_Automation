package com.example.network.model.dto.doctor.appointment

import com.example.network.model.dto.child.ChildDto
import com.example.network.model.dto.doctor.appointment.AppointmentTypeDto
import com.example.network.model.dto.doctor.ClinicDto
import com.example.network.model.dto.doctor.VaccineDto
import com.example.network.model.dto.user.UserDto
import com.example.network.serializer.LocalDateSerializer
import com.example.network.serializer.LocalTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class AppointmentDto(
    @SerialName("appointmentsId")
    val id: Int,

    @SerialName("recommended_visit_date")
    @Serializable(with = LocalDateSerializer::class)
    val recommendedVisitDate: LocalDate? = null,

    @SerialName("recommnded_visit_note")
    val recommendedVisitNote: String? = null,

    val state: String,

    @SerialName("medical_diagnosis")
    val medicalDiagnosis: String,

    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,

    @Serializable(with = LocalTimeSerializer::class)
    val time: LocalTime,

    val createdAt: String,
    val updatedAt: String,

    @SerialName("clinic_id")
    val clinicId: Int,

    @SerialName("vaccin_id")
    val vaccineId: Int? = null,

    @SerialName("appointment_type_id")
    val appointmentTypeId: Int,

    @SerialName("child_id")
    val childId: Int? = null,

    @SerialName("doctor_id")
    val doctorId: Int,

    @SerialName("patient_id")
    val patientId: Int? = null,

    @SerialName("appointment_type")
    val appointmentType: AppointmentTypeDto,

    val user: UserDto? = null,

    val vaccine: VaccineDto? = null,

    val clinic: ClinicDto,
    val child: ChildDto? = null
)