package com.example.doctor_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_new_vaccine.navigation.addNewVaccineScreen
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.admin_profile.navigation.navigateToAdminProfileScreen
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.clinicDetailsScreen
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.vaccines.navigation.vaccinesScreen
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.doctor_profile.navigation.DoctorProfileRoute
import com.example.doctor_profile.navigation.doctorProfileScreen
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.doctor_schedule.navigation.doctorScheduleScreen
import com.example.doctor_schedule.navigation.navigateToScheduleScreen
import com.example.doctor_signup.navigation.doctorSignUpScreen
import com.example.doctor_signup.navigation.navigateToSignUpScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.employment_history.navigation.employmentHistoryScreen
import com.example.employment_history.navigation.navigateToEmploymentHistoryScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.generic_vaccination_table.navigation.GenericVaccinationTableAccessType
import com.example.generic_vaccination_table.navigation.GenericVaccinationTableRoute
import com.example.generic_vaccination_table.navigation.genericVaccineDetailsScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.guardianProfileScreen
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.prescriptions.navigation.PrescriptionsScreen
import com.example.medical_records.navigation.medicalRecordsScreen
import com.example.medical_records.navigation.navigateToMedicalRecordsScreen
import com.example.medicine_details.navigation.medicineDetailsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.navigateToPharmacyDetailsScreen
import com.example.pharmacy_details.navigation.pharmacyDetailsScreen
import com.example.prescription_details.navigation.navigateToPrescriptionDetailsScreen
import com.example.prescription_details.navigation.prescriptionDetailsScreen
import com.example.prescriptions.navigation.navigateToPrescriptionsScreen
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.upload_employee_documents.navigation.navigateToUploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.navigation.uploadEmploymentDocumentsScreen
import com.example.upload_profile_image.navigation.navigateToUploadEmployeeProfileImageScreen
import com.example.upload_profile_image.navigation.uploadProfileImageScreen
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen
import com.example.vaccine_details_screen.navigation.navigateToVaccineDetailsScreen
import com.example.vaccine_details_screen.navigation.vaccineDetailsScreen

@Composable
fun Navigation() {
    val context = LocalContext.current

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = GenericVaccinationTableRoute(
            genericVaccinationTableAccessType = GenericVaccinationTableAccessType.EDITOR_ACCESS,
        ),
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

        doctorProfileScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToLoginScreen = {
                navController.navigateToLoginScreen()
            },
            onNavigateToEmploymentHistoryScreen = {
                navController.navigateToEmploymentHistoryScreen()
            },
            onNavigateToAppointmentsScreen = {
                navController.navigateToScheduleScreen()
            },
            onNavigateToPrescriptionsScreen = {
                navController.navigateToPrescriptionsScreen(null, null)
            },
            onNavigateToMedicalRecordsScreen = {
                navController.navigateToMedicalRecordsScreen()
            },
            onNavigateToDepartmentScreen = { clinicId ->
                navController.navigateToClinicDetailsScreen(
                    clinicId = clinicId,
                    type = ClinicDetailsType.JUST_INFO
                )
            },
        )

        addNewVaccineScreen(
            onNavigateToVaccineDetailsScreenScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    VaccinePreviousScreen.ADD_NEW_VACCINE,
                    vaccineId
                )
            },
            onNavigateUp = {
                navController.navigateUp()
            }
        )
        vaccineDetailsScreen(
            onNavigateToVaccinationTableScreen = {
                navController.navigateToDoctorProfileScreen(
                    doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
                    doctorId = null,
                )
            },
            onNavigateUp = {
//                navController.navigateToVaccinesScreen()
            },
        )

        PrescriptionsScreen(
            onNavigateUp = {

            },
            onNavigateToPrescriptionDetailsScreen = { id ->
                navController.navigateToPrescriptionDetailsScreen(id)
            }
        )

        vaccinesScreen(
            onNavigateUp = {},
            onNavigateToVaccineDetailsScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS,
                    vaccineId = vaccineId,
                )
            }
        )

        pharmacyDetailsScreen(
            onNavigateUp = { },
            onNavigateToEmailApp = { email, subject ->
                context.navigateToEmailApp(email, subject)
            },
            onNavigateToCallApp = { phoneNumber ->
                context.navigateToCallApp(phoneNumber)
            },
            onNavigateToFulfilledPrescriptionsScreen = {},
            onNavigateToMedicinesScreen = {},
            onNavigateToEmploymentHistoryScreen = {},
        )

        employmentHistoryScreen(
            onNavigateToAcceptedByAdminProfileScreen = { },
            onNavigateToToResignedByAdminProfileScreen = { },
            onNavigateUp = { },
            onNavigateToToSuspendedByAdminProfileScreen = { suspendedById, currentEmployeeId ->
                if (suspendedById != currentEmployeeId) {
                    navController.navigateToAdminProfileScreen(suspendedById)
                } else {
                    navController.navigateUp()
                }
            }
        )
        medicalRecordsScreen(
            onNavigateUp = { },
            onNavigateToAppointmentsScreen = { patientId, childId -> },
            onNavigateToPrescriptionsScreen = { patientId, childId -> }
        )

        prescriptionDetailsScreen(
            onNavigateUp = { navController.navigateUp() },
            onNavigateToPatientProfile = { userId ->
                navController.navigateToGuardianProfile(userId, UserProfileMode.VIEW_ONLY, null)
            },
            onNavigateToChildProfile = { childId ->
                navController.navigateToChildProfile(childId,hasAdminAccess=false)
            },
            onNavigateToFulfillingPharmacy = { pharmacyId ->
                navController.navigateToPharmacyDetailsScreen(
                    pharmacyAccessType = PharmacyAccessType.NON_OWNER_ACCESS,
                    pharmacyId = pharmacyId
                )
            },
            onNavigateToMedicineDetails = { medicineId ->
                navController.navigateToMedicineDetails(medicineId)
            },
        )

        guardianProfileScreen(
            onNavigateUp = { navController.navigateUp() },
            onNavigateToChildrenScreen = { },
            onNavigateToAddChildScreen = { },
            onNavigateToAppointments = {  },
            onNavigateToPrescriptions = {  },
            onNavigateToMedicalRecord = {  },
        )

        childProfileScreen(
            navigateToAddGuardianScreen = {},
            navigateToEmployeeProfileScreen = { },
            navigateToGuardianScreen = { },
            navigateUp = { navController.navigateUp() },
            onNavigateToVaccinationTable = {  },
            onNavigateToAppointments = {  },
            onNavigateToPrescriptions = {  },
            onNavigateToMedicalRecords = {  },
            onNavigateToAppointmentDetails = {  },
        )

        medicineDetailsScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
        )

        doctorScheduleScreen(
            onNavigateToAppointmentDetails = {},
            onNavigateToDoctorProfile = {},
            onNavigateToMedicalRecords = {},
            onNavigateToPrescriptions = {},
            onNavigateToVaccines = {},
            onNavigateToNotifications = {},
            onNavigateToVaccineTable = {},
        )

        clinicDetailsScreen(
            onNavigateUp = {},
            onNavigateToDoctorProfile = {},
            onNavigateToScheduleScreen = {},
            onNavigateToVaccines = {},
            onNavigateToAllDoctors = {},
            onNavigateToAllAppointments = {},
            onNavigateToMedicalRecords = {},
            onNavigateToContractHistory = {},
            onNavigateToPrescriptions = {},
            onNavigateToEditClinic = {}
        )

        genericVaccineDetailsScreen(
            onNavigateToVaccineDetailsScreen = {  },
            onNavigateUp = {  }
        )
    }
}