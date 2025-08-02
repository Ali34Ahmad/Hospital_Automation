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
object ClinicsSearchRoute

fun NavController.navigateToClinicsSearch(){
    navigateToScreen(
        route = ClinicsSearchRoute
    )
}

fun NavGraphBuilder.clinicsSearchScreen(
    onNavigateToDepartmentDetails: (clinicId: Int)-> Unit,
    onNavigateToDoctorProfile: ()-> Unit,
    onNavigateToNotifications: ()-> Unit,
    onNavigateToMedicalRecords: ()-> Unit,
    onNavigateToPrescriptions: ()-> Unit,
    onNavigateToVaccines: ()-> Unit,
    ){
    composable<ClinicsSearchRoute> {
        val viewModel = koinViewModel<ClinicsSearchViewModel>()
        val navigationActions = object : ClinicsSearchNavigationActions{
            override fun navigateToDepartmentDetails(clinicId: Int) = onNavigateToDepartmentDetails(clinicId)
            override fun navigateToDoctorProfile() = onNavigateToDoctorProfile()
            override fun navigateToNotifications() = onNavigateToNotifications()
            override fun navigateToMedicalRecords() = onNavigateToMedicalRecords()
            override fun navigateToPrescriptions() = onNavigateToPrescriptions()
            override fun navigateToVaccines() = onNavigateToVaccines()
        }
        ClinicsSearchScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
