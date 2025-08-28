package com.example.admin_app.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.clinics_search.navigation.clinicsSearchScreen
import com.example.doctors.navigation.DoctorSearchRoute
import com.example.doctors.navigation.doctorsSearch
import com.example.employees_search.navigation.employeeSearch
import com.example.pharmacies_search.navigation.pharmaciesSearch

@Composable
fun AdminGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            Log.d("nav graph", " route :${navController.currentDestination?.route}")
            NavigationBar {
                BottomNavItem.items.forEach { item ->
                    Log.d("nav graph", "isSelected :${navController.currentDestination?.route == item.route}")
                    NavigationBarItem(
                        icon = {
                            Icon(painterResource(item.icon), contentDescription = stringResource(item.label)) },
                        label = { Text(stringResource(item.label)) },
                        selected = navController.currentDestination?.route == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                            Log.d("nav graph", " route :${navController.currentDestination?.route}")
                        }
                    )
                }
            }
        }
    ) {innerPadding->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = DoctorSearchRoute(
                
            )
        ) {
                doctorsSearch(
                    onNavigateToDoctorProfile = {},
                    onNavigateToAdminProfile = {},
                    onNavigateToVaccines = {},
                    onNavigateToNotifications = {},
                    onNavigateToToPrescriptions = {},
                    onNavigateToToMedicalRecords = {} ,
                    onNavigateToToVaccineTable = {}
                )
                employeeSearch(
                    onNavigateToEmployeeProfile = {},
                    onNavigateToAdminProfile = {},
                    onNavigateToVaccines = {},
                    onNavigateToNotifications = {},
                    onNavigateToToPrescriptions = {},
                    onNavigateToToMedicalRecords = {},
                    onNavigateToToVaccineTable = {},
                )
                clinicsSearchScreen(
                    onNavigateToDepartmentDetails = {},
                    onNavigateToDoctorProfile = {},
                    onNavigateToNotifications = {},
                    onNavigateToMedicalRecords = {},
                    onNavigateToPrescriptions = {},
                    onNavigateToVaccines = {},
                    onNavigateToCreateNewClinic = {},
                    onNavigateToAdminProfile = {},
                    onNavigateToVaccineTable = {},
                )
                pharmaciesSearch(
                    onNavigateToPharmacyDetails = {},
                    onNavigateToAdminProfile = {},
                    onNavigateToVaccines = {},
                    onNavigateToNotifications = {},
                    onNavigateToPrescriptions = {},
                    onNavigateToMedicalRecords = {},
                    onNavigateToVaccineTable = {},
                )
        }
        }
    }