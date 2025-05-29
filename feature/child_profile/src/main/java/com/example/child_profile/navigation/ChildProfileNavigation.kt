package com.example.child_profile.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.child_profile.presentation.ChildProfileScreen
import com.example.child_profile.presentation.ChildProfileUIAction
import com.example.child_profile.presentation.ChildProfileViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ChildProfileRoute(
    val childId : Int
)

fun NavController.navigateToChildProfile(childId: Int){
    navigateToScreen(
        route = ChildProfileRoute(childId = childId),
    )
}

fun NavGraphBuilder.childProfileScreen(
    navigateToAddGuardianScreen: (Int)-> Unit,
    navigateToAddBirthCertificateScreen: (Int)-> Unit,
    navigateToEmployeeProfileScreen: (Int)-> Unit,
    navigateToGuardianScreen: (Int)-> Unit,
    navigateUp: ()-> Unit
){
    composable<ChildProfileRoute>(
        enterTransition = { fadeIn() + slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
        ) },
        exitTransition = { fadeOut() + slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
        ) },
    ) {
        val viewModel = koinViewModel<ChildProfileViewModel>()
        ChildProfileScreen(
            viewModel = viewModel,
            onAction = { action->
                when(action){
                    is ChildProfileUIAction.NavigateToAddGuardianScreen ->{
                        navigateToAddGuardianScreen(action.childId)
                    }
                    is ChildProfileUIAction.NavigateToBirthCertificateScreen ->{
                        navigateToAddBirthCertificateScreen(action.childId)
                    }
                    is ChildProfileUIAction.NavigateToEmployeeProfileScreen ->{
                        navigateToEmployeeProfileScreen(action.employeeId)
                    }
                    is ChildProfileUIAction.NavigateToGuardiansScreen ->{
                        navigateToGuardianScreen(action.childId)
                    }
                    ChildProfileUIAction.NavigateUp ->{
                        navigateUp()
                    }
                    else -> viewModel.onAction(action)
                }
            }
        )
    }
}
