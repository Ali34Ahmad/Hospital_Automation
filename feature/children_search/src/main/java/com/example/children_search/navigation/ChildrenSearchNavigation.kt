package com.example.children_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.children_search.presentation.ChildrenSearchScreen
import com.example.children_search.presentation.ChildrenSearchViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object ChildrenSearch

fun NavController.navigateToChildrenSearch(){
    navigateToScreen(
        ChildrenSearch
    )
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
