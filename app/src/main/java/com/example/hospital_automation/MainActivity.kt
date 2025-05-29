package com.example.hospital_automation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.add_residential_address.main.AddResidentialAddressNavigationUiActions
import com.example.add_residential_address.main.AddResidentialAddressScreen
import com.example.add_residential_address.main.AddResidentialAddressViewModel
import com.example.admin_profile.AdminProfileNavigationUiActions
import com.example.admin_profile.AdminProfileScreen
import com.example.admin_profile.AdminProfileViewModel
import com.example.email_verification.email_verified_successfully.EmailVerifiedSuccessfullyNavigationUiActions
import com.example.email_verification.email_verified_successfully.EmailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.EmailVerifiedSuccessfullyViewModel
import com.example.email_verification.otp_verification.main.OtpVerificationNavigationUiActions
import com.example.email_verification.otp_verification.main.OtpVerificationScreen
import com.example.email_verification.otp_verification.main.OtpVerificationViewModel
import com.example.employee_profile.EmployeeProfileNavigationUiActions
import com.example.employee_profile.EmployeeProfileScreen
import com.example.employee_profile.EmployeeProfileViewModel
import com.example.employment_history.EmploymentHistoryNavigationUiActions
import com.example.employment_history.EmploymentHistoryScreen
import com.example.employment_history.EmploymentHistoryViewModel
import com.example.enter_email.main.EnterEmailNavigationUiActions
import com.example.enter_email.main.EnterEmailScreen
import com.example.enter_email.main.EnterEmailViewModel
import com.example.home.HomeNavigationUiActions
import com.example.home.HomeScreen
import com.example.home.HomeViewModel
import com.example.hospital_automation.app_logic.AppViewModel
import com.example.hospital_automation.app_logic.Destination
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.login.main.LoginNavigationUiActions
import com.example.login.main.LoginScreen
import com.example.login.main.LoginViewModel
import com.example.reset_password.main.ResetPasswordNavigationUiActions
import com.example.reset_password.main.ResetPasswordScreen
import com.example.reset_password.main.ResetPasswordViewModel
import com.example.signup.main.SignUpNavigationUiActions
import com.example.signup.main.SignUpScreen
import com.example.signup.main.SignUpViewModel
import com.example.upload_employee_documents.UploadEmployeeDocumentsNavigationUiActions
import com.example.upload_employee_documents.UploadEmployeeDocumentsScreen
import com.example.upload_employee_documents.UploadEmployeeDocumentsViewModel
import com.example.upload_employee_profile_image.UploadEmployeeProfileImageNavigationUiActions
import com.example.upload_employee_profile_image.UploadEmployeeProfileImageScreen
import com.example.upload_employee_profile_image.UploadEmployeeProfileImageViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity(), AndroidScopeComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val appViewModel = koinViewModel<AppViewModel>()
            val appUiState = appViewModel.uiState.collectAsState()

            Hospital_AutomationTheme(
                darkTheme = appUiState.value.isDarkTheme
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destination.SignUp,
                ) {
                    composable<Destination.SignUp> {
                        val signUpViewModel = koinViewModel<SignUpViewModel>()
                        val signUpUiState = signUpViewModel.uiState.collectAsState()

                        val navActions = object : SignUpNavigationUiActions {
                            override fun navigateToEmailVerificationScreen() {
                                navController.navigate(
                                    Destination.EmailOtpVerification(
                                        signUpUiState.value.email,
                                        signUpUiState.value.password,
                                        navigateToResetPassword = false
                                    )
                                )
                            }

                            override fun navigateToLoginScreen() {
                                navController.navigate(Destination.Login)
                            }
                        }

                        SignUpScreen(
                            uiState = signUpUiState.value,
                            uiActions = signUpViewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.EmailOtpVerification> {
                        val emailOtpVerificationViewModel =
                            koinViewModel<OtpVerificationViewModel>()
                        val emailOtpVerificationUiState =
                            emailOtpVerificationViewModel.uiState.collectAsState()

                        val navActions = object : OtpVerificationNavigationUiActions {
                            override fun navigateToEmailVerifiedSuccessfullyScreen() {
                                navController.navigate(
                                    Destination.EmailVerifiedSuccessfully(
                                        email = emailOtpVerificationUiState.value.email,
                                        password = emailOtpVerificationUiState.value.password,
                                        navigateToResetPassword = emailOtpVerificationUiState.value.navigateToResetPassword,
                                    )
                                )
                            }
                        }

                        OtpVerificationScreen(
                            uiState = emailOtpVerificationUiState.value,
                            uiActions = emailOtpVerificationViewModel.getUiActions(navActions),
                        )
                    }

                    composable<Destination.EmailVerifiedSuccessfully> {
                        val emailVerifiedSuccessfullyViewModel =
                            koinViewModel<EmailVerifiedSuccessfullyViewModel>()
                        val emailVerifiedSuccessfullyUiState =
                            emailVerifiedSuccessfullyViewModel.uiState.collectAsState()

                        val navActions = object : EmailVerifiedSuccessfullyNavigationUiActions {
                            override fun navigateToUploadEmployeeDocumentsScreen() {
                                navController.navigate(Destination.UploadEmployeeDocuments)
                            }

                            override fun navigateToResetPasswordScreen() {
                                navController.navigate(
                                    Destination.ResetPassword(
                                        email = emailVerifiedSuccessfullyUiState.value.email
                                    )
                                )
                            }
                        }
                        EmailVerifiedSuccessfullyScreen(
                            uiState = emailVerifiedSuccessfullyUiState.value,
                            uiActions = emailVerifiedSuccessfullyViewModel.getUiActions(navActions),
                        )
                    }

                    composable<Destination.UploadEmployeeDocuments> {
                        val uploadEmployeeDocumentsViewModel =
                            koinViewModel<UploadEmployeeDocumentsViewModel>()
                        val uploadEmployeeDocumentsUiState =
                            uploadEmployeeDocumentsViewModel.uiState.collectAsState()

                        val navActions = object : UploadEmployeeDocumentsNavigationUiActions {
                            override fun navigateToAddResidentialAddressScreen() {
                                navController.navigate(Destination.AddResidentialAddress)
                            }
                        }

                        UploadEmployeeDocumentsScreen(
                            uiState = uploadEmployeeDocumentsUiState.value,
                            uiActions = uploadEmployeeDocumentsViewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.AddResidentialAddress> {
                        val addResidentialAddressViewModel =
                            koinViewModel<AddResidentialAddressViewModel>()
                        val addResidentialAddressUiState =
                            addResidentialAddressViewModel.uiState.collectAsState()

                        val navActions = object : AddResidentialAddressNavigationUiActions {
                            override fun navigateToUploadProfileImageScreen() {
                                navController.navigate(Destination.UploadEmployeeProfileImage)
                            }

                        }

                        AddResidentialAddressScreen(
                            uiState = addResidentialAddressUiState.value,
                            uiActions = addResidentialAddressViewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.UploadEmployeeProfileImage> {
                        val viewModel = koinViewModel<UploadEmployeeProfileImageViewModel>()
                        val uiState = viewModel.uiState.collectAsState()

                        val navActions = object : UploadEmployeeProfileImageNavigationUiActions {
                            override fun navigateToHomeScreenScreen() {
                                navController.navigate(Destination.Home)
                            }
                        }

                        UploadEmployeeProfileImageScreen(
                            uiState = uiState.value,
                            uiActions = viewModel.getUiActions(navActions)
                        )
                    }


                    composable<Destination.Login> {
                        val loginViewModel = koinViewModel<LoginViewModel>()
                        val loginUiState = loginViewModel.uiState.collectAsState()

                        val navActions = object : LoginNavigationUiActions {
                            override fun navigateToSignUpScreen() {
                                navController.navigate(Destination.SignUp)
                            }

                            override fun navigateToEnterEmailScreen() {
                                navController.navigate(Destination.EnterEmail)
                            }

                            override fun navigateToHomeScreen() {
                                navController.navigate(Destination.Home)
                            }

                        }
                        LoginScreen(
                            uiState = loginUiState.value,
                            uiActions = loginViewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.ResetPassword> {
                        val resetPasswordViewModel = koinViewModel<ResetPasswordViewModel>()
                        val resetPasswordUiState = resetPasswordViewModel.uiState.collectAsState()

                        val navActions = object : ResetPasswordNavigationUiActions {
                            override fun navigateToHomeScreen() {
                                navController.navigate(Destination.Home)
                            }

                        }
                        ResetPasswordScreen(
                            uiState = resetPasswordUiState.value,
                            uiActions = resetPasswordViewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.EnterEmail> {
                        val enterEmailViewModel = koinViewModel<EnterEmailViewModel>()
                        val emailEmailUiState = enterEmailViewModel.uiState.collectAsState()

                        val navActions = object : EnterEmailNavigationUiActions {
                            override fun navigateToEmailOtpVerificationScreen() {
                                navController.navigate(
                                    Destination.EmailOtpVerification(
                                        email = emailEmailUiState.value.email,
                                        navigateToResetPassword = true,
                                    )
                                )
                            }
                        }
                        EnterEmailScreen(
                            uiState = emailEmailUiState.value,
                            uiActions = enterEmailViewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.Home> {
                        val viewModel = koinViewModel<HomeViewModel>()
                        val uiState = viewModel.uiState.collectAsState()

                        val navActions = object : HomeNavigationUiActions {
                            override fun navigateToAddChildScreen() {

                            }

                            override fun navigateToAddGuardianScreen() {

                            }

                            override fun navigateToEmployeeProfileScreen() {
                                navController.navigate(Destination.EmployeeProfile)
                            }

                            override fun navigateToRequestsScreen() {

                            }

                            override fun navigateToAddedChildrenScreen() {

                            }

                        }
                        HomeScreen(
                            uiState = uiState.value,
                            uiActions = viewModel.getUiActions(navActions)
                        )
                    }
                    composable<Destination.EmployeeProfile> {
                        val viewModel = koinViewModel<EmployeeProfileViewModel>()
                        val uiState = viewModel.uiState.collectAsState()

                        val navActions = object : EmployeeProfileNavigationUiActions {
                            override fun navigateToAddedChildrenScreen() {

                            }

                            override fun navigateToEmploymentHistoryScreen() {
                                navController.navigate(Destination.EmploymentHistory)
                            }

                            override fun navigateUp() {
                                navController.navigateUp()
                            }

                            override fun navigateToLoginScreen() {
                                navController.navigate(Destination.Login)
                            }

                        }
                        EmployeeProfileScreen(
                            uiState = uiState.value,
                            uiActions = viewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.EmploymentHistory> {
                        val viewModel = koinViewModel<EmploymentHistoryViewModel>()
                        val uiState = viewModel.uiState.collectAsState()

                        val navActions = object : EmploymentHistoryNavigationUiActions {
                            override fun navigateToAcceptedByAdminProfileScreen() {
                                navController.navigate(
                                    Destination.AdminProfile(
                                        adminId = uiState.value.employmentHistory?.acceptedBy?.userId
                                            ?: -1
                                    )
                                )
                            }

                            override fun navigateToToResignedByAdminProfileScreen() {
                                navController.navigate(
                                    Destination.AdminProfile(
                                        adminId = uiState.value.employmentHistory?.resignedBy?.userId
                                            ?: -1
                                    )
                                )
                            }

                            override fun navigateToToSuspendedByAdminProfileScreen() {
                                navController.navigate(
                                    Destination.AdminProfile(
                                        adminId = uiState.value.employmentHistory?.suspendedBy?.userId
                                            ?: -1
                                    )
                                )
                            }

                            override fun navigateUp() {
                                navController.navigateUp()
                            }


                        }
                        EmploymentHistoryScreen(
                            uiState = uiState.value,
                            uiActions = viewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.AdminProfile> {
                        val viewModel = koinViewModel<AdminProfileViewModel>()
                        val uiState = viewModel.uiState.collectAsState()

                        val navActions = object : AdminProfileNavigationUiActions {
                            override fun navigateUp() {
                                navController.navigateUp()
                            }
                        }
                        AdminProfileScreen(
                            uiState = uiState.value,
                            uiActions = viewModel.getUiActions(navActions)
                        )
                    }
                }
            }
        }
    }

    override val scope: Scope by activityScope()
}