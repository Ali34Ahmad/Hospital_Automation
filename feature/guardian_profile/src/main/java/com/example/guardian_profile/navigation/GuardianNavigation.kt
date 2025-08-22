package com.example.guardian_profile.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.guardian_profile.presentation.GuardianProfileNavigationAction
import com.example.guardian_profile.presentation.GuardianProfileScreen
import com.example.guardian_profile.presentation.GuardianProfileViewModel
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.navigation.extesion.navigateToScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class GuardianProfileRoute(
    val guardianId: Int,
    val childId: Int? = null,
    val userProfileMode: UserProfileMode
)

/**
 * Navigates to the user profile screen.
 *
 * Use the [userProfileMode] parameter to control the behavior of the screen:
 * - [UserProfileMode.VIEW_ONLY]: View the user profile without any action.
 * - [UserProfileMode.SET_AS_GUARDIAN]: Display the user profile with the option to assign the current user as a guardian for a child.
 * - [UserProfileMode.ADD_CHILD]: Display the user profile with the option to add a child to the current user.
 * - [UserProfileMode.ONLY_COMMUNICATION_INFO] : Display the user profile without any private info.
 * If [userProfileMode] is [UserProfileMode.SET_AS_GUARDIAN], you must provide a non-null [childId].
 *
 * @param guardianId The ID of the current user.
 * @param childId Optional ID of the child to associate with the current user (used only in SET_AS_GUARDIAN mode).
 * @param userProfileMode Defines how the profile screen should behave depending on the navigation context.
 *
 * @author Ali Mansoura
 */

fun NavController.navigateToGuardianProfile(
    guardianId: Int,
    userProfileMode: UserProfileMode,
    childId: Int? = null
){
    navigateToScreen(
        GuardianProfileRoute(
            guardianId= guardianId,
            childId = childId,
            userProfileMode = userProfileMode
        )
    )
}

fun NavGraphBuilder.guardianProfileScreen(
    onNavigateUp: () -> Unit,
    onNavigateToChildrenScreen: (guardianId: Int)-> Unit,
    onNavigateToAddChildScreen: (guardianId: Int)-> Unit,
    onNavigateToAppointments: (guardianId: Int)-> Unit,
    onNavigateToPrescriptions: (guardianId: Int)-> Unit,
    onNavigateToMedicalRecord: (guardianId: Int)-> Unit,

){
    composable<GuardianProfileRoute> {
        val viewModel = koinViewModel<GuardianProfileViewModel>()
        val context = LocalContext.current
        val navigationActions = object : GuardianProfileNavigationAction{
            override fun navigateUp() = onNavigateUp()

            override fun openEmail(email: String) = context.navigateToEmailApp(email)

            override fun openContacts(phone: String) = context.navigateToCallApp(phone)

            override fun navigateToAddChild(guardianId: Int) = onNavigateToAddChildScreen(guardianId)

            override fun navigateToChildren(guardianId: Int) = onNavigateToChildrenScreen(guardianId)
            override fun navigateToAppointments(guardianId: Int) = onNavigateToAppointments(guardianId)

            override fun navigateToPrescriptions(guardianId: Int) = onNavigateToPrescriptions(guardianId)

            override fun navigateToMedicalRecord(guardianId: Int) = onNavigateToMedicalRecord(guardianId)
        }
        GuardianProfileScreen(
            viewModel = viewModel,
            navigationActions = navigationActions
        )
    }
}