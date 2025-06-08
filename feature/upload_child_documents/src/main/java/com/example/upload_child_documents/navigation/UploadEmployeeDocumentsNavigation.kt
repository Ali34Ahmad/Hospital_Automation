package com.example.upload_child_documents.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.upload_child_documents.main.UploadChildDocumentsNavigationUiActions
import com.example.upload_child_documents.main.UploadChildDocumentsScreen
import com.example.upload_child_documents.main.UploadChildDocumentsViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
data class UploadChildDocumentsRoute(
    val id: Int?
)

fun NavController.navigateToUploadChildDocumentsScreen(id: Int?) {
    navigateToScreen(UploadChildDocumentsRoute(id))
}

fun NavGraphBuilder.uploadChildDocumentsScreen(
    onNavigateToAddResidentialAddressScreen: () -> Unit,
) {
    composable<UploadChildDocumentsRoute> {
        val uploadChildDocumentsViewModel =
            koinViewModel<UploadChildDocumentsViewModel>()
        val uploadChildDocumentsUiState =
            uploadChildDocumentsViewModel.uiState.collectAsState()

        val navActions = object : UploadChildDocumentsNavigationUiActions {
            override fun navigateToAddResidentialAddressScreen() {
                onNavigateToAddResidentialAddressScreen()
            }
        }

        UploadChildDocumentsScreen(
            uiState = uploadChildDocumentsUiState.value,
            uiActions = uploadChildDocumentsViewModel.getUiActions(navActions)
        )
    }
}