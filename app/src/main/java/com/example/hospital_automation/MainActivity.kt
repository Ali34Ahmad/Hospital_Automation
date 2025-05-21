package com.example.hospital_automation

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.add_residential_address.AddResidentialAddressNavigationUiActions
import com.example.add_residential_address.AddResidentialAddressScreen
import com.example.add_residential_address.AddResidentialAddressViewModel
import com.example.email_verification.email_verified_successfully.EmailVerifiedSuccessfullyNavigationUiActions
import com.example.email_verification.email_verified_successfully.EmailVerifiedSuccessfullyScreen
import com.example.email_verification.email_verified_successfully.EmailVerifiedSuccessfullyViewModel
import com.example.email_verification.otp_verification.OtpVerificationNavigationUiActions
import com.example.email_verification.otp_verification.OtpVerificationScreen
import com.example.email_verification.otp_verification.OtpVerificationViewModel
import com.example.employee_profile.EmployeeProfileNavigationUiActions
import com.example.employee_profile.EmployeeProfileScreen
import com.example.employee_profile.EmployeeProfileViewModel
import com.example.enter_email.EnterEmailNavigationUiActions
import com.example.enter_email.EnterEmailScreen
import com.example.enter_email.EnterEmailViewModel
import com.example.home.HomeNavigationUiActions
import com.example.home.HomeScreen
import com.example.home.HomeViewModel
import com.example.hospital_automation.app_logic.AppViewModel
import com.example.hospital_automation.app_logic.Destination
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.hospital_automation.ui.theme.scrimDark
import com.example.hospital_automation.ui.theme.scrimLight
import com.example.login.LoginNavigationUiActions
import com.example.login.LoginScreen
import com.example.login.LoginViewModel
import com.example.reset_password.ResetPasswordNavigationUiActions
import com.example.reset_password.ResetPasswordScreen
import com.example.reset_password.ResetPasswordViewModel
import com.example.signup.SignUpNavigationUiActions
import com.example.signup.SignUpScreen
import com.example.signup.SignUpViewModel
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
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                scrimLight.toArgb(),
                scrimDark.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                scrimLight.toArgb(),
                scrimDark.toArgb()
            )
        )
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
                        val emailOtpVerificationViewModel = koinViewModel<OtpVerificationViewModel>()
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
                                ))
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

                            override fun openPdfFileChooser() {
                            }
                        }

                        UploadEmployeeDocumentsScreen(
                            uiState = uploadEmployeeDocumentsUiState.value,
                            uiActions = uploadEmployeeDocumentsViewModel.getUiActions(navActions)
                        )
                    }

                    composable<Destination.AddResidentialAddress> {
                        val addResidentialAddressViewModel = koinViewModel<AddResidentialAddressViewModel>()
                        val addResidentialAddressUiState = addResidentialAddressViewModel.uiState.collectAsState()

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

                            override fun openImagePicker() {

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
                                ))
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

                        val context= LocalContext.current

                        val navActions = object : EmployeeProfileNavigationUiActions {
                            override fun navigateToCallApp(phoneNumber: String) {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = "tel:$phoneNumber".toUri()
                                }

                                if (intent.resolveActivity(context.packageManager) != null) {
                                    try {
                                        context.startActivity(intent)
                                    } catch (e: SecurityException) {
                                        Toast.makeText(context, "Could not open dialer: Permission denied.", Toast.LENGTH_SHORT).show()
                                    } catch (e: Exception) {
                                        Toast.makeText(context, "Could not open dialer.", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(context, "No application available to make calls.", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun navigateToEmail(email: String,subject: String) {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = "mailto:${Uri.encode(email)}".toUri()
                                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                                    if (subject.isNotEmpty()) {
                                        putExtra(Intent.EXTRA_SUBJECT, subject)
                                    }
                                }

                                if (intent.resolveActivity(context.packageManager) != null) {
                                    try {
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        Toast.makeText(context, "Could not open email app.", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(context, "No application available to send emails.", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun navigateToAddedChildrenScreen() {

                            }

                            override fun navigateToEmploymentHistoryScreen() {

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
                }
            }
        }
    }

    override val scope: Scope by activityScope()
}