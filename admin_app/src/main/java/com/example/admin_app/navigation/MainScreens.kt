package com.example.admin_app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.admin_profile.navigation.navigateToAdminProfileScreen
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.clinic_details.navigation.navigateToClinicDetailsScreen
import com.example.clinics_search.navigation.clinicsSearchScreen
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.doctor_profile.navigation.navigateToDoctorProfileScreen
import com.example.doctors.navigation.doctorsSearch
import com.example.employee_profile.navigation.EmployeeProfileAccessType
import com.example.employee_profile.navigation.navigateToEmployeeProfileScreen
import com.example.employees_search.navigation.employeeSearch
import com.example.employment_requests.navigation.employmentRequestsScreen
import com.example.generic_vaccination_table.navigation.GenericVaccinationTableAccessType
import com.example.generic_vaccination_table.navigation.navigateToGenericVaccinationTableScreen
import com.example.pharmacies_search.navigation.pharmaciesSearch
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.navigateToPharmacyDetailsScreen
import com.example.ui.theme.spacing
import com.example.vaccines.navigation.navigateToVaccinesScreen

fun NavGraphBuilder.mainScreens(
    navController: NavHostController,
) {
    employmentRequestsScreen(
        onNavigateToEmployeeProfileDetailsScreen = { employeeId ->
            navController.navigateToEmployeeProfileScreen(
                employeeId = employeeId,
                employeeProfileAccessType = EmployeeProfileAccessType.ADMIN_ACCESS
            )
        },
        onNavigateToPharmacyDetailsScreen = { pharmacyId ->
            navController.navigateToPharmacyDetailsScreen(
                pharmacyId = pharmacyId,
                pharmacyAccessType = PharmacyAccessType.ADMIN_ACCESS
            )
        },
        onNavigateToDoctorProfileDetailsScreen = { doctorId ->
            navController.navigateToDoctorProfileScreen(
                doctorId = doctorId,
                doctorProfileAccessType = DoctorProfileAccessType.ADMIN_ACCESS,
            )
        },
        onNavigateToAdminProfile = {
            navController.navigateToAdminProfileScreen(
                adminId = null,
            )
        },
        onNavigateToVaccines = {
            navController.navigateToVaccinesScreen()
        },
        onNavigateToVaccineTable = {
            navController.navigateToGenericVaccinationTableScreen(
                genericVaccinationTableAccessType = GenericVaccinationTableAccessType.VIEWER_ACCESS
            )
        },
    )

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