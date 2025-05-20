package com.example.children_search.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.children_search.ChildrenSearchScreen
import com.example.children_search.ChildrenSearchViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object ChildrenSearch

fun NavController.navigateToChildrenSearch(){
    navigate(route = ChildrenSearch){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.childrenSearchScreen(){
    composable<ChildrenSearch> {
        val viewModel = koinViewModel<ChildrenSearchViewModel>()

        ChildrenSearchScreen(
            viewModel = viewModel,
            onAction = viewModel::onAction,
        )
    }
}
