package com.example.admin_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.employment_requests.navigation.employmentRequestsScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.signup.navigation.navigateToSignUpScreen
import com.example.signup.navigation.signUpScreen
import com.example.upload_profile_image.navigation.navigateToUploadEmployeeProfileImageScreen
import com.example.upload_profile_image.navigation.uploadProfileImageScreen
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen
import com.example.vaccine_details_screen.navigation.navigateToVaccineDetailsScreen
import com.example.vaccine_details_screen.navigation.vaccineDetailsScreen
import com.example.vaccines.navigation.VaccinesRoute
import com.example.vaccines.navigation.navigateToVaccinesScreen
import com.example.vaccines.navigation.vaccinesScreen


@Composable
fun Navigation() {
    val context = LocalContext.current

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthGraphRoute,
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
                    navController.navigateToVaccinesScreen()
                }
            )

            addResidentialAddressScreen(
                onNavigateToUploadProfileImageScreen = {
                    navController.navigateToUploadEmployeeProfileImageScreen()
                }
            )

            uploadProfileImageScreen(
                onNavigateToHomeScreenScreen = {
                    navController.navigateToVaccinesScreen()
                }
            )

            loginScreen(
                onNavigateToEnterEmailScreen = {
                    navController.navigateToEnterEmailScreen()
                },
                onNavigateToHomeScreen = {
                    navController.navigateToVaccinesScreen()
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
    }
}