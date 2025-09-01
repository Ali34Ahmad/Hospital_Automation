package com.example.admin_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.doctor_profile.navigation.doctorProfileScreen
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.employee_profile.navigation.employeeProfileScreen
import com.example.employment_history.navigation.employmentHistoryScreen
import com.example.employment_requests.navigation.EmploymentRequestsRoute
import com.example.employment_requests.navigation.employmentRequestsScreen
import com.example.employment_requests.navigation.navigateToEmploymentRequestsScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.generic_vaccination_table.navigation.genericVaccineDetailsScreen
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.pharmacy_details.navigation.pharmacyDetailsScreen
import com.example.prescription_details.navigation.navigateToPrescriptionDetailsScreen
import com.example.prescription_details.navigation.prescriptionDetailsScreen
import com.example.prescriptions.navigation.PrescriptionsRoute
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

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PrescriptionsRoute(
            doctorId = 143
        ),
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
                    navController.navigateToDoctorProfileScreen(
                        doctorProfileAccessType = DoctorProfileAccessType.ADMIN_ACCESS,
                        doctorId = 143,
                    )
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
                    navController.navigateToVaccinesScreen()
                }
            )
        }

        vaccinesScreen(
            onNavigateUp = {},
            onNavigateToVaccineDetailsScreen = { vaccineId ->
                navController.navigateToVaccineDetailsScreen(
                    vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS,
                    vaccineId = vaccineId
                )
            }
        )

        vaccineDetailsScreen(
            onNavigateToVaccinationTableScreen = { },
            onNavigateUp = {
                navController.navigateUp()
            }
        )

        employmentRequestsScreen(
            onNavigateUp = { },
            onNavigateToVaccineDetailsScreen = { }
        )

        doctorProfileScreen(
            onNavigateToEmploymentHistoryScreen = {},
            onNavigateToLoginScreen = {},
            onNavigateUp = {},
            onNavigateToAppointmentsScreen = {},
            onNavigateToPrescriptionsScreen = {},
            onNavigateToMedicalRecordsScreen = {},
            onNavigateToDepartmentScreen = {}
        )

        employeeProfileScreen(
            onNavigateToEmploymentHistoryScreen = {

            },
            onNavigateToLoginScreen = {

            },
            onNavigateUp = {

            },
            onNavigateToAddedChildrenScreen = {

            }
        )
        pharmacyDetailsScreen(
            onNavigateUp = { },
            onNavigateToEmailApp = { email, subject ->

            },
            onNavigateToCallApp = { },
            onNavigateToFulfilledPrescriptionsScreen = { },
            onNavigateToMedicinesScreen = { },
            onNavigateToEmploymentHistoryScreen = { }
        )

        employmentHistoryScreen(
            onNavigateToAcceptedByAdminProfileScreen = { },
            onNavigateToToResignedByAdminProfileScreen = { },
            onNavigateUp = { },
            onNavigateToToSuspendedByAdminProfileScreen = { suspendedById: Int, currentEmployeeId: Int ->

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
            onNavigateUp = {  },
            onNavigateToPatientProfile = {  },
            onNavigateToChildProfile = {  },
            onNavigateToFulfillingPharmacy = {  },
            onNavigateToMedicineDetails = {  }
        )

        genericVaccineDetailsScreen(
            onNavigateToVaccineDetailsScreen = {

            },
            onNavigateUp = {

            }
        )
    }
}