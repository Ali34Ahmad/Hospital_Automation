package com.example.hospital_automation.fake_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.children.navigation.ChildrenRoute
import com.example.children.navigation.childrenScreen
import com.example.model.helper.IdType

@Composable
fun FakeGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = ChildrenRoute(
            userId = 66,
            type = IdType.EMPLOYEE
        )
    ){
        childrenScreen(
            navigateUp = {
                navController.navigateUp()
            },
            navigateToChildProfile = { childId ->
                navController.navigateToChildProfile(
                    childId = childId
                )
            }
        )
        childProfileScreen(
            navigateToAddGuardianScreen = {

            },
            navigateToAddBirthCertificateScreen = {

            },
            navigateToEmployeeProfileScreen = {

            },
            navigateToGuardianScreen = {

            },
            navigateUp = {navController.navigateUp()}
        )
    }
}