package com.example.guardians_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.guardians_search.presentation.GuardiansSearchActions
import com.example.guardians_search.presentation.GuardiansSearchScreen
import com.example.guardians_search.presentation.GuardiansSearchViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class GuardiansSearchRoute(
    val childId: Int? = null
)

/**
 * Navigates to the Guardians Search screen.
 *
 * If [childId] is null, the screen will allow searching for any guardian.
 * If [childId] is provided, the screen will be used to search and add a guardian for the specified child.
 *
 * @param childId Optional ID of the child to add a guardian for. If null, a general search is performed.
 * @author Ali Mansoura
 */
fun NavController.navigateToGuardiansSearch(
    childId:Int? = null
) {
    navigateToScreen(route = GuardiansSearchRoute(childId))
}

fun NavGraphBuilder.guardianSearchScreen(
    onNavigateUp: ()-> Unit,
    onNavigateToGuardiansSearchRoute: (guardianId: Int,childId: Int?)-> Unit
){
    composable<GuardiansSearchRoute>{
        val viewModel = koinViewModel<GuardiansSearchViewModel>()
        GuardiansSearchScreen(
            viewModel = viewModel,
            onAction = {action->
                when(action){
                    is GuardiansSearchActions.NavigateToGuardianDetails -> {
                        onNavigateToGuardiansSearchRoute(action.guardianId,action.childId)
                    }
                    GuardiansSearchActions.OnNavigateBack -> onNavigateUp()
                    else -> viewModel.onAction(action)
                }
            }
        )
    }
}
