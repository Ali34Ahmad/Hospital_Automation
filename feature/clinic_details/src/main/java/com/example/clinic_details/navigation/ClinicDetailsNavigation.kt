package com.example.clinic_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.clinic_details.presentation.ClinicDetailsScreen
import com.example.clinic_details.presentation.ClinicDetailsViewModel
import com.example.clinic_details.presentation.ClinicNavigationAction
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ClinicDetailsRoute(
    val clinicId: Int,
)

fun NavController.navigateToClinicDetails(
    clinicId: Int,
){
    navigateToScreen(
        route = ClinicDetailsRoute(clinicId = clinicId)
    )
}

fun NavGraphBuilder.clinicDetailsScreen(
    onNavigateUp: () -> Unit,
    onNavigateToDoctorProfile: ()-> Unit,
    onNavigateToScheduleScreen : ()->Unit,
    onNavigateToVaccines: ()-> Unit,
){
    composable<ClinicDetailsRoute> {
        val viewModel = koinViewModel<ClinicDetailsViewModel>()
        val navigationActions = object : ClinicNavigationAction{
            override fun navigateUp() = onNavigateUp()
            override fun navigateToDoctorProfile() = onNavigateToDoctorProfile()
            override fun navigateToScheduleScreen() =onNavigateToScheduleScreen()
            override fun navigateToVaccines() = onNavigateToVaccines()
        }
        ClinicDetailsScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}