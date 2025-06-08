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
import com.example.add_child_screen.navigation.addChildScreen
import com.example.add_child_screen.navigation.navigateToAddChild
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.admin_profile.navigation.adminProfileScreen
import com.example.admin_profile.navigation.navigateToAdminProfileScreen
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.children.navigation.childrenScreen
import com.example.children.navigation.navigateToChildrenScreen
import com.example.email_verification.email_verified_successfully.navigation.emailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.navigation.navigateToEmailVerifiedSuccessfullyScreen
import com.example.email_verification.otp_verification.naviation.emailOtpVerificationScreen
import com.example.email_verification.otp_verification.naviation.navigateToEmailOtpVerificationScreen
import com.example.employee_profile.navigation.EmployeeProfileRoute
import com.example.employee_profile.navigation.ProfileAccessType
import com.example.employee_profile.navigation.employeeProfileScreen
import com.example.employee_profile.navigation.navigateToEmployeeProfileScreen
import com.example.employment_history.navigation.employmentHistoryScreen
import com.example.employment_history.navigation.navigateToEmploymentHistoryScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.guardianProfileScreen
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.guardians.navigation.navigateToGuardiansScreen
import com.example.guardians_search.navigation.guardianSearchScreen
import com.example.guardians_search.navigation.navigateToGuardiansSearch
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
                                navController.navigateToGuardiansSearch()
                            },
                            onNavigateToAddGuardianScreen = {

                            },
                            onNavigateToEmployeeProfileScreen = {
                                navController.navigateToEmployeeProfileScreen(
                                    profileAccessType = ProfileAccessType.TOKEN_ACCESS,
                                    employeeId = null
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
                        onNavigateToToResignedByAdminProfileScreen = { resignedById ->
                            navController.navigateToAdminProfileScreen(resignedById)
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

                    adminProfileScreen(
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                    )

                    guardianSearchScreen(
                        onNavigateUp = navController::navigateUp,
                        onNavigateToGuardianProfile = { guardianId, childId ->
                            navController.navigateToGuardianProfile(
                                guardianId = guardianId,
                                userProfileMode = when (childId) {
                                    null -> UserProfileMode.ADD_CHILD
                                    else -> UserProfileMode.SET_AS_GUARDIAN
                                },
                                childId = childId
                            )
                        }
                    )

                    guardianProfileScreen(
                        onNavigateUp = navController::navigateUp,
                        onNavigateToChildrenScreen = { guardianId ->
                            navController.navigateToChildrenScreen(guardianId)
                        },
                        onNavigateToAddChildScreen = { guardianId ->
                            navController.navigateToAddChild(
                                guardianId
                            )
                        }
                    )

                    childrenScreen(
                        navigateToChildProfile = navController::navigateToChildProfile,
                        navigateUp = navController::navigateUp
                    )

                    addChildScreen(
                        onNavigateUp = navController::navigateUp,
                        onNavigateToAddChildCertificate = { TODO("navigate to add certificate screen") }
                    )

                    childProfileScreen(
                        navigateToAddGuardianScreen = navController::navigateToGuardiansScreen,
                        navigateToEmployeeProfileScreen = {
                            navController.navigateToEmployeeProfileScreen(
                                profileAccessType = ProfileAccessType.ID_ACCESS,
                                employeeId = -1
                            )
                        },
                        navigateToGuardianScreen = navController::navigateToGuardiansScreen,
                        navigateUp = navController::navigateUp
                    )

                }
            }
        }
    }

    override val scope: Scope by activityScope()
}