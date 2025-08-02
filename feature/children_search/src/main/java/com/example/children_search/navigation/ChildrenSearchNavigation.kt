package com.example.children_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.children_search.presentation.ChildrenSearchNavigationActions
import com.example.children_search.presentation.ChildrenSearchScreen
import com.example.children_search.presentation.ChildrenSearchViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class ChildrenSearchRoute(
    val searchType: SearchType
)

/**
 * Navigates to the search for children screen
 * you can customize [searchType] to be global at the system level or just
 * for the current employee.
 *
 * @param searchType : The type of the search either [SearchType.GLOBAL] or [SearchType.EMPLOYEE]
 * @see SearchType
 * @author Ali Mansoura.
 */
fun NavController.navigateToChildrenSearch(searchType: SearchType){
    navigateToScreen(
        ChildrenSearchRoute(
            searchType = searchType
        )
    )
}

fun NavGraphBuilder.childrenSearchScreen(
    onNavigateUp: () -> Unit,
    onNavigateToChildDetail: (childId: Int)-> Unit,
){
    composable<ChildrenSearchRoute> {
        val viewModel = koinViewModel<ChildrenSearchViewModel>()
        val navigationActions = object : ChildrenSearchNavigationActions{
            override fun navigateUp() = onNavigateUp()
            override fun navigateToChildProfile(childId: Int) = onNavigateToChildDetail(childId)
        }
        ChildrenSearchScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
