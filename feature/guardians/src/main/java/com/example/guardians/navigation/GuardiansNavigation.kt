package com.example.guardians.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.guardians.presentation.GuardianNavigationAction
import com.example.guardians.presentation.GuardiansScreen
import com.example.guardians.presentation.GuardiansViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class GuardiansRoute(
    val childId: Int
)

/**
 * Navigates to Guardian screen that is responsible for showing the guardians of a given child
 * by passing the [childId]
 * @param childId: The id of the child who you want to ask for his guardians.
 * @author Ali Mansoura.
 */
fun NavController.navigateToGuardiansScreen(
    childId: Int
){
    navigateToScreen(GuardiansRoute(childId=childId))
}


fun NavGraphBuilder.guardiansScreen(
    onNavigateUp: () -> Unit,
    onNavigateToGuardianProfile: (guardianId: Int)-> Unit
){
    composable<GuardiansRoute> {
        val viewModel = koinViewModel<GuardiansViewModel>()

        val navigationAction = object : GuardianNavigationAction{
            override fun navigateUp() = onNavigateUp()

            override fun navigateToGuardianProfile(guardianId: Int) = onNavigateToGuardianProfile(guardianId)

        }

        GuardiansScreen(
            viewModel = viewModel,
            navigationAction = navigationAction
        )
    }
}