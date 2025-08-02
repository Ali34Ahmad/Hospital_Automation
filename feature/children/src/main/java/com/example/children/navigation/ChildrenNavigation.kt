package com.example.children.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.children.presentation.ChildrenNavigationAction
import com.example.children.presentation.ChildrenScreen
import com.example.children.presentation.ChildrenViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ChildrenRoute(
    val userId: Int,
)
fun NavController.navigateToChildrenScreen(
    userId: Int
){
    navigateToScreen(ChildrenRoute(userId = userId))
}

fun NavGraphBuilder.childrenScreen(
    navigateToChildProfile: (Int) -> Unit,
    navigateUp: ()-> Unit
){
    composable<ChildrenRoute> {
        val viewModel = koinViewModel<ChildrenViewModel>()
        val navigationAction = object : ChildrenNavigationAction{
            override fun navigateUp() = navigateUp()

            override fun navigateToChildProfile(childId: Int) =
                navigateToChildProfile(childId)
        }
        ChildrenScreen(
            viewModel = viewModel,
            navigationActions = navigationAction
        )
    }
}





