package com.example.admin_app.navigation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.admin_app.navigation.helper.getDoctorDestinationOrNull
import com.example.appointment_details.navigation.appointmentDetailsScreen
import com.example.appointment_details.navigation.navigateToAppointmentDetails
import com.example.child_profile.navigation.ChildProfileRoute
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.children.navigation.ChildrenRoute
import com.example.children.navigation.childrenScreen
import com.example.children.navigation.navigateToChildrenScreen
import com.example.children_search.navigation.ChildrenSearchRoute
import com.example.children_search.navigation.SearchType
import com.example.children_search.navigation.childrenSearchScreen
import com.example.clinic_details.navigation.ClinicDetailsRoute
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.clinicDetailsScreen
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.doctor_schedule.navigation.ScheduleRoute
import com.example.doctor_schedule.navigation.navigateToScheduleScreen
import com.example.doctor_schedule.navigation.scheduleScreen
import com.example.doctors.navigation.navigateToDoctorsSearch
import com.example.guardian_profile.navigation.GuardianProfileRoute
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.guardianProfileScreen
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.guardians.navigation.guardiansScreen
import com.example.guardians.navigation.navigateToGuardiansScreen
import com.example.medicine_details.navigation.medicineDetailsScreen
import com.example.medicine_details.navigation.navigateToMedicineDetails
import com.example.pharmacy_medicines.navigation.PharmacyMedicinesRoute
import com.example.pharmacy_medicines.navigation.pharmacyMedicines

@Composable
fun AdminGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            val items = BottomNavItem.items
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            //get the current doctor destination or null
            val doctorDestination by remember(navBackStackEntry){
                mutableStateOf(getDoctorDestinationOrNull(navBackStackEntry))
            }

            val isPrimaryDestination by remember (navBackStackEntry) {
                derivedStateOf {
                    doctorDestination?.isPrimary?:true
                }
            }



            var selectedDestination by rememberSaveable {
                mutableIntStateOf(0)
            }

            val shouldShowBottomBar = items.any { item ->
                currentDestination?.hasRoute(item.route::class) == true
                        &&isPrimaryDestination
            }

            AnimatedVisibility (
                shouldShowBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { it }
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { it }
                ) + fadeOut()
            ){
                NavigationBar {
                    items.forEachIndexed{ index , item ->
                        NavigationBarItem(
                            icon = {
                                Icon(painterResource(item.icon), contentDescription = stringResource(item.label)) },
                            label = { Text(stringResource(item.label)) },
                            selected = index == selectedDestination,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
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
    ) {innerPadding->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = ScheduleRoute(
                id = 1,
                hasAdminAccess = true,
                searchType = AppointmentSearchType.CHILD,
                name = "Ali Ahmad",
                speciality = null,
                imageUrl = null
//                    "https://sdmntpritalynorth.oaiusercontent.com/files/00000000-c8f0-6246-b999-336fbdd875fd/raw?se=2025-09-01T07%3A29%3A10Z&sp=r&sv=2024-08-04&sr=b&scid=acc53f61-4ade-5e2c-b554-748c5f915419&skoid=0a4a0f0c-99ac-4752-9d87-cfac036fa93f&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-09-01T05%3A51%3A55Z&ske=2025-09-02T05%3A51%3A55Z&sks=b&skv=2024-08-04&sig=13kJ50s50dqASQiG2v7SAgFwdpNx6SGABR5DUW2RW9g%3D"
            )
        ) {
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
                    TODO("not yet implemented")
                },
                onNavigateToAllDoctors = {clinicId,clinicName->
                    navController.navigateToDoctorsSearch(
                        clinicId = clinicId,
                        clinicName = clinicName,
                        showNavBar = false
                    )
                },
                onNavigateToAllAppointments = {
                    TODO("feature not found")
                },
                onNavigateToMedicalRecords = {
                    TODO("not yet implemented")
                },
                onNavigateToContractHistory = {
                    TODO("not yet implemented")
                },
                onNavigateToPrescriptions = {
                    TODO("not yet implemented")
                },
                onNavigateToEditClinic = {
                    TODO("feature not found")
                }
            )
            guardianProfileScreen(
                onNavigateUp = navController::navigateUp,
                onNavigateToChildrenScreen = {userId->
                    navController.navigateToChildrenScreen(userId)
                },
                onNavigateToAddChildScreen = {
                    //no need here
                },
                onNavigateToAppointments = {userId,name,imageUrl->
                    navController.navigateToScheduleScreen(
                        id = userId,
                        hasAdminAccess = true,
                        searchType = AppointmentSearchType.USER,
                        name = name,
                        speciality = null,
                        imageUrl = imageUrl
                    )
                },
                onNavigateToPrescriptions = {
                    TODO("not yet implemented")
                },
                onNavigateToMedicalRecord = {
                    TODO("not yet implemented")
                }
            )
            //guardian children
            childrenScreen(
                navigateToChildProfile = {childId->
                    navController.navigateToChildProfile(
                        childId = childId,
                        hasAdminAccess = true
                    )
                },
                navigateUp = navController::navigateUp
            )
            //child's guardians
            guardiansScreen(
                onNavigateUp = navController::navigateUp,
                onNavigateToGuardianProfile = {userId->
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
                navigateToEmployeeProfileScreen = {employeeId->
                    TODO("not yet implemented")
                },
                navigateToGuardiansScreen = navController::navigateToGuardiansScreen,
                navigateUp = navController::navigateUp,
                onNavigateToVaccinationTable = {
                    TODO("not yet implemented")
                },
                onNavigateToAppointments = { childId,name->
                    navController.navigateToScheduleScreen(
                        id = childId,
                        hasAdminAccess = true,
                        searchType = AppointmentSearchType.CHILD,
                        name = name,
                        speciality = null,
                        imageUrl = null
                    )
                },
                onNavigateToPrescriptions = {
                    TODO("not yet implemented")
                },
                onNavigateToMedicalRecords = {
                    TODO("not yet implemented")
                },
                onNavigateToAppointmentDetails = {appointmentId->
                    navController.navigateToAppointmentDetails(
                        appointmentId = appointmentId,
                        canEdit = false
                    )
                }
            )
            pharmacyMedicines(
                onNavigateUp = navController::navigateUp,
                onNavigateToPharmacy = {
                    TODO("Not yet implemented")
                },
                onNavigateToMedicineDetails = {medicineId->
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
                onNavigateToChildDetail = {childId->
                    navController.navigateToChildProfile(
                        childId = childId,
                        hasAdminAccess = true
                    )
                }
            )
            scheduleScreen(
                onNavigateToAppointmentDetails = {appointmentId->
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
                onNavigateToDepartmentDetails = {clinicId->
                    navController.navigateToClinicDetailsScreen(
                        clinicId = clinicId,
                        type = ClinicDetailsType.ADMIN_ACCESS
                    )
                },
                onNavigateToVaccineDetails = {
                    TODO("Not yet implemented")
                },
                onNavigateToAddMedicalDiagnosis = {_,_,_,_,_->
                    //no need here
                }
            ) { userId ->
                navController.navigateToGuardianProfile(
                    guardianId = userId,
                    userProfileMode = UserProfileMode.ADMIN_ACCESS,
                    childId = null
                )
            }
        }
    }
}

