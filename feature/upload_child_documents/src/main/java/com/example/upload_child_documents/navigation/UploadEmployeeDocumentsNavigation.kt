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
    onNavigateToSearchGuardiansScreen: (childId: Int?) -> Unit,
    onNavigateToHomeScreen: () -> Unit,
) {
    composable<UploadChildDocumentsRoute> {
        val viewModel =
            koinViewModel<UploadChildDocumentsViewModel>()
        val uiState =
            viewModel.uiState.collectAsState()

        val navActions = object : UploadChildDocumentsNavigationUiActions {
            override fun navigateToSearchGuardiansScreen() {
                onNavigateToSearchGuardiansScreen(uiState.value.childId)
            }

            override fun navigateToHomeScreen() {
                onNavigateToHomeScreen()
            }
        }

        UploadChildDocumentsScreen(
            uiState = uiState.value,
            uiActions = viewModel.getUiActions(navActions)
        )
    }
}