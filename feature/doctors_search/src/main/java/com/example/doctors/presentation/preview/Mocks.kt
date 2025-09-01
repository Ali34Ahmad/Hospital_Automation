package com.example.doctors.presentation.preview

import androidx.paging.PagingData
import com.example.doctors.presentation.DoctorsSearchNavigationActions
import com.example.model.doctor.DoctorData
import com.example.model.guardian.PagedData
import kotlinx.coroutines.flow.flowOf

val mockActions = object : DoctorsSearchNavigationActions{
    override fun navigateUp() {

    }

    override fun navigateToDoctorProfile(doctorId: Int) {

    }

    override fun navigateToAdminProfile() {
    }

    override fun navigateToVaccines() {
    }

    override fun navigateToNotifications() {

    }

    override fun navigateToPrescriptions() {

    }

    override fun navigateToMedicalRecords() {
    }

    override fun navigateToVaccineTable() {

    }
}

private val doctorsList = listOf(
    DoctorData(
        id = 1,
        firstName = "Ali",
        lastName = "Mazen",
        imageUrl = "",
        speciality = "dentist",
        middleName = "Elias",

    ),
    DoctorData(
        id = 2,
        firstName = "Ali",
        middleName = "Elias",
        lastName = "Samira",
        imageUrl = "",
        speciality = "dentist"
    ),
    DoctorData(
        id = 3,
        firstName = "Milad",
        lastName = "Mazen",
        imageUrl = "",
        middleName = "Elias",
        speciality = "dentist"
    ),
)

val doctorsFlow = flowOf(
    PagingData.from(doctorsList)
)
