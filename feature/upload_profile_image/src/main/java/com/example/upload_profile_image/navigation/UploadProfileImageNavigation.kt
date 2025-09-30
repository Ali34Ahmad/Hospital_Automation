package com.example.upload_profile_image.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.upload_profile_image.presentation.UploadProfileImageNavigationUiActions
import com.example.upload_profile_image.presentation.UploadProfileImageScreen
import com.example.upload_profile_image.presentation.UploadProfileImageViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data object UploadProfileImageRoute

fun NavController.navigateToUploadProfileImageScreen() {
    navigateToScreen(UploadProfileImageRoute)
}

fun NavGraphBuilder.uploadProfileImageScreen(
    onNavigateToHomeScreenScreen: () -> Unit,
) {
    composable<UploadProfileImageRoute> {
        val viewModel = koinViewModel<UploadProfileImageViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

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