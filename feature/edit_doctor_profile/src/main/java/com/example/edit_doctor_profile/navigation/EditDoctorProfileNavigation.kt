package com.example.edit_doctor_profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.edit_doctor_profile.presentation.EditDoctorProfileNavigationActions
import com.example.edit_doctor_profile.presentation.EditDoctorProfileScreen
import com.example.edit_doctor_profile.presentation.EditDoctorProfileViewModel
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object EditDoctorProfileRoute

fun NavController.navigateToEditDoctorProfile(){
    navigateToScreen(EditDoctorProfileRoute)
}

fun NavGraphBuilder.editDoctorProfileScreen(
    onNavigateUp: ()-> Unit
){
    composable<EditDoctorProfileRoute> {
        val viewModel = koinViewModel<EditDoctorProfileViewModel>()
        val navigationActions = object : EditDoctorProfileNavigationActions{
            override fun navigateUp() = onNavigateUp()
        }
        EditDoctorProfileScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}