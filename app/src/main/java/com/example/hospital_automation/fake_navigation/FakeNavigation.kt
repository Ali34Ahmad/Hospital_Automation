package com.example.hospital_automation.fake_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.add_child_screen.navigation.addChildScreen
import com.example.add_child_screen.navigation.navigateToAddChild
import com.example.child_profile.navigation.childProfileScreen
import com.example.child_profile.navigation.navigateToChildProfile
import com.example.children.navigation.childrenScreen
import com.example.children.navigation.navigateToChildrenScreen
import com.example.children_search.navigation.SearchType
import com.example.children_search.navigation.childrenSearchScreen
import com.example.children_search.navigation.navigateToChildrenSearch
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.guardian_profile.navigation.guardianProfileScreen
import com.example.guardian_profile.navigation.navigateToGuardianProfile
import com.example.guardians.navigation.guardiansScreen
import com.example.guardians.navigation.navigateToGuardiansScreen
import com.example.guardians_search.navigation.guardianSearchScreen
import com.example.guardians_search.navigation.navigateToGuardiansSearch

@Composable
fun FakeGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = FakeStartDestination
    ){
        fakeStartDestination(
            navigateToAddChild = navController::navigateToGuardiansSearch,
            navigateToAddGuardian = {
                navController.navigateToChildrenSearch(
                    searchType = SearchType.GLOBAL
                )
            }
        )
        //1.
//        guardianSearchScreen(
//            onNavigateUp = navController::navigateUp,
//            onNavigateToGuardianProfile = { guardianId, childId->
//                navController.navigateToGuardianProfile(
//                    guardianId = guardianId,
//                    userProfileMode = when(childId){
//                            null -> UserProfileMode.ADD_CHILD
//                            else -> UserProfileMode.SET_AS_GUARDIAN
//                        }
//                    ,
//                    childId = childId
//                )
//            }
//        )
        //2.
//        guardianProfileScreen(
//            onNavigateUp = navController::navigateUp,
//            onNavigateToChildrenScreen = {guardianId->
//                navController.navigateToChildrenScreen(guardianId)
//            }
//        ) { guardianId ->
//            navController.navigateToAddChild(
//                guardianId
//            )
//        }
        //3.
//        childrenScreen(
//            navigateToChildProfile = navController::navigateToChildProfile,
//            navigateUp = navController::navigateUp
//        )
        //4.
//        addChildScreen(
//            onNavigateUp = navController::navigateUp,
//            onNavigateToAddChildCertificate = { TODO("navigate to add certificate screen") }
//        )
        //5.
//        childProfileScreen(
//            navigateToAddGuardianScreen = navController::navigateToGuardiansScreen,
//            navigateToEmployeeProfileScreen = { TODO("navigate to employee profile") },
//            navigateToGuardianScreen = navController::navigateToGuardiansScreen,
//            navigateUp = navController::navigateUp
//        )
        //6.
//        guardiansScreen(
//            onNavigateUp = navController::navigateUp,
//            onNavigateToGuardianProfile = {guardianId->
//                navController.navigateToGuardianProfile(
//                    guardianId = guardianId,
//                    userProfileMode = UserProfileMode.VIEW_ONLY,
//                    childId = null
//                )
//            }
//        )
        //7.
//        childrenSearchScreen(
//            onNavigateUp = navController::navigateUp,
//            onNavigateToChildDetail = navController::navigateToChildProfile
//        )
    }
}