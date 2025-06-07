package com.example.child_profile.presentation.preview

import androidx.compose.runtime.Composable
import com.example.child_profile.presentation.ChildProfileNavigationAction
import com.example.child_profile.presentation.ChildProfileScreen
import com.example.child_profile.presentation.ChildProfileUIState
import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

private val mockActions = object : ChildProfileNavigationAction{
    override fun navigateUp() {}

    override fun navigateToAddGuardianScreen(childId: Int) {}

    override fun navigateToEmployeeProfileScreen(employeeId: Int) {}

    override fun navigateToGuardiansScreen(childId: Int){}
}
val fakeChild =  ChildFullData(
    numberOfGuardians = 3,
    childId = 1,
    firstName = "Ali",
    lastName = "Mansoura",
    fatherFirstName = "Bassam",
    fatherLastName = "Mansoura",
    motherFirstName = "Aida",
    motherLastName = "Ghanem",
    dateOfBirth = "04-09-2002",
    birthCertificateImgUrl = "",
    gender = "male",
    employeeId = 1
)

@DarkAndLightModePreview
@Composable
fun ChildProfileScreenLoadingPreview() {
    Hospital_AutomationTheme {
        ChildProfileScreen(
            uiState = ChildProfileUIState(
                state = ScreenState.LOADING
            ),
            navigationActions = mockActions,
            onAction = {}
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildProfileScreenErrorPreview() {
    Hospital_AutomationTheme {
        ChildProfileScreen(
            uiState = ChildProfileUIState(
                state = ScreenState.ERROR
            ),
            navigationActions = mockActions,
            onAction = {}
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildProfileScreenSuccessPreview() {
    Hospital_AutomationTheme {
        ChildProfileScreen(
            uiState = ChildProfileUIState(
                state = ScreenState.SUCCESS,
                child = fakeChild
            ),
            navigationActions = mockActions,
            onAction = {}
        )
    }
}


