package com.example.add_child_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.add_child_screen.presentation.AddChildNavigationAction
import com.example.add_child_screen.presentation.AddChildScreen
import com.example.add_child_screen.presentation.AddChildViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class AddChildRoute(
    val guardianId: Int
)

/**
 * Navigates to add child screen after selecting the guardian.
 * @param guardianId: The ID of the selected guardian.
 * @author Ali Mansoura.
 */
fun NavController.navigateToAddChild(guardianId: Int) {
    navigateToScreen(
        route = AddChildRoute(
            guardianId = guardianId
        )
    )
}

fun NavGraphBuilder.addChildScreen(
    onNavigateUp:()-> Unit,
    onNavigateToAddChildCertificate: (childId: Int)-> Unit,
){
    composable<AddChildRoute> {

        val viewModel = koinViewModel<AddChildViewModel>()
        val navigationActions = object : AddChildNavigationAction{
            override fun navigateUp() = onNavigateUp()

            override fun navigateToNextScreen(childId: Int) = onNavigateToAddChildCertificate(childId)
        }
        AddChildScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
