package com.example.employees_search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.employees_search.presentation.EmployeeSearchViewModel
import com.example.employees_search.presentation.EmployeesSearchNavigationActions
import com.example.employees_search.presentation.EmployeesSearchScreen
import com.example.navigation.extesion.switchToTab
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object EmployeesSearchRoute

fun NavController.switchToEmployeesSearch(
    startDestination: Any
){
    switchToTab(
        route = EmployeesSearchRoute,
        startDestination = startDestination
    )
}

fun NavGraphBuilder.employeeSearch(
    onNavigateToEmployeeProfile: (Int)-> Unit
){
    composable<EmployeesSearchRoute> {
        val viewModel = koinViewModel<EmployeeSearchViewModel>()
        val navigationActions = object : EmployeesSearchNavigationActions{
            override fun navigateToEmployeeProfile(employeeId: Int) = onNavigateToEmployeeProfile(employeeId)
        }
        EmployeesSearchScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}
