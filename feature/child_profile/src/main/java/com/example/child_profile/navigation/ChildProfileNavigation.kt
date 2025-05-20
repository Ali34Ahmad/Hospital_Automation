package com.example.child_profile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.child_profile.ChildProfileScreen
import com.example.child_profile.ChildProfileViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ChildProfileRoute(
    val childId : Int
)

fun NavController.navigateToChildProfile(childId: Int){
    navigate(route = ChildProfileRoute(childId = childId)){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.childProfileScreen(){
    composable<ChildProfileRoute> {
        val viewModel = koinViewModel<ChildProfileViewModel>()
        ChildProfileScreen(
            viewModel = viewModel,
            onAction = viewModel::onAction
        )
    }
}
