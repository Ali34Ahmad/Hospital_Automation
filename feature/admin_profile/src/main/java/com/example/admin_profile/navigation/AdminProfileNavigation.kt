package com.example.admin_profile.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.admin_profile.presentation.AdminProfileNavigationUiActions
import com.example.admin_profile.presentation.AdminProfileScreen
import com.example.admin_profile.presentation.AdminProfileViewModel
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
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

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
