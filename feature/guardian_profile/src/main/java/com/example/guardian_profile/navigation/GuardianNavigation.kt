package com.example.guardian_profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.guardian_profile.GuardianProfileViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class GuardianProfileRoute(
    val guardianId: Int,
    val childId: Int? = null,
)

fun NavController.navigateToGuardianProfile(guardianId: Int,navOptions: NavOptions){
    navigate<GuardianProfileRoute>(GuardianProfileRoute(guardianId),navOptions)
}

fun NavGraphBuilder.guardianProfileScreen(){
    composable<GuardianProfileRoute> {
        val viewModel = koinViewModel<GuardianProfileViewModel>()
        TODO("Get the Screen here")
    }
}