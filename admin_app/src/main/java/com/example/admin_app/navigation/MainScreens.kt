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
import com.example.model.enums.Role
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
            navController.navigateToVaccinesScreen(Role.ADMIN)
        },
        onNavigateToVaccineTable = {
            navController.navigateToGenericVaccinationTableScreen(
                genericVaccinationTableAccessType = GenericVaccinationTableAccessType.VIEWER_ACCESS
            )
        },
    )

    doctorsSearch(
        onNavigateUp = navController::navigateUp,
        onNavigateToDoctorProfile = { doctorId ->
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
            navController.navigateToVaccinesScreen(Role.ADMIN)
        },
        onNavigateToNotifications = { },
        onNavigateToToPrescriptions = { },
        onNavigateToToMedicalRecords = { },
        onNavigateToToVaccineTable = {
            navController.navigateToGenericVaccinationTableScreen(
                genericVaccinationTableAccessType = GenericVaccinationTableAccessType.VIEWER_ACCESS
            )
        }
    )
    employeeSearch(
        onNavigateToEmployeeProfile = { employeeId ->
            navController.navigateToEmployeeProfileScreen(
                employeeId = employeeId,
                employeeProfileAccessType = EmployeeProfileAccessType.ADMIN_ACCESS
            )
        },
        onNavigateToAdminProfile = {
            navController.navigateToAdminProfileScreen(
                adminId = null,
            )
        },
        onNavigateToVaccines = {
            navController.navigateToVaccinesScreen(Role.ADMIN)
        },
        onNavigateToNotifications = { },
        onNavigateToToPrescriptions = { },
        onNavigateToToMedicalRecords = { },
        onNavigateToToVaccineTable = {
            navController.navigateToGenericVaccinationTableScreen(
                genericVaccinationTableAccessType = GenericVaccinationTableAccessType.VIEWER_ACCESS
            )
        },
    )
    clinicsSearchScreen(
        onNavigateToDepartmentDetails = { clinicId ->
            navController.navigateToClinicDetailsScreen(
                clinicId = clinicId,
                type = ClinicDetailsType.ADMIN_ACCESS
            )
        },
        onNavigateToDoctorProfile = { },
        onNavigateToNotifications = { },
        onNavigateToMedicalRecords = { },
        onNavigateToPrescriptions = { },
        onNavigateToVaccines = {
            navController.navigateToVaccinesScreen(Role.ADMIN)
        },
        onNavigateToCreateNewClinic = {},
        onNavigateToAdminProfile = {
            navController.navigateToAdminProfileScreen(
                adminId = null,
            )
        },
        onNavigateToVaccineTable = {
            navController.navigateToGenericVaccinationTableScreen(
                genericVaccinationTableAccessType = GenericVaccinationTableAccessType.VIEWER_ACCESS
            )
        },
    )
    pharmaciesSearch(
        onNavigateToPharmacyDetails = { pharmacyId ->
            navController.navigateToPharmacyDetailsScreen(
                pharmacyId = pharmacyId,
                pharmacyAccessType = PharmacyAccessType.ADMIN_ACCESS
            )
        },
        onNavigateToAdminProfile = {
            navController.navigateToAdminProfileScreen(
                adminId = null,
            )
        },
        onNavigateToVaccines = {
            navController.navigateToVaccinesScreen(Role.ADMIN)
        },
        onNavigateToNotifications = { },
        onNavigateToPrescriptions = { },
        onNavigateToMedicalRecords = {},
        onNavigateToVaccineTable = {
            navController.navigateToGenericVaccinationTableScreen(
                genericVaccinationTableAccessType = GenericVaccinationTableAccessType.VIEWER_ACCESS
            )
        },
    )
}