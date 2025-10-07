package com.example.doctor_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_new_vaccine.navigation.addNewVaccineScreen
import com.example.add_new_vaccine.navigation.navigateToAddNewVaccineScreen
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.admin_profile.navigation.adminProfileScreen
import com.example.admin_profile.navigation.navigateToAdminProfileScreen
import com.example.appointment_details.navigation.AppointmentDetailsRoute
import com.example.appointment_details.navigation.appointmentDetailsScreen
import com.example.appointment_details.navigation.navigateToAppointmentDetails
import com.example.child_profile.navigation.ChildProfileMode
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.clinicDetailsScreen
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.clinics_search.navigation.clinicsSearchScreen
import com.example.clinics_search.navigation.navigateToClinicsSearch
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.doctor_profile.navigation.doctorProfileScreen
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.doctor_schedule.navigation.ScheduleRoute
import com.example.doctor_schedule.navigation.navigateToScheduleScreen
import com.example.doctor_schedule.navigation.scheduleScreen
import com.example.doctor_signup.navigation.doctorSignUpScreen
import com.example.doctor_signup.navigation.navigateToDoctorSignUpScreen
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
import com.example.generic_vaccination_table.navigation.genericVaccinationTableScreen
import com.example.generic_vaccination_table.navigation.navigateToGenericVaccinationTableScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.guardianProfileScreen
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.medical_diagnosis.navigation.diagnosisScreen
import com.example.medical_diagnosis.navigation.navigateToDiagnosisScreen
import com.example.medical_records.navigation.medicalRecordsScreen
import com.example.medical_records.navigation.navigateToMedicalRecordsScreen
import com.example.medicine_details.navigation.medicineDetailsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.medicines_search.navigation.MedicinesSearchRoute
import com.example.medicines_search.navigation.medicinesScreen
import com.example.model.enums.Role
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.pharmacies.navigation.navigateToPharmacies
import com.example.pharmacies.navigation.pharmaciesScreen
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.navigateToPharmacyDetailsScreen
import com.example.pharmacy_details.navigation.pharmacyDetailsScreen
import com.example.prescription_details.navigation.navigateToPrescriptionDetailsScreen
import com.example.prescription_details.navigation.prescriptionDetailsScreen
import com.example.prescriptions.navigation.navigateToPrescriptionsScreen
import com.example.prescriptions.navigation.prescriptionsScreen
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.upload_employee_documents.navigation.navigateToUploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.navigation.uploadEmploymentDocumentsScreen
import com.example.upload_profile_image.navigation.navigateToUploadProfileImageScreen
import com.example.upload_profile_image.navigation.uploadProfileImageScreen
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen
import com.example.vaccine_details_screen.navigation.navigateToVaccineDetailsScreen
import com.example.vaccine_details_screen.navigation.navigateToVaccineDetailsScreenReplacingCurrentScreen
import com.example.vaccine_details_screen.navigation.vaccineDetailsScreen
import com.example.vaccines.navigation.navigateToVaccinesScreen
import com.example.vaccines.navigation.vaccinesScreen

@Composable
fun DoctorNavigation() {
    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = GenericVaccinationTableRoute(
            genericVaccinationTableAccessType = GenericVaccinationTableAccessType.EDITOR_ACCESS
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
                    navController.navigateToScheduleScreen(
                        askForPermissions = true
                    )
                },
                onNavigateToToSignUpScreen = {
                    navController.navigateToDoctorSignUpScreen()
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
                    navController.navigateToScheduleScreen(
                        askForPermissions = true
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
                navController.navigateToEmploymentHistoryScreen(null)
            },
            onNavigateToAppointmentsScreen = { _, _, _, _ ->
                navController.navigateToScheduleScreen()
            },
            onNavigateToPrescriptionsScreen = {
                navController.navigateToPrescriptionsScreen(null, null, null)
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

        vaccinesScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToVaccineDetailsScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS,
                    vaccineId = vaccineId,
                )
            },
            onNavigateToAddNewVaccineScreen = {
                navController.navigateToAddNewVaccineScreen()
            },
        )

        addNewVaccineScreen(
            onNavigateToVaccineDetailsScreenScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreenReplacingCurrentScreen(
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
                navController.navigateToGenericVaccinationTableScreen(
                    genericVaccinationTableAccessType = GenericVaccinationTableAccessType.EDITOR_ACCESS
                )
            },
            onNavigateUp = {
                navController.navigateUp()
            },
        )

        genericVaccinationTableScreen(
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
                navController.navigateToGuardianProfile(
                    userId,
                    UserProfileMode.ONLY_COMMUNICATION_INFO,
                    null
                )
            },
            onNavigateToChildProfile = { childId ->
                navController.navigateToChildProfile(
                    childId = childId,
                    childProfileMode = ChildProfileMode.DOCTOR_ACCESS,
                )
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

        pharmacyDetailsScreen(
            onNavigateUp = { navController.navigateUp() },
            onNavigateToEmailApp = { email, subject ->
                context.navigateToEmailApp(email, subject)
            },
            onNavigateToCallApp = { phoneNumber ->
                context.navigateToCallApp(phoneNumber)
            },
            onNavigateToFulfilledPrescriptionsScreen = {},
            onNavigateToMedicinesScreen = { id, name, image -> },
            onNavigateToEmploymentHistoryScreen = {}
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
            onNavigateToSuspendedByAdminProfileScreen = { suspendedById, currentEmployeeId, _, _ ->
                if (suspendedById != currentEmployeeId) {
                    navController.navigateToAdminProfileScreen(suspendedById)
                } else {
                    navController.navigateUp()
                }
            }
        )

        adminProfileScreen(
            onNavigateUp = {
                navController.navigateUp()
            }
        )

        medicalRecordsScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToAppointmentsScreen = { patientId, childId ->
                navController.navigateToScheduleScreen()
            },
            onNavigateToPrescriptionsScreen = { patientId, childId, _ ->
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
                navController.navigateToScheduleScreen(
                    id = childId,
                    searchType = AppointmentSearchType.CHILD
                )
            },
            onNavigateToPrescriptions = { childId ->
                navController.navigateToPrescriptionsScreen(
                    patientId = null,
                    childId = childId,
                    doctorId = null
                )
            },
            onNavigateToMedicalRecords = { childId ->
            },
            onNavigateToAppointmentDetails = { appointmentId ->
                navController.navigateToAppointmentDetails(
                    appointmentId = appointmentId,
                    canEdit = false
                )
            },
            navigateToGuardiansScreen = {},
        )

        //Ali Mansoura
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
                navController.navigateToMedicalRecordsScreen(
                    doctorId = null
                )
            },
            onNavigateToPrescriptions = {
                navController.navigateToPrescriptionsScreen(
                    doctorId = null,
                    patientId = null,
                    childId = null
                )
            },
            onNavigateToVaccines = {
                navController.navigateToVaccinesScreen(Role.DOCTOR)
            },
            onNavigateToCreateNewClinic = {
            },
            onNavigateToAdminProfile = {
            },
            onNavigateToVaccineTable = {
                navController.navigateToGenericVaccinationTableScreen(
                    genericVaccinationTableAccessType = GenericVaccinationTableAccessType.EDITOR_ACCESS
                )
            },
        )

        clinicDetailsScreen(
            onNavigateUp = navController::navigateUp,
            onNavigateToDoctorProfile = { doctorId ->
                navController.navigateToDoctorProfileScreen(
                    doctorProfileAccessType = DoctorProfileAccessType.OTHER_DOCTOR_ACCESS,
                    doctorId = doctorId
                )
            },
            onNavigateToScheduleScreen = { },
            onNavigateToVaccines = {
                navController.navigateToVaccinesScreen(Role.DOCTOR)
            },
            onNavigateToAllDoctors = { clinicId, clinicName -> },
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
            onNavigateToProfile = {
                navController.navigateToDoctorProfileScreen(
                    doctorProfileAccessType = DoctorProfileAccessType.TOKEN_ACCESS,
                    doctorId = null
                )
            },
            onNavigateToPrescriptions = {
                navController.navigateToPrescriptionsScreen(null, null, null)
            },
            onNavigateToVaccines = {
                navController.navigateToVaccinesScreen(Role.DOCTOR)
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
            onNavigateToUserProfile = { userId ->
                navController.navigateToGuardianProfile(
                    guardianId = userId,
                    userProfileMode = UserProfileMode.ONLY_COMMUNICATION_INFO,
                )
            },
            onNavigateToChildProfile = { childId ->
                navController.navigateToChildProfile(
                    childId = childId,
                    childProfileMode = ChildProfileMode.DOCTOR_ACCESS,
                )
            },
            onNavigateUp = {
                navController.navigateUp()
            },
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
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS,
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
            onNavigateToChildProfile = { childId ->
                navController.navigateToChildProfile(
                    childId = childId,
                    childProfileMode = ChildProfileMode.DOCTOR_ACCESS
                )
            },
        )

        diagnosisScreen(
            onNavigateToAppointmentDetails = { appointmentId ->
                val newRoute = AppointmentDetailsRoute(appointmentId, canEdit = true)
                navController.navigate(route = newRoute) {
                    popUpTo<ScheduleRoute> {
                        inclusive = false
                        saveState = true
                    }
                    restoreState = false
                }
            },
            onNavigateToMedicinesSearchScreen = { childId, patientId, appointmentId ->
                val newRoute = MedicinesSearchRoute(
                    childId = childId,
                    patientId = patientId,
                    appointmentId = appointmentId
                )
                navController.navigate(route = newRoute) {
                    popUpTo<ScheduleRoute> {
                        inclusive = false
                        saveState = true
                    }
                    restoreState = false
                }
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
//                navController.navigateToAppointmentDetailsReplacementCurrent(
//                    appointmentId = appointmentId,
//                    canEdit = true
//                )
                val newRoute = AppointmentDetailsRoute(appointmentId, canEdit = true)
                navController.navigate(route = newRoute) {
                    popUpTo<ScheduleRoute> {
                        inclusive = false
                        saveState = true
                    }
                    restoreState = false
                }
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

        medicineDetailsScreen(
            onNavigateUp = navController::navigateUp
        )


    }
}