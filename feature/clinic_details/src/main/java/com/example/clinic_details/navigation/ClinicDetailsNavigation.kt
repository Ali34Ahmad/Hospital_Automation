
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
    ADMIN_ACCESS,
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
    onNavigateToDoctorProfile: (id: Int)-> Unit,
    onNavigateToScheduleScreen : ()->Unit,
    onNavigateToVaccines: ()-> Unit,
    onNavigateToAllDoctors: (Int, String)-> Unit,
    onNavigateToAllAppointments: ()-> Unit,
    onNavigateToMedicalRecords: ()-> Unit,
    onNavigateToContractHistory: ()-> Unit,
    onNavigateToPrescriptions: ()-> Unit,
    onNavigateToEditClinic: ()-> Unit
){
    composable<ClinicDetailsRoute> {
        val viewModel = koinViewModel<ClinicDetailsViewModel>()
        val navigationActions = object : ClinicNavigationAction{
            override fun navigateUp() = onNavigateUp()
            override fun navigateToDoctorProfile(id: Int) = onNavigateToDoctorProfile(id)
            override fun navigateToScheduleScreen() =onNavigateToScheduleScreen()
            override fun navigateToVaccines() = onNavigateToVaccines()
            override fun navigateToAllDoctors(
                clinicId:Int,
                clinicName: String,
            )=onNavigateToAllDoctors(
                clinicId,
                clinicName
            )

            override fun navigateToAllAppointments() = onNavigateToAllAppointments()
            override fun navigateToPrescriptions() = onNavigateToPrescriptions()

            override fun navigateToMedicalRecords() = onNavigateToMedicalRecords()

            override fun navigateToContractHistory() = onNavigateToContractHistory()
            override fun navigateToEditClinic() = onNavigateToEditClinic()
        }
        ClinicDetailsScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}