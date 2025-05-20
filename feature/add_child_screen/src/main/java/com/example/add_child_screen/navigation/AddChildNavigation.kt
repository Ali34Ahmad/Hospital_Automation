package com.example.add_child_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.add_child_screen.AddChildScreen
import com.example.add_child_screen.AddChildViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class AddChildRoute(
    val guardianId: Int
)

fun NavController.navigateToAddChild(guardianId: Int) {
    navigate(route = AddChildRoute(guardianId = guardianId)){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.addChildScreen(){
    composable<AddChildRoute> {
        val viewModel = koinViewModel<AddChildViewModel>()
        AddChildScreen(
            viewModel = viewModel,
            onAction = viewModel::onAction
        )
    }
}
