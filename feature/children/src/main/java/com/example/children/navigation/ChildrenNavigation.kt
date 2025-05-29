package com.example.children.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.children.presentation.ChildrenScreen
import com.example.children.presentation.ChildrenUIAction
import com.example.children.presentation.ChildrenViewModel
import com.example.model.helper.IdType
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ChildrenRoute(
    val userId: Int,
    val type: IdType,
)
fun NavController.navigateToChildrenScreen(
    userId: Int,idType: IdType
){
    navigate(ChildrenRoute(userId = userId,type = idType)){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.childrenScreen(
    navigateToChildProfile: (Int) -> Unit,
    navigateUp: ()-> Unit
){
    composable<ChildrenRoute> {
        val viewModel = koinViewModel<ChildrenViewModel>()
        ChildrenScreen(
            viewModel = viewModel,
            onAction = { action->
                when(action){
                    is ChildrenUIAction.NavigateToChildProfile -> navigateToChildProfile(action.childId)
                    ChildrenUIAction.NavigateUp -> navigateUp()
                    else -> viewModel.onAction(action)
                }
            },
        )
    }
}





