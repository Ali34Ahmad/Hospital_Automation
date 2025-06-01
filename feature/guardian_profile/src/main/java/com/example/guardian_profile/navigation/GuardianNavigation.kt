package com.example.guardian_profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.guardian_profile.presentation.GuardianProfileActions
import com.example.guardian_profile.presentation.GuardianProfileScreen
import com.example.guardian_profile.presentation.GuardianProfileViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class GuardianProfileRoute(
    val guardianId: Int,
    val childId: Int? = null,
    val userProfileMode: UserProfileMode
)

/**
 * Navigates to the user profile screen.
 *
 * Use the [userProfileMode] parameter to control the behavior of the screen:
 * - [UserProfileMode.VIEW_ONLY]: View the user profile without any action.
 * - [UserProfileMode.ADD_AS_GUARDIAN]: Display the user profile with the option to assign the current user as a guardian for a child.
 * - [UserProfileMode.ADD_CHILD]: Display the user profile with the option to add a child to the current user.
 *
 * If [userProfileMode] is [UserProfileMode.ADD_AS_GUARDIAN], you must provide a non-null [childId].
 *
 * @param guardianId The ID of the current user.
 * @param childId Optional ID of the child to associate with the current user (used only in ADD_AS_GUARDIAN mode).
 * @param userProfileMode Defines how the profile screen should behave depending on the navigation context.
 *
 * @author Ali Mansoura
 */

fun NavController.navigateToGuardianProfile(
    guardianId: Int,navOptions: NavOptions,
    userProfileMode: UserProfileMode,
    childId: Int? = null
){
    navigate<GuardianProfileRoute>(
        GuardianProfileRoute(
            guardianId= guardianId,
            childId = childId,
            userProfileMode = userProfileMode
        ),
        navOptions
    )
}

fun NavGraphBuilder.guardianProfileScreen(
    onNavigateUp: () -> Unit,
    onNavigateToChildrenScreen: (guardianId: Int)-> Unit
){
    composable<GuardianProfileRoute> {
        val viewModel = koinViewModel<GuardianProfileViewModel>()
        GuardianProfileScreen(
            viewModel = viewModel,
            onAction = {action->
                when(action){
                    GuardianProfileActions.NavigateBack -> onNavigateUp()
                    is GuardianProfileActions.NavigateToChildren ->{
                        onNavigateToChildrenScreen(action.guardianId)
                    }
                    is GuardianProfileActions.Open -> Unit
                    is GuardianProfileActions.OpenEmail -> Unit
                    else -> viewModel.onAction(action)
                }
            }
        )
    }
}