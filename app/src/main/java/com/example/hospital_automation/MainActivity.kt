package com.example.hospital_automation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.admin_profile.navigation.adminProfileScreen
import com.example.admin_profile.navigation.navigateToAdminProfileScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.employee_profile.navigation.EmployeeProfilePreviousDestination
import com.example.employee_profile.navigation.EmployeeProfileRoute
import com.example.employee_profile.navigation.employeeProfileScreen
import com.example.employee_profile.navigation.navigateToEmployeeProfileScreen
import com.example.employment_history.navigation.employmentHistoryScreen
import com.example.employment_history.navigation.navigateToEmploymentHistoryScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.home.navigation.HomeRoute
import com.example.home.navigation.homeScreen
import com.example.home.navigation.navigateToHomeScreen
import com.example.hospital_automation.main.AppViewModel
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.navigation.extesion.switchToTap
import com.example.reset_password.navigation.navigateToResetPasswordScreen
import com.example.reset_password.navigation.resetPasswordScreen
import com.example.signup.navigation.SignUpRoute
import com.example.signup.navigation.navigateToSignUpScreen
import com.example.signup.navigation.signUpScreen
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.upload_employee_documents.navigation.navigateToUploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.navigation.uploadEmployeeDocumentsScreen
import com.example.upload_employee_profile_image.navigation.navigateToUploadEmployeeProfileImageScreen
import com.example.upload_employee_profile_image.navigation.uploadEmployeeProfileImageScreen
import kotlinx.serialization.Serializable
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope


@Serializable
object AuthGraphRoute

@Serializable
object MainGraphRoute


class MainActivity : ComponentActivity(), AndroidScopeComponent {
    private val appViewModel: AppViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appUiState = appViewModel.uiState.collectAsState()

            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    Color.TRANSPARENT, Color.TRANSPARENT,
                    detectDarkMode = {
                        appUiState.value.isDarkTheme
                    }),
                navigationBarStyle = SystemBarStyle.auto(
                    Color.TRANSPARENT, Color.TRANSPARENT,
                    detectDarkMode = {
                        appUiState.value.isDarkTheme
                    }),
            )

            Hospital_AutomationTheme(
                darkTheme = appUiState.value.isDarkTheme
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AuthGraphRoute,
                ) {
                    navigation<AuthGraphRoute>(
                        startDestination = SignUpRoute,
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
                            onNavigateToUploadEmployeeDocumentsScreen = {
                                navController.navigateToUploadEmployeeDocumentsScreen()
                            }
                        )

                        uploadEmployeeDocumentsScreen(
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
                                navController.navigate(MainGraphRoute) {
                                    popUpTo(AuthGraphRoute) {
                                        inclusive = true
                                    }
                                }
                            }
                        )

                        loginScreen(
                            onNavigateToEnterEmailScreen = {
                                navController.navigateToEnterEmailScreen()
                            },
                            onNavigateToHomeScreen = {
                                navController.navigate(MainGraphRoute) {
                                    popUpTo(AuthGraphRoute) {
                                        inclusive = true
                                    }
                                }
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
                                navController.navigateToHomeScreen()
                            }
                        )
                    }

                    navigation<MainGraphRoute>(
                        startDestination = HomeRoute,
                    ) {
                        homeScreen(
                            onNavigateToAddChildScreen = {

                            },
                            onNavigateToAddGuardianScreen = {

                            },
                            onNavigateToEmployeeProfileScreen = {
                                navController.switchToTap(
                                    route = EmployeeProfileRoute,
                                    startDestination = HomeRoute
                                )
                            },
                            onNavigateToRequestsScreen = {

                            },
                            onNavigateToAddedChildrenScreen = {

                            }
                        )

                        employeeProfileScreen(
                            onNavigateToEmploymentHistoryScreen = {
                                navController.navigateToEmploymentHistoryScreen()
                            },
                            onNavigateToLoginScreen = {
                                navController.navigateToLoginScreen()
                            },
                            onNavigateUp = {
                                navController.navigateUp()
                            },
                            onNavigateToAddedChildrenScreen = {

                            }
                        )
                    }

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
                        onNavigateToToSuspendedByAdminProfileScreen = { adminId ->
                            navController.navigateToAdminProfileScreen(adminId)
                        }
                    )

                    adminProfileScreen(
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                    )
                }
            }
        }
    }

    override val scope: Scope by activityScope()
}