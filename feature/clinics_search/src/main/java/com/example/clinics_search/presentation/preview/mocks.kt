package com.example.clinics_search.presentation.preview

import androidx.paging.PagingData
import com.example.clinics_search.presentation.ClinicsSearchNavigationActions
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.doctor.day_scedule.DaySchedule
import kotlinx.coroutines.flow.flowOf
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

val mockNavigationActions = object : ClinicsSearchNavigationActions{
    override fun navigateToDepartmentDetails(clinicId: Int) {

    }

    override fun navigateToDoctorProfile() {
    }

    override fun navigateToAdminProfile() {

    }

    override fun navigateToNotifications() {
    }

    override fun navigateToMedicalRecords() {
    }

    override fun navigateToPrescriptions() {
    }

    override fun navigateToVaccines() {
    }

    override fun navigateToVaccineTable() {

    }

    override fun navigateToCreateNewClinic() {

    }
}
val mockDays = listOf(
    DaySchedule(
        id = 1,
        doctorId = 1,
        clinicId = 1,
        pharmacyId = null,
        dayOfWeek = DayOfWeek.SATURDAY,
        startTime = LocalTime.now(),
        endTime =LocalTime.now().plusHours(4)
    ), DaySchedule(
        id = 2,
        doctorId = null,
        clinicId = 1,
        pharmacyId = 1,
        dayOfWeek = DayOfWeek.MONDAY,
        startTime = LocalTime.now(),
        endTime = LocalTime.of(4,0,0)
    ), DaySchedule(
        id = 3,
        doctorId = 1,
        clinicId = 1,
        pharmacyId = null,
        dayOfWeek = DayOfWeek.TUESDAY,
        startTime = LocalTime.now(),
        endTime = LocalTime.of(4,0,0)
    ), DaySchedule(
        id = 4,
        doctorId = 1,
        clinicId = 1,
        pharmacyId = null,
        dayOfWeek = DayOfWeek.WEDNESDAY,
        startTime = LocalTime.now(),
        endTime = LocalTime.of(4,0,0)
    ), DaySchedule(
        id = 5,
        doctorId = 1,
        clinicId = 1,
        pharmacyId = null,
        dayOfWeek = DayOfWeek.THURSDAY,
        startTime = LocalTime.now(),
        endTime = LocalTime.of(4,0,0)
    )
)
val mockClinicsList = listOf(
    ClinicFullData(
        clinicId = 1,
        firstAvailableTime = LocalTime.now(),
        name = "Cardiac Surgery Clinic",
        providesVaccines = false,
        isDeactivated = false,
        deactivationReason = null,
        creationDate = LocalDate.of(2020, 10, 10),
        closingDate = null,
        deactivatedById = 1,
        workDays = mockDays,
        activeDoctors = emptyList(),
        deactivatedByUser = null,
        clinicServices = emptyList(),
    ),ClinicFullData(
        clinicId = 2,
        firstAvailableTime = LocalTime.now(),
        name = "Orthopedic Department",
        providesVaccines = false,
        isDeactivated = true,
        deactivationReason = null,
        creationDate = LocalDate.of(2020,10,10),
        closingDate = null,
        deactivatedById = 1,
        workDays = mockDays,
        activeDoctors = emptyList(),
        deactivatedByUser = null,
        clinicServices = emptyList(),
    ),ClinicFullData(
        clinicId = 3,
        firstAvailableTime = LocalTime.now(),
        name = "Vaccine Department",
        providesVaccines = true,
        isDeactivated = false,
        deactivationReason = null,
        creationDate = LocalDate.of(2020,10,10),
        closingDate = null,
        deactivatedById = 1,
        workDays = mockDays,
        activeDoctors = emptyList(),
        deactivatedByUser = null,
        clinicServices = emptyList(),
    ),ClinicFullData(
        clinicId = 4,
        firstAvailableTime = LocalTime.now(),
        name = "Emergency Department",
        providesVaccines = false,
        isDeactivated = true,
        deactivationReason = "due to Ali Ahmad",
        creationDate = LocalDate.of(2020,10,10),
        closingDate = LocalDate.of(2025,2,1),
        deactivatedById = null,
        workDays = mockDays,
        activeDoctors = emptyList(),
        deactivatedByUser = null,
        clinicServices = emptyList(),
    ),
)
val clinicsFlow = flowOf(
    PagingData.from(mockClinicsList)
)