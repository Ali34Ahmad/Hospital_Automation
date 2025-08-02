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
    val childId: Int
)

fun NavController.navigateToChildProfile(childId: Int) {
    navigateToScreen(
        route = ChildProfileRoute(childId = childId),
    )
}

fun NavGraphBuilder.childProfileScreen(
    navigateToAddGuardianScreen: (Int) -> Unit,
    navigateToEmployeeProfileScreen: (employeeId: Int) -> Unit,
    navigateToGuardianScreen: (Int) -> Unit,
    navigateUp: () -> Unit
) {
    composable<ChildProfileRoute> {
        val viewModel = koinViewModel<ChildProfileViewModel>()
        val navigationActions = object : ChildProfileNavigationAction {
            override fun navigateUp() = navigateUp()

            override fun navigateToAddGuardianScreen(childId: Int) =
                navigateToAddGuardianScreen(childId)

            override fun navigateToEmployeeProfileScreen(employeeId: Int) =
                navigateToEmployeeProfileScreen(employeeId)

            override fun navigateToGuardiansScreen(childId: Int) = navigateToGuardianScreen(childId)
        }

        ChildProfileScreen(
            viewModel = viewModel,
            navigationActions = navigationActions,
        )
    }
}
