package com.example.upload_child_documents.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.extesion.navigateToScreen
import com.example.upload_child_documents.presentation.UploadChildDocumentsNavigationUiActions
import com.example.upload_child_documents.presentation.UploadChildDocumentsScreen
import com.example.upload_child_documents.presentation.UploadChildDocumentsViewModel
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
            viewModel.uiState.collectAsStateWithLifecycle()

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