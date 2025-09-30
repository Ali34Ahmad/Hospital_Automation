package com.example.employee_profile.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.employee_profile.presentation.EmployeeProfileNavigationUiActions
import com.example.employee_profile.presentation.EmployeeProfileScreen
import com.example.employee_profile.presentation.EmployeeProfileViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class EmployeeProfileRoute(
    val employeeProfileAccessType: EmployeeProfileAccessType,
    val employeeId: Int?,
)

fun NavController.navigateToEmployeeProfileScreen(employeeProfileAccessType: EmployeeProfileAccessType, employeeId: Int?) {
    navigateToScreen(EmployeeProfileRoute(employeeProfileAccessType,employeeId))
}

fun NavGraphBuilder.employeeProfileScreen(
    onNavigateToEmploymentHistoryScreen: (employeeId:Int?) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToAddedChildrenScreen: (employeeId:Int?) -> Unit,
) {
    composable<EmployeeProfileRoute> {
        val viewModel = koinViewModel<EmployeeProfileViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val navActions = object : EmployeeProfileNavigationUiActions {
            override fun navigateToAddedChildrenScreen(employeeId:Int?) {
                onNavigateToAddedChildrenScreen(employeeId)
            }

            override fun navigateToEmploymentHistoryScreen(employeeId:Int?) {
                onNavigateToEmploymentHistoryScreen(employeeId)
            }

            override fun navigateUp() {
                onNavigateUp()
            }

            override fun navigateToLoginScreen() {
                onNavigateToLoginScreen()
            }
        }

        EmployeeProfileScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
