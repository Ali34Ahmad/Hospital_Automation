package com.example.doctors.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.doctors.presentation.DoctorSearchScreen
import com.example.doctors.presentation.DoctorsSearchNavigationActions
import com.example.doctors.presentation.DoctorsSearchViewModel
import com.example.navigation.extesion.switchToTab
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Doctors route
 * pass the clinic id with non-null value if you want to browse doctors
 * by a specific clinic
 * or you can pass it null if you want to browse all doctors in the system
 *
 * @property clinicId the id of the clinic you want to browse doctors by
 * @property clinicName the name of the clinic you want to browse doctors by
 * @author Ali Mansoura
 */
@Serializable
data class DoctorSearchRoute(
    val clinicId: Int? = null,
    val clinicName: String = "",
)

fun NavController.switchToDoctorSearchTab(
    startDestination: Any,
    clinicId: Int? = null,
    clinicName: String,
){
    switchToTab(
        route = DoctorSearchRoute(
            clinicId,
            clinicName,
        ),
        startDestination = startDestination
    )
}

fun NavGraphBuilder.doctorsSearch(
    onNavigateToDoctorProfile:(Int)-> Unit
){
    composable<DoctorSearchRoute> {
        val viewModel = koinViewModel<DoctorsSearchViewModel>()
        val navigationActions = object : DoctorsSearchNavigationActions{
            override fun navigateToDoctorProfile(doctorId: Int) = onNavigateToDoctorProfile(doctorId)

            override fun navigateToAdminProfile() {
                TODO("Not yet implemented")
            }

            override fun navigateToVaccines() {
                TODO("Not yet implemented")
            }

            override fun navigateToNotifications() {
                TODO("Not yet implemented")
            }

            override fun navigateToPrescriptions() {
                TODO("Not yet implemented")
            }

            override fun navigateToMedicalRecords() {
                TODO("Not yet implemented")
            }

            override fun navigateToVaccineTable() {
                TODO("Not yet implemented")
            }
        }

        DoctorSearchScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}