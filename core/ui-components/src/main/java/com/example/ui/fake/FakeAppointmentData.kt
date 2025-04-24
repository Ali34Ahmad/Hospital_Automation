package com.example.ui.fake

import com.example.constants.enums.AppointmentState
import com.example.model.Appointment
import java.time.LocalDate
import java.time.LocalDateTime

fun createSampleAppointments(): List<Appointment> {
    return listOf(
        Appointment(
            id = 123,
            patientId = 456,
            patientName = "Ali Ahmad",
            clinicName = "City Health Clinic",
            doctorId = 789,
            childId = null, // Assuming this is an adult appointment
            dateTime = LocalDateTime.of(2024, 10, 26, 10, 30), // October 26, 2024, 10:30 AM
            appointmentType = "Vaccination",
            state = AppointmentState.UPCOMING,
        ),
        Appointment(
            id = 456,
            patientId = 789,
            patientName = "Mariam Saoud",
            clinicName = "Family Wellness Center",
            doctorId = 101,
            vaccineName = "MMR Vaccine",
            childId = 123, // Assuming this is a child appointment
            dateTime = LocalDateTime.of(2024, 11, 15, 14, 0), // November 15, 2024, 2:00 PM
            appointmentType = "Child Vaccination",
            recommendedVisitDate =LocalDate.now(),
            recommendedVisitNote = "This is a recommended visit note",
            state = AppointmentState.PASSED,
            medicalDiagnosis = "This is really bad situation"
        ),
        Appointment(
            id = 789,
            patientId = 112,
            patientName = "Mariam Saoud",
            clinicName = "City Health Clinic",
            doctorId = 789,
            vaccineName = null,
            childId = null,
            dateTime = LocalDateTime.of(2024, 12, 5, 9, 0), // December 5, 2024, 9:00 AM
            appointmentType = "Checkup",
            recommendedVisitDate = null,
            recommendedVisitNote = null,
            state = AppointmentState.MISSED,
        )
        // Add more appointments as needed
    )
}