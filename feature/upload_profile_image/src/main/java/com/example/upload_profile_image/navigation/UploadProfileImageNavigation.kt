package com.example.upload_profile_image.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.upload_profile_image.main.UploadProfileImageNavigationUiActions
import com.example.upload_profile_image.main.UploadProfileImageScreen
import com.example.upload_profile_image.main.UploadProfileImageViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data object UploadEmployeeProfileImageRoute

fun NavController.navigateToUploadEmployeeProfileImageScreen() {
    navigateToScreen(UploadEmployeeProfileImageRoute)
}

fun NavGraphBuilder.uploadProfileImageScreen(
    onNavigateToHomeScreenScreen: () -> Unit,
) {
    composable<UploadEmployeeProfileImageRoute> {
        val viewModel = koinViewModel<UploadProfileImageViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : UploadProfileImageNavigationUiActions {
            override fun navigateToHomeScreenScreen() {
                onNavigateToHomeScreenScreen()
            }
        }

        UploadProfileImageScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}