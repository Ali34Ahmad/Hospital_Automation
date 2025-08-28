package com.example.clinics_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.clinics_search.presentation.ClinicsSearchNavigationActions
import com.example.clinics_search.presentation.ClinicsSearchScreen
import com.example.clinics_search.presentation.ClinicsSearchViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class ClinicsSearchRoute(
    val hasAdminAccess: Boolean = false,
)

fun NavController.navigateToClinicsSearch(
    hasAdminAccess: Boolean = false,
){
    navigateToScreen(
        route = ClinicsSearchRoute(
            hasAdminAccess
        )
    )
}

fun NavGraphBuilder.clinicsSearchScreen(
    onNavigateToDepartmentDetails: (clinicId: Int)-> Unit,
    onNavigateToDoctorProfile: ()-> Unit,
    onNavigateToNotifications: ()-> Unit,
    onNavigateToMedicalRecords: ()-> Unit,
    onNavigateToPrescriptions: ()-> Unit,
    onNavigateToVaccines: ()-> Unit,
    onNavigateToCreateNewClinic: ()-> Unit ,
    onNavigateToAdminProfile: ()-> Unit,
    onNavigateToVaccineTable: ()-> Unit
    ){
    composable<ClinicsSearchRoute> {
        val viewModel = koinViewModel<ClinicsSearchViewModel>()
        val navigationActions = object : ClinicsSearchNavigationActions{
            override fun navigateToDepartmentDetails(clinicId: Int) = onNavigateToDepartmentDetails(clinicId)
            override fun navigateToDoctorProfile() = onNavigateToDoctorProfile()
            override fun navigateToAdminProfile() = onNavigateToAdminProfile()

            override fun navigateToNotifications() = onNavigateToNotifications()
            override fun navigateToMedicalRecords() = onNavigateToMedicalRecords()
            override fun navigateToPrescriptions() = onNavigateToPrescriptions()
            override fun navigateToVaccines() = onNavigateToVaccines()
            override fun navigateToVaccineTable() = onNavigateToVaccineTable()

            override fun navigateToCreateNewClinic() = onNavigateToCreateNewClinic()

        }
        ClinicsSearchScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
