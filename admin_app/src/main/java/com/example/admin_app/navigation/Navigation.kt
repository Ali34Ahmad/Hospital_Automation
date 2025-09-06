package com.example.admin_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.admin_profile.navigation.navigateToAdminProfileScreen
import com.example.children_search.navigation.SearchType
import com.example.children_search.navigation.navigateToChildrenSearch
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.doctor_profile.navigation.doctorProfileScreen
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.doctor_schedule.navigation.navigateToScheduleScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.employee_profile.navigation.EmployeeProfileAccessType
import com.example.employee_profile.navigation.employeeProfileScreen
import com.example.employee_profile.navigation.navigateToEmployeeProfileScreen
import com.example.employment_history.navigation.employmentHistoryScreen
import com.example.employment_history.navigation.navigateToEmploymentHistoryScreen
import com.example.employment_requests.navigation.EmploymentRequestsRoute
import com.example.employment_requests.navigation.employmentRequestsScreen
import com.example.employment_requests.navigation.navigateToEmploymentRequestsScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.generic_vaccination_table.navigation.GenericVaccinationTableAccessType
import com.example.generic_vaccination_table.navigation.genericVaccineDetailsScreen
import com.example.generic_vaccination_table.navigation.navigateToGenericVaccinationTableScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.medical_records.navigation.medicalRecordsScreen
import com.example.medical_records.navigation.navigateToMedicalRecordsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.navigateToPharmacyDetailsScreen
import com.example.pharmacy_details.navigation.pharmacyDetailsScreen
import com.example.prescription_details.navigation.navigateToPrescriptionDetailsScreen
import com.example.prescription_details.navigation.prescriptionDetailsScreen
import com.example.prescriptions.navigation.navigateToPrescriptionsScreen
import com.example.prescriptions.navigation.prescriptionsScreen
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.signup.navigation.navigateToSignUpScreen
import com.example.signup.navigation.signUpScreen
import com.example.upload_profile_image.navigation.navigateToUploadProfileImageScreen
import com.example.upload_profile_image.navigation.uploadProfileImageScreen
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen
import com.example.vaccine_details_screen.navigation.navigateToVaccineDetailsScreen
import com.example.vaccine_details_screen.navigation.vaccineDetailsScreen
import com.example.vaccines.navigation.navigateToVaccinesScreen
import com.example.vaccines.navigation.vaccinesScreen


@Composable
fun Navigation() {
    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = EmploymentRequestsRoute,
    ) {
        navigation<AuthGraphRoute>(
            startDestination = LoginRoute,
        ) {
            signUpScreen(
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
                    navController.navigateToEmploymentRequestsScreen()
                }
            )

            loginScreen(
                onNavigateToEnterEmailScreen = {
                    navController.navigateToEnterEmailScreen()
                },
                onNavigateToHomeScreen = {
                    navController.navigateToEmploymentRequestsScreen()
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
                    navController.navigateToEmploymentRequestsScreen()
                }
            )
        }

        employmentRequestsScreen(
            onNavigateToEmployeeProfileDetailsScreen = { employeeId ->
                navController.navigateToEmployeeProfileScreen(
                    employeeId = employeeId,
                    employeeProfileAccessType = EmployeeProfileAccessType.ADMIN_ACCESS
                )
            },
            onNavigateToPharmacyDetailsScreen = { pharmacyId ->
                navController.navigateToPharmacyDetailsScreen(
                    pharmacyId = pharmacyId,
                    pharmacyAccessType = PharmacyAccessType.ADMIN_ACCESS
                )
            },
            onNavigateToDoctorProfileDetailsScreen = { doctorId ->
                navController.navigateToDoctorProfileScreen(
                    doctorId = doctorId,
                    doctorProfileAccessType = DoctorProfileAccessType.ADMIN_ACCESS,
                )
            },
            onNavigateToAdminProfile = {
                navController.navigateToAdminProfileScreen(
                    adminId = null,
                )
            },
            onNavigateToVaccines = {
                navController.navigateToVaccinesScreen()
            },
            onNavigateToVaccineTable = {
                navController.navigateToGenericVaccinationTableScreen(
                    genericVaccinationTableAccessType = GenericVaccinationTableAccessType.VIEWER_ACCESS
                )
            },
        )

        doctorProfileScreen(
            onNavigateToEmploymentHistoryScreen = { doctorId ->
                navController.navigateToEmploymentHistoryScreen(doctorId)
            },
            onNavigateToLoginScreen = {},
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToAppointmentsScreen = { doctorId ->
                navController.navigateToScheduleScreen(
                    id = doctorId,
                    hasAdminAccess = true,
                    searchType = AppointmentSearchType.DOCTOR,
                )
            },
            onNavigateToPrescriptionsScreen = { doctorId ->
                navController.navigateToPrescriptionsScreen(
                    patientId = null,
                    childId = null,
                    doctorId = doctorId,
                )
            },
            onNavigateToMedicalRecordsScreen = { doctorId ->
                navController.navigateToMedicalRecordsScreen(doctorId)
            },
            onNavigateToDepartmentScreen = { clinicId ->
                navController.navigateToClinicDetailsScreen(
                    clinicId = clinicId,
                    type = ClinicDetailsType.ADMIN_ACCESS
                )
            }
        )

        employeeProfileScreen(
            onNavigateToEmploymentHistoryScreen = { employeeId ->
                navController.navigateToEmploymentHistoryScreen(employeeId)
            },
            onNavigateToLoginScreen = {},
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToAddedChildrenScreen = { employeeId ->
                navController.navigateToChildrenSearch(
                    searchType = SearchType.EMPLOYEE,
                    employeeId = employeeId,
                )
            }
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
            onNavigateToFulfilledPrescriptionsScreen = { },
            onNavigateToMedicinesScreen = {
                TODO()
            },
            onNavigateToEmploymentHistoryScreen = { employeeId ->
                navController.navigateToEmploymentHistoryScreen(employeeId)
            }
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
            onNavigateToSuspendedByAdminProfileScreen = { suspendedById: Int, currentEmployeeId: Int ->
                if (suspendedById == currentEmployeeId) {
                    navController.navigateToEmployeeProfileScreen(
                        employeeId = currentEmployeeId,
                        employeeProfileAccessType = EmployeeProfileAccessType.ADMIN_ACCESS
                    )
                } else {
                    navController.navigateToAdminProfileScreen(
                        adminId = currentEmployeeId,
                    )
                }
            }
        )

        medicalRecordsScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToAppointmentsScreen = { patientId, childId ->
                navController.navigateToScheduleScreen(
                    id = patientId ?: childId,
                    searchType = if (patientId != null) AppointmentSearchType.USER
                    else AppointmentSearchType.CHILD
                )
            },
            onNavigateToPrescriptionsScreen = { patientId, childId, doctorId ->
                navController.navigateToPrescriptionsScreen(patientId, childId, doctorId)
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
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToPatientProfile = { userId ->
                navController.navigateToGuardianProfile(
                    guardianId = userId,
                    userProfileMode = UserProfileMode.ADMIN_ACCESS
                )
            },
            onNavigateToChildProfile = { childId ->
                navController.navigateToGuardianProfile(
                    guardianId = childId,
                    userProfileMode = UserProfileMode.ADMIN_ACCESS
                )
            },
            onNavigateToFulfillingPharmacy = { pharmacyId ->
                navController.navigateToPharmacyDetailsScreen(
                    pharmacyId = pharmacyId,
                    pharmacyAccessType = PharmacyAccessType.ADMIN_ACCESS
                )
            },
            onNavigateToMedicineDetails = {medicineId->
                navController.navigateToMedicineDetails(
                    medicineId=medicineId,
                )
            }
        )

        vaccinesScreen(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToVaccineDetailsScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS,
                    vaccineId = vaccineId
                )
            },
            onNavigateToAddNewVaccineScreen = {},
        )

        vaccineDetailsScreen(
            onNavigateToVaccinationTableScreen = { },
            onNavigateUp = {
                navController.navigateUp()
            }
        )

        genericVaccineDetailsScreen(
            onNavigateToVaccineDetailsScreen = {vaccineId->
                navController.navigateToVaccineDetailsScreen(
                    vaccineId=vaccineId,
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS
                )
            },
            onNavigateUp = {
                navController.navigateUp()
            }
        )

    }
}