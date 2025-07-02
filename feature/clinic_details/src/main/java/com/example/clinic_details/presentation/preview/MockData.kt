package com.example.clinic_details.presentation.preview

import com.example.clinic_details.presentation.ClinicNavigationAction
import com.example.model.doctor.DoctorData
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.doctor.clinic.ClinicServiceData
import com.example.model.doctor.day_scedule.DaySchedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val mockClinic = ClinicFullData(
    clinicId = 1,
    firstAvailableTime = LocalTime.of(10,0,0),
    name = "dentist",
    providesVaccines = true,
    isDeactivated = false,
    deactivationReason = null,
    creationDate = LocalDate.of(2020,1,1),

    workDays = listOf(
        DaySchedule(
            id = 1,
            doctorId = 1,
            clinicId = 1,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.SUNDAY,
            startTime = LocalTime.of(10,0,0),
            endTime = LocalTime.of(16,0,0)
        ),
        DaySchedule(
            id = 2,
            doctorId = 1,
            clinicId = 1,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.MONDAY,
            startTime = LocalTime.of(10,0,0),
            endTime = LocalTime.of(16,0,0)
        ),
        DaySchedule(
            id = 3,
            doctorId = 1,
            clinicId = 1,
            pharmacyId = null,
            dayOfWeek = DayOfWeek.TUESDAY,
            startTime = LocalTime.of(10,0,0),
            endTime = LocalTime.of(16,0,0)
        ),
    ),
    activeDoctors = listOf(
        DoctorData(
            id = 1,
            firstName = "Ahmad",
            lastName = "Majed",
            imageUrl = null,
            speciality = "dentist"
        ),DoctorData(
            id = 2,
            firstName = "Salem",
            lastName = "Ahmad",
            imageUrl = null,
            speciality = "dentist"
        ),DoctorData(
            id = 3,
            firstName = "Mira",
            lastName = "Kamal",
            imageUrl = null,
            speciality = "dentist"
        ),DoctorData(
            id = 4,
            firstName = "Lara",
            lastName = "Joni",
            imageUrl = null,
            speciality = "dentist"
        ),
    ),
    deactivatedByUser = null,
    clinicServices = listOf(
        ClinicServiceData(
            id = 1,
            name = "Check Up",
            description = "Check Up description",
            clinicId = 1
        ),ClinicServiceData(
            id = 2,
            name = "Dental fillings",
            description = "Dental fillings description",
            clinicId = 1
        ),ClinicServiceData(
            id = 3,
            name = "tooth extraction",
            description = "tooth extraction description",
            clinicId = 1
        ),ClinicServiceData(
            id = 4,
            name = "Teeth whitening",
            description = "Teeth whitening description",
            clinicId = 1
        ),ClinicServiceData(
            id = 5,
            name = "Surgery",
            description = "Surgery description",
            clinicId = 1
        ),ClinicServiceData(
            id = 6,
            name = "Vaccine",
            description = "Vaccine description",
            clinicId = 1
        )
    )
)

val mockAction = object : ClinicNavigationAction{
    override fun navigateUp() {
    }

    override fun navigateToDoctorProfile(doctorId: Int) {

    }

    override fun navigateToInitialScreen(doctorId: Int) {
    }

    override fun navigateToVaccines(clinicId: Int) {
    }
}