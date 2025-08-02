package com.example.clinic_details.navigation

import androidx.annotation.Keep
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
    val type: ClinicDetailsType,
)

@Keep
@Serializable
enum class ClinicDetailsType{
    JUST_INFO,
    FOR_REGISTERING,
}
fun NavController.navigateToClinicDetailsScreen(
    clinicId: Int,
    type: ClinicDetailsType
){
    navigateToScreen(
        route = ClinicDetailsRoute(clinicId = clinicId, type = type)
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