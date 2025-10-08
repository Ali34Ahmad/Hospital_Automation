package com.example.edit_doctor_profile.presentation.preview

import com.example.edit_doctor_profile.presentation.EditDoctorProfileNavigationActions
import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.ui.fake.createWorkDaysList

val mockNavigationActions = object : EditDoctorProfileNavigationActions{
    override fun navigateUp() {}
}

val mockWorkdays = createWorkDaysList()
val mockWorkday = mockWorkdays[1]
val mockAppointmentType = AppointmentTypeSummaryData(
    id = 1,
    name = "Check up",
    duration = 30,
    description = "fake description for check up appointment type"
)

val mockAppointmentTypes = listOf(
    mockAppointmentType,
)
