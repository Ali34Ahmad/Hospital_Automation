package com.example.upload_employee_profile_image.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.upload_employee_profile_image.main.UploadEmployeeProfileImageNavigationUiActions
import com.example.upload_employee_profile_image.main.UploadEmployeeProfileImageScreen
import com.example.upload_employee_profile_image.main.UploadEmployeeProfileImageViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data object UploadEmployeeProfileImageRoute

fun NavController.navigateToUploadEmployeeProfileImageScreen() {
    navigateToScreen(UploadEmployeeProfileImageRoute)
}

fun NavGraphBuilder.uploadEmployeeProfileImageScreen(
    onNavigateToHomeScreenScreen: () -> Unit,
) {
    composable<UploadEmployeeProfileImageRoute> {
        val viewModel = koinViewModel<UploadEmployeeProfileImageViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        val navActions = object : UploadEmployeeProfileImageNavigationUiActions {
            override fun navigateToHomeScreenScreen() {
                onNavigateToHomeScreenScreen()
            }
        }

        UploadEmployeeProfileImageScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}