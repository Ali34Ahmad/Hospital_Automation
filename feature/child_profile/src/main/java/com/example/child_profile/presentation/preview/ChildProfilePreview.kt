package com.example.child_profile.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.child_profile.presentation.ChildProfileNavigationAction
import com.example.child_profile.presentation.ChildProfileScreen
import com.example.child_profile.presentation.ChildProfileUIAction
import com.example.child_profile.presentation.ChildProfileUIState
import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.util.UiText
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
val refreshedChild =  ChildFullData(
    numberOfGuardians = 3,
    childId = 1,
    firstName = "Sleman",
    lastName = "kzelik",
    fatherFirstName = "Ibrahem",
    fatherLastName = "kzelik",
    motherFirstName = "Aida",
    motherLastName = "Gogo",
    dateOfBirth = "04-09-1972",
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
        var uiState by remember{ mutableStateOf( ChildProfileUIState(
            state = ScreenState.SUCCESS,
            child = fakeChild
        )) }
        ChildProfileScreen(
            uiState =uiState,
            navigationActions = mockActions,
            onAction ={}
        )
    }
}