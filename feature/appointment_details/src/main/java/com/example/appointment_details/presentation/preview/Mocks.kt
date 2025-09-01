package com.example.appointment_details.presentation.preview

import com.example.appointment_details.presentation.AppointmentDetailsNavigationActions
import com.example.model.child.ChildData
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.model.doctor.appointment.VaccineSummaryData
import com.example.model.doctor.clinic.ClinicData
import com.example.model.guardian.GuardianData
import java.time.LocalDate
import java.time.LocalTime

val mockNavigationActions = object : AppointmentDetailsNavigationActions{
    override fun navigateUp() {

    }

    override fun navigateToDepartmentDetails(deptId: Int) {
    }

    override fun navigateToVaccineDetails(vaccineId: Int) {
    }


    override fun navigateToAddMedicalDiagnosis(
        appointmentId: Int,
        fullName: String,
        patientId: Int?,
        childId: Int?,
        canSkip: Boolean,
    ) {

    }

    override fun navigateToGuardianProfile(guardianId: Int) {

    }

}

val mockAppointment = AppointmentData(
    id = 1,
    recommendedVisitDate = LocalDate.now().plusDays(10),
    recommendedVisitNote = "try to come on time",
    state = AppointmentState.UPCOMMING,
    medicalDiagnosis = "medical diagnosis here",
    date = LocalDate.now(),
    time = LocalTime.now(),
    clinicId = 1,
    vaccineId =1,
    appointmentTypeId = 1,
    childId =null,
    doctorId = 1,
    patientId = 1,
    appointmentType = AppointmentTypeData(
        id = 1,
        name = "Vaccine",
        standardDurationInMinutes = 15,
        description ="taking a vaccine",
        doctorId = 1
    ),
    user = GuardianData(id = 1, img = "fake","Sara Salem Sara"),
    vaccine = null,
    clinic = ClinicData(
        clinicId = 1,
        name = "Vaccine Clinic"
    ),
    child =null
)
val mockVaccine =  AppointmentData(
    id = 1,
    recommendedVisitDate = LocalDate.now().plusDays(10),
    recommendedVisitNote = "try to come on time",
    state = AppointmentState.UPCOMMING,
    medicalDiagnosis = "medical diagnosis here",
    date = LocalDate.now(),
    time = LocalTime.now(),
    clinicId = 1,
    vaccineId =1,
    appointmentTypeId = 1,
    childId =1,
    doctorId = 1,
    patientId = 1,
    appointmentType = AppointmentTypeData(
        id = 1,
        name = "Vaccine",
        standardDurationInMinutes = 15,
        description ="taking a vaccine",
        doctorId = 1
    ),
    user = null,
    vaccine = VaccineSummaryData(
        vaccineId = 1,
        name = "HBI"
    ),
    clinic = ClinicData(
        clinicId = 1,
        name = "Vaccine Clinic"
    ),
    child = ChildData(
        id = 1,
        firstName = "Ali",
        lastName = "Ahmad",
        fatherFirstName = "Shafeek",
        fatherLastName = "Ahmad",
        motherFirstName = "Sara",
        motherLastName = "Sara",
        dateOfBirth = LocalDate.now()
    )
)