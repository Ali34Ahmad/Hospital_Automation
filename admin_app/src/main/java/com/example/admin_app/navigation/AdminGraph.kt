package com.example.admin_app.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.add_residential_address.navigation.addResidentialAddressScreen
import com.example.add_residential_address.navigation.navigateToAddResidentialAddressScreen
import com.example.admin_app.navigation.helper.getDoctorDestinationOrNull
import com.example.admin_profile.navigation.navigateToAdminProfileScreen
import com.example.appointment_details.navigation.appointmentDetailsScreen
import com.example.appointment_details.navigation.navigateToAppointmentDetails
import com.example.child_profile.navigation.ChildProfileMode
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.children.navigation.childrenScreen
import com.example.children.navigation.navigateToChildrenScreen
import com.example.children_search.navigation.SearchType
import com.example.children_search.navigation.childrenSearchScreen
import com.example.children_search.navigation.navigateToChildrenSearch
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.clinicDetailsScreen
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.doctor_profile.navigation.doctorProfileScreen
import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.doctor_schedule.navigation.navigateToScheduleScreen
import com.example.doctor_schedule.navigation.scheduleScreen
import com.example.doctors.navigation.navigateToDoctorsSearch
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
import com.example.employment_requests.navigation.navigateToEmploymentRequestsScreen
import com.example.enter_email.navigation.enterEmailScreen
import com.example.enter_email.navigation.navigateToEnterEmailScreen
import com.example.generic_vaccination_table.navigation.genericVaccineDetailsScreen
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.guardianProfileScreen
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.guardians.navigation.guardiansScreen
import com.example.guardians.navigation.navigateToGuardiansScreen
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLoginScreen
import com.example.medical_records.navigation.medicalRecordsScreen
import com.example.medical_records.navigation.navigateToMedicalRecordsScreen
import com.example.medicine_details.navigation.medicineDetailsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.navigateToPharmacyDetailsScreen
import com.example.pharmacy_details.navigation.pharmacyDetailsScreen
import com.example.pharmacy_medicines.navigation.navigateToPharmacyMedicines
import com.example.pharmacy_medicines.navigation.pharmacyMedicines
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
fun AdminGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        bottomBar = {
            val items = BottomNavItem.items
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            //get the current doctor destination or null
            val doctorDestination by remember(navBackStackEntry) {
                mutableStateOf(getDoctorDestinationOrNull(navBackStackEntry))
            }

            val isPrimaryDestination by remember(navBackStackEntry) {
                derivedStateOf {
                    doctorDestination?.isPrimary ?: true
                }
            }


            var selectedDestination by rememberSaveable {
                mutableIntStateOf(0)
            }

            val shouldShowBottomBar = items.any { item ->
                currentDestination?.hasRoute(item.route::class) == true
                        && isPrimaryDestination
            }

            AnimatedVisibility(
                shouldShowBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { it }
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { it }
                ) + fadeOut()
            ) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painterResource(item.icon),
                                    contentDescription = stringResource(item.label)
                                )
                            },
                            label = { Text(stringResource(item.label)) },
                            selected = index == selectedDestination,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                                selectedDestination = index
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(PaddingValues(bottom = innerPadding.calculateBottomPadding())),
            navController = navController,
            startDestination = EmploymentRequestsRoute
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
                onNavigateToMedicinesScreen = { pharmacyId ->
                    TODO()
                    navController.navigateToPharmacyMedicines(
                        pharmacyId = pharmacyId,
                        name = "",
                        imageUrl = "",
                    )
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
                onNavigateToMedicineDetails = { medicineId ->
                    navController.navigateToMedicineDetails(
                        medicineId = medicineId,
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
                onNavigateToVaccineDetailsScreen = { vaccineId ->
                    navController.navigateToVaccineDetailsScreen(
                        vaccineId = vaccineId,
                        vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS
                    )
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )

            //Ali Mansoura
            mainScreens(navController)
            clinicDetailsScreen(
                onNavigateUp = navController::navigateUp,
                onNavigateToDoctorProfile = {
                    TODO("not yet implemented")
                },
                onNavigateToScheduleScreen = {
                    //no need here because it is used in the doctor bottomBar
                },
                onNavigateToVaccines = {
                    navController.navigateToVaccinesScreen()
                },
                onNavigateToAllDoctors = { clinicId, clinicName ->
                    navController.navigateToDoctorsSearch(
                        clinicId = clinicId,
                        clinicName = clinicName,
                        showNavBar = false
                    )
                },
                onNavigateToAllAppointments = { },
                onNavigateToMedicalRecords = { },
                onNavigateToContractHistory = { },
                onNavigateToPrescriptions = { },
                onNavigateToEditClinic = { }
            )

            guardianProfileScreen(
                onNavigateUp = navController::navigateUp,
                onNavigateToChildrenScreen = { userId ->
                    navController.navigateToChildrenScreen(userId)
                },
                onNavigateToAddChildScreen = {
                    //no need here
                },
                onNavigateToAppointments = { userId, name, imageUrl ->
                    navController.navigateToScheduleScreen(
                        id = userId,
                        hasAdminAccess = true,
                        searchType = AppointmentSearchType.USER,
                        name = name,
                        speciality = null,
                        imageUrl = imageUrl
                    )
                },
                onNavigateToPrescriptions = { },
                onNavigateToMedicalRecord = { }
            )
            //guardian children
            childrenScreen(
                navigateToChildProfile = { childId ->
                    navController.navigateToChildProfile(
                        childId = childId,
                        childProfileMode = ChildProfileMode.ADMIN_ACCESS,
                    )
                },
                navigateUp = navController::navigateUp
            )
            //child's guardians
            guardiansScreen(
                onNavigateUp = navController::navigateUp,
                onNavigateToGuardianProfile = { userId ->
                    navController.navigateToGuardianProfile(
                        guardianId = userId,
                        userProfileMode = UserProfileMode.ADMIN_ACCESS,
                        childId = null
                    )
                }
            )

            childProfileScreen(
                navigateToAddGuardianScreen = {
                    // no need here
                },
                navigateToEmployeeProfileScreen = { employeeId ->
                    TODO("not yet implemented")
                },
                navigateToGuardiansScreen = navController::navigateToGuardiansScreen,
                navigateUp = navController::navigateUp,
                onNavigateToVaccinationTable = {
                    TODO("not yet implemented")
                },
                onNavigateToAppointments = { childId, name ->
                    navController.navigateToScheduleScreen(
                        id = childId,
                        hasAdminAccess = true,
                        searchType = AppointmentSearchType.CHILD,
                        name = name,
                        speciality = null,
                        imageUrl = null
                    )
                },
                onNavigateToPrescriptions = { },
                onNavigateToMedicalRecords = {},
                onNavigateToAppointmentDetails = { appointmentId ->
                    navController.navigateToAppointmentDetails(
                        appointmentId = appointmentId,
                        canEdit = false
                    )
                }
            )
            pharmacyMedicines(
                onNavigateUp = navController::navigateUp,
                onNavigateToPharmacy = { pharmacyId ->
                    navController.navigateToPharmacyDetailsScreen(
                        pharmacyId = pharmacyId,
                        pharmacyAccessType = PharmacyAccessType.ADMIN_ACCESS
                    )
                },
                onNavigateToMedicineDetails = { medicineId ->
                    navController.navigateToMedicineDetails(
                        medicineId = medicineId
                    )
                }
            )
            medicineDetailsScreen(
                onNavigateUp = navController::navigateUp
            )
            //children search : employee - global
            childrenSearchScreen(
                onNavigateUp = navController::navigateUp,
                onNavigateToChildDetail = { childId ->
                    navController.navigateToChildProfile(
                        childId = childId,
                        childProfileMode = ChildProfileMode.ADMIN_ACCESS,
                    )
                }
            )
            scheduleScreen(
                onNavigateToAppointmentDetails = { appointmentId ->
                    navController.navigateToAppointmentDetails(
                        appointmentId = appointmentId,
                        canEdit = false
                    )
                },
                onNavigateToProfile = {

                },
                onNavigateToMedicalRecords = {

                },
                onNavigateToPrescriptions = {

                },
                onNavigateToVaccines = {},
                onNavigateToNotifications = {

                },
                onNavigateToVaccineTable = {},
                onNavigateToUserProfile = {},
                onNavigateToChildProfile = {},
                onNavigateUp = navController::navigateUp
            )
            appointmentDetailsScreen(
                onNavigateUp = navController::navigateUp,
                onNavigateToDepartmentDetails = { clinicId ->
                    navController.navigateToClinicDetailsScreen(
                        clinicId = clinicId,
                        type = ClinicDetailsType.ADMIN_ACCESS
                    )
                },
                onNavigateToVaccineDetails = { vaccineId ->
                    navController.navigateToVaccineDetailsScreen(
                        vaccineId = vaccineId,
                        vaccinePreviousScreen = VaccinePreviousScreen.NORMAL_ACCESS
                    )
                },
                onNavigateToAddMedicalDiagnosis = { _, _, _, _, _ ->
                    //no need here
                },
                onNavigateToGuardianProfile = { userId ->
                    navController.navigateToGuardianProfile(
                        guardianId = userId,
                        userProfileMode = UserProfileMode.ADMIN_ACCESS,
                        childId = null
                    )
                }
            )
        }
    }
}

