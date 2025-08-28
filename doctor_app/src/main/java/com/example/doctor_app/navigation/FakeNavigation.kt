package com.example.doctor_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
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
import com.example.doctor_signup.navigation.doctorSignUpScreen
import com.example.doctor_signup.navigation.navigateToSignUpScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.medical_diagnosis.navigation.diagnosisScreen
import com.example.medical_diagnosis.navigation.navigateToDiagnosisScreen
import com.example.medicine_details.navigation.medicineDetailsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.medicines_search.navigation.medicinesScreen
import com.example.medicines_search.navigation.navigateToMedicineSearchScreenReplacingCurrent
import com.example.pharmacies.navigation.navigateToPharmacies
import com.example.pharmacies.navigation.pharmaciesScreen
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.upload_employee_documents.navigation.navigateToUploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.navigation.uploadEmploymentDocumentsScreen
import com.example.upload_profile_image.navigation.navigateToUploadEmployeeProfileImageScreen
import com.example.upload_profile_image.navigation.uploadProfileImageScreen

@Composable
fun FakeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DoctorScheduleRoute()
    ) {
        navigation<AuthGraphRoute>(
            startDestination = LoginRoute,
        ) {
            doctorSignUpScreen(
                onNavigateToEmailVerificationScreen = { email, password ->
                    navController.navigateToEmailOtpVerificationScreen(
                        email,
                        password,
                        navigateToResetPassword = false
                    )
                },
                onNavigateToLoginScreen = {
                    navController.navigateToLoginScreen()
                }
            )

            emailOtpVerificationScreen(
                onNavigateToEmailVerifiedSuccessfullyScreen = { email, password, navigateToResetPassword ->
                    navController.navigateToEmailVerifiedSuccessfullyScreen(
                        email, password, navigateToResetPassword
                    )
                }
            )

            emailVerifiedSuccessfullyScreen(
                onNavigateToResetPasswordScreen = { email ->
                    navController.navigateToResetPasswordScreen(email)
                },
                onNavigateToNextScreen = {
                    navController.navigateToUploadEmployeeDocumentsScreen()
                }
            )

            uploadEmploymentDocumentsScreen(
                onNavigateToAddResidentialAddressScreen = {
                    navController.navigateToAddResidentialAddressScreen()
                }
            )

            addResidentialAddressScreen(
                onNavigateToUploadProfileImageScreen = {
                    navController.navigateToUploadEmployeeProfileImageScreen()
                }
            )

            uploadProfileImageScreen(
                onNavigateToHomeScreenScreen = {
//                    navController.navigateToScheduleScreen()
                    navController.navigateToDoctorProfileScreen(
                        doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
                        doctorId = null
                    )
                }
            )

            loginScreen(
                onNavigateToEnterEmailScreen = {
                    navController.navigateToEnterEmailScreen()
                },
                onNavigateToHomeScreen = {
                    navController.navigateToDoctorProfileScreen(
                        doctorId = null,
                        doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS
                    )
//                    navController.navigate(
//                        DoctorProfileRoute(
//                            ProfileAccessType.TOKEN_ACCESS,
//                            doctorId = null
//                        )
//                    )
                },
                onNavigateToToSignUpScreen = {
                    navController.navigateToSignUpScreen()
                }
            )

            enterEmailScreen(
                onNavigateToEmailOtpVerificationScreen = { email, navigateToResetPassword ->
                    navController.navigateToEmailOtpVerificationScreen(
                        email = email,
                        navigateToResetPassword = navigateToResetPassword
                    )
                }
            )

            resetPasswordScreen(
                onNavigateToHomeScreen = {
                    navController.navigateToDoctorProfileScreen(
                        doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
                        doctorId = null
                    )
                }
            )
        }

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
                navController.navigateToAppointmentDetails(
                    appointmentId,
                    canEdit = true
                )
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
                    userProfileMode = UserProfileMode.ONLY_COMMUNICATION_INFO,
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
                    appointmentId = appointmentId,
                    canEdit = true
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
                    appointmentId = appointmentId,
                    canEdit = true
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