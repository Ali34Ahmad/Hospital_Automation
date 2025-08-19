package com.example.doctor_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.appointment_details.navigation.appointmentDetailsScreen
import com.example.appointment_details.navigation.navigateToAppointmentDetails
import com.example.appointment_details.navigation.navigateToAppointmentDetailsReplacementCurrent
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.clinicDetailsScreen
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.clinics_search.navigation.clinicsSearchScreen
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.doctor_schedule.navigation.DoctorScheduleRoute
import com.example.doctor_schedule.navigation.doctorScheduleScreen
import com.example.doctor_schedule.navigation.navigateToScheduleScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.medical_diagnosis.navigation.diagnosisScreen
import com.example.medical_diagnosis.navigation.navigateToDiagnosisScreen
import com.example.medicine_details.navigation.medicineDetailsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.medicines_search.navigation.medicinesScreen
import com.example.medicines_search.navigation.navigateToMedicineSearchScreenReplacingCurrent
import com.example.pharmacies.navigation.navigateToPharmacies
import com.example.pharmacies.navigation.pharmaciesScreen

@Composable
fun FakeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DoctorScheduleRoute
    ) {
        clinicsSearchScreen(
            onNavigateToDepartmentDetails = {clinicId->
                navController.navigateToClinicDetailsScreen(
                    clinicId = clinicId,
                    type = ClinicDetailsType.FOR_REGISTERING
                )
            },
            onNavigateToDoctorProfile = {
                navController.navigateToDoctorProfileScreen(
                    doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
                    doctorId = null
                )
            },
            onNavigateToNotifications = {
                TODO("This feature is not implemented")
            },
            onNavigateToMedicalRecords = {
                TODO("Need Navigation Route")
            },
            onNavigateToPrescriptions = {
                TODO("Need Navigation Route")
            },
            onNavigateToVaccines = {
                TODO("Need Navigation Route")
            },
            onNavigateToCreateNewClinic = {
                //this feature is not applicable for doctor
            }
        )
        clinicDetailsScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToDoctorProfile = {
                navController.navigateToDoctorProfileScreen(
                    doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
                    doctorId = null
                )
            },
            onNavigateToScheduleScreen = {
                navController.navigateToScheduleScreen()
            },
            onNavigateToVaccines = {
                TODO("Need Navigation Route")
            },
            onNavigateToAllDoctors = {},
            onNavigateToAllAppointments = {},
            onNavigateToMedicalRecords = {},
            onNavigateToContractHistory = {},
            onNavigateToPrescriptions = {},
            onNavigateToEditClinic = {},
        )
        doctorScheduleScreen(
            onNavigateToAppointmentDetails = {appointmentId ->
                navController.navigateToAppointmentDetails(appointmentId)
            },
            onNavigateToDoctorProfile = {
                navController.navigateToDoctorProfileScreen(
                    doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
                    doctorId = null
                )
            },
            onNavigateToPrescriptions = {
                TODO("Need Navigation Route")
            },
            onNavigateToVaccines = {
                TODO("Need Navigation Route")
            },
            onNavigateToNotifications = {
                TODO("This feature is not implemented")
            },
            onNavigateToMedicalRecords = {
                TODO("Need Navigation Route")
            },
            onNavigateToVaccineTable = {
                TODO("Need Navigation Route")
            }
        )
        appointmentDetailsScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToDepartmentDetails = {clinicId->
                navController.navigateToClinicDetailsScreen(
                    clinicId = clinicId,
                    type = ClinicDetailsType.JUST_INFO
                )
            },
            onNavigateToVaccineDetails = {
                TODO("Need Navigation Route")
            },
            onNavigateToAddMedicalDiagnosis = {appointmentId,fullName,patientId,childId,canSkip->
                navController.navigateToDiagnosisScreen(
                    appointmentId = appointmentId,
                    fullName = fullName,
                    patientId = patientId,
                    childId = childId,
                    canSkip = canSkip
                )
            },
            onNavigateToGuardianProfile = {guardianId->
                navController.navigateToGuardianProfile(
                    guardianId = guardianId,
                    userProfileMode = UserProfileMode.VIEW_ONLY,
                    childId = null
                )
            },
            onNavigateToDoctorSchedule = {
                navController.navigateToScheduleScreen()
            }
        )
        diagnosisScreen(
            onNavigateToAppointmentDetails = {appointmentId ->
                navController.navigateToAppointmentDetailsReplacementCurrent(
                    appointmentId = appointmentId
                )
            },
            onNavigateToMedicinesSearchScreen = {
                    childId, patientId, appointmentId ->
                navController.navigateToMedicineSearchScreenReplacingCurrent(
                    childId = childId,
                    patientId = patientId,
                    appointmentId = appointmentId
                )
            }
        )
        medicinesScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToPharmacies = {medicineId,medicineName->
                navController.navigateToPharmacies(
                    medicineId = medicineId,
                    medicineName = medicineName
                )
            },
            onNavigateToMedicineDetails = {medicineId->
                navController.navigateToMedicineDetails(
                    medicineId = medicineId
                )
            },
            onNavigateToAppointmentDetails = { appointmentId->
                navController.navigateToAppointmentDetailsReplacementCurrent(
                    appointmentId = appointmentId
                )
            }
        )
        pharmaciesScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToPharmacyDetails =  {
                TODO("Need Navigation Route")
            },
        )
        medicineDetailsScreen(
            onNavigateUp = navController::navigateUp
        )
    }
}