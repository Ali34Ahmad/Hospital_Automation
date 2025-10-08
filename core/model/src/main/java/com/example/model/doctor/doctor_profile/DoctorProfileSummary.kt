package com.example.model.doctor.doctor_profile

import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.model.doctor.workday.WorkDaySummaryData
import com.example.model.user.FullName

data class DoctorProfileSummary(
    val fullName: FullName,
    val availabilitySchedule: List<WorkDaySummaryData>,
    val appointmentTypes: List<AppointmentTypeSummaryData>,
    val imageUrl: String?,
)
