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
import com.example.appointment_details.navigation.appointmentDetailsScreen
import com.example.appointment_details.navigation.navigateToAppointmentDetails
import com.example.appointment_details.navigation.navigateToAppointmentDetailsReplacementCurrent
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.clinicDetailsScreen
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.clinics_search.navigation.clinicsSearchScreen
import com.example.clinics_search.navigation.navigateToClinicsSearch
import com.example.vaccines.navigation.vaccinesScreen
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.doctor_profile.navigation.doctorProfileScreen
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.doctor_schedule.navigation.navigateToScheduleScreen
import com.example.doctor_schedule.navigation.scheduleScreen
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
import com.example.generic_vaccination_table.navigation.genericVaccineDetailsScreen
import com.example.generic_vaccination_table.navigation.navigateToGenericVaccinationTableScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.guardianProfileScreen
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.medical_diagnosis.navigation.diagnosisScreen
import com.example.medical_diagnosis.navigation.navigateToDiagnosisScreen
import com.example.medical_records.navigation.MedicalRecordsRoute
import com.example.prescriptions.navigation.prescriptionsScreen
import com.example.medical_records.navigation.medicalRecordsScreen
import com.example.medical_records.navigation.navigateToMedicalRecordsScreen
import com.example.medicine_details.navigation.medicineDetailsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.medicines_search.navigation.medicinesScreen
import com.example.medicines_search.navigation.navigateToMedicineSearchScreenReplacingCurrent
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.pharmacies.navigation.navigateToPharmacies
import com.example.pharmacies.navigation.pharmaciesScreen
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.navigateToPharmacyDetailsScreen
import com.example.pharmacy_details.navigation.pharmacyDetailsScreen
import com.example.prescription_details.navigation.navigateToPrescriptionDetailsScreen
import com.example.prescription_details.navigation.prescriptionDetailsScreen
import com.example.prescriptions.navigation.PrescriptionsRoute
import com.example.prescriptions.navigation.navigateToPrescriptionsScreen
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.upload_employee_documents.navigation.navigateToUploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.navigation.uploadEmploymentDocumentsScreen
import com.example.upload_profile_image.navigation.navigateToUploadProfileImageScreen
import com.example.upload_profile_image.navigation.uploadProfileImageScreen
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen
import com.example.vaccine_details_screen.navigation.navigateToVaccineDetailsScreen
import com.example.vaccine_details_screen.navigation.vaccineDetailsScreen
import com.example.vaccines.navigation.navigateToVaccinesScreen

@Composable
fun Navigation() {
    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MedicalRecordsRoute(
            doctorId = null
        )
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
                    navController.navigateToUploadProfileImageScreen()
                }
            )

            uploadProfileImageScreen(
                onNavigateToHomeScreenScreen = {
                    navController.navigateToClinicsSearch()
                }
            )

            loginScreen(
                onNavigateToEnterEmailScreen = {
                    navController.navigateToEnterEmailScreen()
                },
                onNavigateToHomeScreen = {
                    navController.navigateToPrescriptionsScreen(
                        patientId = null,
                        childId = null,
                        doctorId = null
                    )
//                    navController.navigateToScheduleScreen()
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
                    navController.navigateToScheduleScreen()
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
                navController.navigateToEmploymentHistoryScreen(null)
            },
            onNavigateToAppointmentsScreen = {
                navController.navigateToScheduleScreen()
            },
            onNavigateToPrescriptionsScreen = {
                navController.navigateToPrescriptionsScreen(null, null,null)
            },
            onNavigateToMedicalRecordsScreen = {
                navController.navigateToMedicalRecordsScreen(null)
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

        vaccinesScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToVaccineDetailsScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS,
                    vaccineId = vaccineId,
                )
            }
        )

        vaccineDetailsScreen(
            onNavigateToVaccinationTableScreen = {
                navController.navigateToGenericVaccinationTableScreen(
                    genericVaccinationTableAccessType = GenericVaccinationTableAccessType.EDITOR_ACCESS
                )
            },
            onNavigateUp = {
                navController.navigateUp()
            },
        )

        prescriptionsScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToPrescriptionDetailsScreen = { id ->
                navController.navigateToPrescriptionDetailsScreen(id)
            }
        )

        prescriptionDetailsScreen(
            onNavigateUp = { navController.navigateUp() },
            onNavigateToPatientProfile = { userId ->
                navController.navigateToGuardianProfile(userId, UserProfileMode.VIEW_ONLY, null)
            },
            onNavigateToChildProfile = { childId ->
                navController.navigateToChildProfile(childId, hasAdminAccess = false)
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

        employmentHistoryScreen(
            onNavigateToAcceptedByAdminProfileScreen = { adminId ->
                navController.navigateToAdminProfileScreen(adminId)
            },
            onNavigateToToResignedByAdminProfileScreen = { adminId ->
                navController.navigateToAdminProfileScreen(adminId)
            },
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToToSuspendedByAdminProfileScreen = { suspendedById, currentEmployeeId ->
                if (suspendedById != currentEmployeeId) {
                    navController.navigateToAdminProfileScreen(suspendedById)
                } else {
                    navController.navigateUp()
                }
            }
        )

        medicalRecordsScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToAppointmentsScreen = { patientId, childId ->
                navController.navigateToScheduleScreen()
            },
            onNavigateToPrescriptionsScreen = { patientId, childId,_ ->
                navController.navigateToPrescriptionsScreen(patientId, childId, null)
            }
        )

        guardianProfileScreen(
            onNavigateUp = { navController.navigateUp() },
            onNavigateToChildrenScreen = { },
            onNavigateToAddChildScreen = { },
            onNavigateToAppointments = { patientId, name, imageUrl ->
                navController.navigateToScheduleScreen()
            },
            onNavigateToPrescriptions = { patientId ->
                navController.navigateToPrescriptionsScreen(patientId = patientId, null, null)
            },
            onNavigateToMedicalRecord = { patientId ->
                navController.navigateToPrescriptionsScreen(patientId = patientId, null, null)
            },
        )

        childProfileScreen(
            navigateToAddGuardianScreen = { },
            navigateToEmployeeProfileScreen = { },
            navigateUp = { navController.navigateUp() },
            onNavigateToVaccinationTable = {},
            onNavigateToAppointments = { childId, name ->
                navController.navigateToScheduleScreen()
            },
            onNavigateToPrescriptions = { childId ->
            },
            onNavigateToMedicalRecords = { childId ->
            },
            onNavigateToAppointmentDetails = { appointmentId ->
                navController.navigateToAppointmentDetails(
                    appointmentId = appointmentId,
                    canEdit = false
                )
            },
            navigateToGuardiansScreen = { TODO() },
        )

        genericVaccineDetailsScreen(
            onNavigateToVaccineDetailsScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS,
                    vaccineId = vaccineId
                )
            },
            onNavigateUp = {
                navController.navigateUp()
            }
        )

        clinicsSearchScreen(
            onNavigateToDepartmentDetails = { clinicId ->
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

            },
            onNavigateToMedicalRecords = {
            },
            onNavigateToPrescriptions = {
                navController.navigateToPrescriptionsScreen(null, null,null)
            },
            onNavigateToVaccines = {
                navController.navigateToVaccinesScreen()
            },
            onNavigateToCreateNewClinic = {
                //this feature is not applicable for doctor
            },
            onNavigateToAdminProfile = { TODO() },
            onNavigateToVaccineTable = { TODO() },
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
                navController.navigateToVaccinesScreen()
            },
            onNavigateToAllDoctors = { clinicId, clinicName ->
                TODO()
            },
            onNavigateToAllAppointments = {},
            onNavigateToMedicalRecords = {},
            onNavigateToContractHistory = {},
            onNavigateToPrescriptions = {},
            onNavigateToEditClinic = {},
        )

        scheduleScreen(
            onNavigateToAppointmentDetails = { appointmentId ->
                navController.navigateToAppointmentDetails(
                    appointmentId,
                    canEdit = true
                )
            },
//            onNavigateToDoctorProfile = {
//                navController.navigateToDoctorProfileScreen(
//                    doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
//                    doctorId = null
//                )
//            },
            onNavigateToPrescriptions = {
                navController.navigateToPrescriptionsScreen(null, null,null)
            },
            onNavigateToVaccines = {
                navController.navigateToVaccinesScreen()
            },
            onNavigateToNotifications = {
            },
            onNavigateToMedicalRecords = {
                navController.navigateToMedicalRecordsScreen(null)
            },
            onNavigateToVaccineTable = {
                navController.navigateToGenericVaccinationTableScreen(
                    genericVaccinationTableAccessType = GenericVaccinationTableAccessType.EDITOR_ACCESS
                )
            },
            onNavigateToProfile = { TODO() },
            onNavigateToUserProfile = { TODO() },
            onNavigateToChildProfile = { TODO() },
            onNavigateUp = { TODO() },
        )
        appointmentDetailsScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToDepartmentDetails = { clinicId ->
                navController.navigateToClinicDetailsScreen(
                    clinicId = clinicId,
                    type = ClinicDetailsType.JUST_INFO
                )
            },
            onNavigateToVaccineDetails = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    vaccineId = vaccineId,
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS
                )
            },
            onNavigateToAddMedicalDiagnosis = { appointmentId, fullName, patientId, childId, canSkip ->
                navController.navigateToDiagnosisScreen(
                    appointmentId = appointmentId,
                    fullName = fullName,
                    patientId = patientId,
                    childId = childId,
                    canSkip = canSkip
                )
            },
            onNavigateToGuardianProfile = { guardianId ->
                navController.navigateToGuardianProfile(
                    guardianId = guardianId,
                    userProfileMode = UserProfileMode.ONLY_COMMUNICATION_INFO,
                    childId = null
                )
            },
//            onNavigateToDoctorSchedule = {
//                navController.navigateToScheduleScreen()
//            }
        )
        diagnosisScreen(
            onNavigateToAppointmentDetails = { appointmentId ->
                navController.navigateToAppointmentDetailsReplacementCurrent(
                    appointmentId = appointmentId,
                    canEdit = true
                )
            },
            onNavigateToMedicinesSearchScreen = { childId, patientId, appointmentId ->
                navController.navigateToMedicineSearchScreenReplacingCurrent(
                    childId = childId,
                    patientId = patientId,
                    appointmentId = appointmentId
                )
            }
        )

        medicinesScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToPharmacies = { medicineId, medicineName ->
                navController.navigateToPharmacies(
                    medicineId = medicineId,
                    medicineName = medicineName
                )
            },
            onNavigateToMedicineDetails = { medicineId ->
                navController.navigateToMedicineDetails(
                    medicineId = medicineId
                )
            },
            onNavigateToAppointmentDetails = { appointmentId ->
                navController.navigateToAppointmentDetailsReplacementCurrent(
                    appointmentId = appointmentId,
                    canEdit = true
                )
            }
        )
        pharmaciesScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToPharmacyDetails = { pharmacyId ->
                navController.navigateToPharmacyDetailsScreen(
                    pharmacyId = pharmacyId,
                    pharmacyAccessType = PharmacyAccessType.NON_OWNER_ACCESS
                )
            },
        )

        pharmacyDetailsScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToEmailApp = { email, subject ->
                context.navigateToEmailApp(email, subject)
            },
            onNavigateToCallApp = { phoneNumber ->
                context.navigateToCallApp(phoneNumber)
            },
            onNavigateToFulfilledPrescriptionsScreen = {
            },
            onNavigateToMedicinesScreen = {},
            onNavigateToEmploymentHistoryScreen = {},
        )

        medicineDetailsScreen(
            onNavigateUp = navController::navigateUp
        )


    }
}