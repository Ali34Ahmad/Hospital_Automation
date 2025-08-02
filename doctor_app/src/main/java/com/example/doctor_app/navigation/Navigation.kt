package com.example.doctor_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_new_vaccine.navigation.addNewVaccineScreen
import com.example.add_new_vaccine.navigation.navigateToAddNewVaccineScreen
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.doctor_profile.navigation.ProfileAccessType
import com.example.doctor_profile.navigation.doctorProfileScreen
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.doctor_signup.navigation.DoctorSignUpRoute
import com.example.doctor_signup.navigation.doctorSignUpScreen
import com.example.doctor_signup.navigation.navigateToSignUpScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.employment_history.navigation.navigateToEmploymentHistoryScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.upload_employee_documents.navigation.navigateToUploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.navigation.uploadEmploymentDocumentsScreen
import com.example.upload_employee_profile_image.navigation.navigateToUploadEmployeeProfileImageScreen
import com.example.upload_employee_profile_image.navigation.uploadEmployeeProfileImageScreen
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen
import com.example.vaccine_details_screen.navigation.navigateToVaccineDetailsScreen
import com.example.vaccine_details_screen.navigation.vaccineDetailsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthGraphRoute,
    ) {

        navigation<AuthGraphRoute>(
            startDestination = DoctorSignUpRoute,
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
                onNavigateToUploadEmployeeDocumentsScreen = {
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

            uploadEmployeeProfileImageScreen(
                onNavigateToHomeScreenScreen = {
//                    navController.navigateToScheduleScreen()
                    navController.navigateToDoctorProfileScreen(
                        profileAccessType = ProfileAccessType.TOKEN_ACCESS,
                        doctorId = null
                    )
                }
            )

            loginScreen(
                onNavigateToEnterEmailScreen = {
                    navController.navigateToEnterEmailScreen()
                },
                onNavigateToHomeScreen = {
                    navController.navigateToAddNewVaccineScreen()
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
                        profileAccessType = ProfileAccessType.TOKEN_ACCESS,
                        doctorId = null
                    )
                }
            )
        }

        doctorProfileScreen(
            onNavigateUp = {
//                    navController.navigateToAppointmentDetails()
            },
            onNavigateToLoginScreen = {
                navController.navigateToLoginScreen()
            },
            onNavigateToEmploymentHistoryScreen = {
                navController.navigateToEmploymentHistoryScreen()
            },
            onNavigateToAppointmentsScreen = {
//                    navController.
            },
            onNavigateToPrescriptionsScreen = {
//                    navController.
            },
            onNavigateToMedicalRecordsScreen = {
//                    navController.
            },
            onNavigateToDepartmentScreen = {
//                    navController.
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
                    profileAccessType = ProfileAccessType.TOKEN_ACCESS,
                    doctorId = null,
                )
            },
            onNavigateUp = {
//                navController.navigateToVaccinesScreen()
            },
        )
    }
}