package com.example.child_profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.child_profile.presentation.ChildProfileNavigationAction
import com.example.child_profile.presentation.ChildProfileScreen
import com.example.child_profile.presentation.ChildProfileViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ChildProfileRoute(
    val childId: Int,
    val hasAdminAccess : Boolean
)

fun NavController.navigateToChildProfile(
    childId: Int,
    hasAdminAccess: Boolean
) {
    navigateToScreen(
        route = ChildProfileRoute(
            childId = childId,
            hasAdminAccess = hasAdminAccess
        ),
    )
}

fun NavGraphBuilder.childProfileScreen(
    navigateToAddGuardianScreen: (Int) -> Unit,
    navigateToEmployeeProfileScreen: (employeeId: Int) -> Unit,
    navigateToGuardiansScreen: (Int) -> Unit,
    navigateUp: () -> Unit,
    onNavigateToVaccinationTable: (Int)-> Unit,
    onNavigateToAppointments: (Int,String)-> Unit,
    onNavigateToPrescriptions: (Int)-> Unit,
    onNavigateToMedicalRecords: (Int)-> Unit,
    onNavigateToAppointmentDetails: (Int) -> Unit
) {
    composable<ChildProfileRoute> {
        val viewModel = koinViewModel<ChildProfileViewModel>()
        val navigationActions = object : ChildProfileNavigationAction {
            override fun navigateUp() = navigateUp()

            override fun navigateToAppointmentDetails(appointmentId: Int) =
                onNavigateToAppointmentDetails(appointmentId)

            override fun navigateToAddGuardianScreen(childId: Int) =
                navigateToAddGuardianScreen(childId)

            override fun navigateToEmployeeProfileScreen(employeeId: Int) =
                navigateToEmployeeProfileScreen(employeeId)

            override fun navigateToGuardiansScreen(childId: Int) =
                navigateToGuardiansScreen(childId)

            override fun navigateToVaccinationTableScreen(childId: Int) =
                onNavigateToVaccinationTable(childId)

            override fun navigateToAppointments(childId: Int,name: String) =
                onNavigateToAppointments(childId,name)

            override fun navigateToMedicalRecords(childId: Int) =
                onNavigateToMedicalRecords(childId)

            override fun navigateToPrescriptions(childId: Int) =
                onNavigateToPrescriptions(childId)
        }

        ChildProfileScreen(
            viewModel = viewModel,
            navigationActions = navigationActions,
        )
    }
}