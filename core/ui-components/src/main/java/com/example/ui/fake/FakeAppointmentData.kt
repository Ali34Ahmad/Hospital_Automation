package com.example.ui.fake


import com.example.model.age.Age
import com.example.model.child.ChildData
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.model.doctor.clinic.ClinicData
import com.example.model.doctor.vaccine.VaccineData
import com.example.model.enums.AgeUnit
import com.example.model.guardian.GuardianData
import java.time.LocalDate
import java.time.LocalTime


private val date = LocalDate.now()
private val time = LocalTime.now()
private val clinic = ClinicData(
    clinicId = 1,
    name = "dentist"
)
private val child = ChildData(
    id = 1,
    firstName = "Mazen",
    lastName = "Ali",
    fatherFirstName = "Hadi",
    fatherLastName = "Ali",
    motherFirstName = "Hoda",
    motherLastName = "Hoda",
    dateOfBirth = "2002-10-10"
)
private val guardian =GuardianData(
    id = 1,
    img = null,
    fullName = "Ali Bassam Man"
)
private val appType =AppointmentTypeData(
    id = 1,
    name = "Check up",
    standardDurationInMinutes = 20,
    description = "check up desc",
    doctorId = 1
)
private val vaccine = VaccineData(
    id = 1,
    name = "fake vaccine",
    description = "so good for energy",
    quantity = 10,
    minAge = Age(
        value = 10,
        unit = AgeUnit.DAY
    ),
    maxAge = Age(
        value = 2,
        unit = AgeUnit.MONTH
    ),
)
fun createSampleAppointments(): List<AppointmentData> =
    listOf(
        AppointmentData(
            id = 1,
            recommendedVisitDate = date,
            recommendedVisitNote = "recommendedVisitNote",
            state = AppointmentState.PASSED,
            medicalDiagnosis = "Anemia",
            date = date,
            time = time,
            clinicId = 1,
            appointmentTypeId = 1 ,
            doctorId =1,
            patientId = 1,
            appointmentType = appType,
            user = guardian,
            vaccine = null,
            clinic = clinic,
            child = null
        ),
        AppointmentData(
            id = 1,
            recommendedVisitDate = date,
            recommendedVisitNote = "recommendedVisitNote",
            state = AppointmentState.UPCOMMING,
            medicalDiagnosis = "Anemia",
            date = date,
            time = time,
            clinicId = 1,
            vaccineId = 1,
            appointmentTypeId = 1 ,
            childId = 1,
            doctorId =1,
            patientId = null,
            appointmentType = appType,
            user = null,
            vaccine = vaccine,
            clinic = clinic,
            child = child
        ),
        AppointmentData(
            id = 1,
            recommendedVisitDate = date,
            recommendedVisitNote = "recommendedVisitNote",
            state = AppointmentState.MISSED,
            medicalDiagnosis = "Anemia",
            date = date,
            time = time,
            clinicId = 1,
            vaccineId = 1,
            appointmentTypeId = 1 ,
            childId = 1,
            doctorId =1,
            patientId = null,
            appointmentType = appType,
            user = null,
            vaccine = vaccine,
            clinic = clinic,
            child = child
        ),
        AppointmentData(
            id = 1,
            recommendedVisitDate = date,
            recommendedVisitNote = "recommendedVisitNote",
            state = AppointmentState.PENDING,
            medicalDiagnosis = "Anemia",
            date = date,
            time = time,
            clinicId = 1,
            vaccineId = 1,
            appointmentTypeId = 1 ,
            childId = 1,
            doctorId =1,
            patientId = null,
            appointmentType = appType,
            user = null,
            vaccine = vaccine,
            clinic = clinic,
            child = child
        ),
    )
