package com.example.guardians_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.guardians_search.GuardiansSearchScreen
import com.example.guardians_search.GuardiansSearchViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object GuardiansSearchRoute

fun NavController.navigateToGuardiansSearch(){
    navigate(route = GuardiansSearchRoute){
        launchSingleTop =true
    }
}

fun NavGraphBuilder.guardianSearchScreen(){
    composable<GuardiansSearchRoute>{
        val viewModel = koinViewModel<GuardiansSearchViewModel>()
        GuardiansSearchScreen(
            onAction = viewModel::onAction,
            viewModel = viewModel
        )
    }
}
