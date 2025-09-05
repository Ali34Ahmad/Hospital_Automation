package com.example.admin_profile.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.admin_profile.main.AdminProfileNavigationUiActions
import com.example.admin_profile.main.AdminProfileScreen
import com.example.admin_profile.main.AdminProfileViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class AdminProfileRoute(
    val adminId:Int?,
)

fun NavController.navigateToAdminProfileScreen(adminId: Int?) {
    navigateToScreen(AdminProfileRoute(adminId))
}

fun NavGraphBuilder.adminProfileScreen(
    onNavigateUp:()->Unit,
) {
    composable<AdminProfileRoute> {
        val viewModel = koinViewModel<AdminProfileViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : AdminProfileNavigationUiActions {
            override fun navigateUp() {
                onNavigateUp()
            }
        }

        AdminProfileScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}
