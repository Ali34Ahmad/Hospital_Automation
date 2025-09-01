package com.example.admin_app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.clinics_search.navigation.clinicsSearchScreen
import com.example.doctors.navigation.doctorsSearch
import com.example.employees_search.navigation.employeeSearch
import com.example.pharmacies_search.navigation.pharmaciesSearch

fun NavGraphBuilder.mainScreens(
    navController: NavHostController
) {
    doctorsSearch(
        onNavigateUp = navController::navigateUp,
        onNavigateToDoctorProfile = {
            TODO("not yet implemented")
        },
        onNavigateToAdminProfile = {
            TODO("not yet implemented")
        },
        onNavigateToVaccines = {
            TODO("not yet implemented")
        },
        onNavigateToNotifications = {
            TODO("not yet implemented")
        },
        onNavigateToToPrescriptions = {
            TODO("not yet implemented")
        },
        onNavigateToToMedicalRecords = {
            TODO("not yet implemented")
        } ,
        onNavigateToToVaccineTable = {
            TODO("not yet implemented")
        }
    )
    employeeSearch(
        onNavigateToEmployeeProfile = {
            TODO("not yet implemented")
        },
        onNavigateToAdminProfile = {
            TODO("not yet implemented")
        },
        onNavigateToVaccines = {
            TODO("not yet implemented")
        },
        onNavigateToNotifications = {
            TODO("not yet implemented")
        },
        onNavigateToToPrescriptions = {
            TODO("not yet implemented")
        },
        onNavigateToToMedicalRecords = {
            TODO("not yet implemented")
        },
        onNavigateToToVaccineTable = {
            TODO("not yet implemented")
        },
    )
    clinicsSearchScreen(
        onNavigateToDepartmentDetails = {clinicId->
            navController.navigateToClinicDetailsScreen(
                clinicId = clinicId,
                type = ClinicDetailsType.ADMIN_ACCESS
            )
        },
        onNavigateToDoctorProfile = {
            TODO("not yet implemented")
        },
        onNavigateToNotifications = {
            TODO("not yet implemented")
        },
        onNavigateToMedicalRecords = {
            TODO("not yet implemented")
        },
        onNavigateToPrescriptions = {
            TODO("not yet implemented")
        },
        onNavigateToVaccines = {
            TODO("not yet implemented")
        },
        onNavigateToCreateNewClinic = {
            TODO("feature not found")
        },
        onNavigateToAdminProfile = {
            TODO("not yet implemented")
        },
        onNavigateToVaccineTable = {
            TODO("not yet implemented")
        },
    )
    pharmaciesSearch(
        onNavigateToPharmacyDetails = {
            TODO("not yet implemented")
        },
        onNavigateToAdminProfile = {
            TODO("not yet implemented")
        },
        onNavigateToVaccines = {
            TODO("not yet implemented")
        },
        onNavigateToNotifications = {
            TODO("not yet implemented")
        },
        onNavigateToPrescriptions = {
            TODO("not yet implemented")
        },
        onNavigateToMedicalRecords = {
            TODO("not yet implemented")
        },
        onNavigateToVaccineTable = {
            TODO("not yet implemented")
        },
    )
}