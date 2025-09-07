package com.example.child_profile.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.child_profile.navigation.ChildProfileMode
import com.example.child_profile.presentation.ChildProfileNavigationAction
import com.example.child_profile.presentation.ChildProfileScreen
import com.example.child_profile.presentation.ChildProfileUIState
import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import java.time.LocalDate

private val mockActions =
    object : ChildProfileNavigationAction{
    override fun navigateUp() {}

    override fun navigateToAddGuardianScreen(childId: Int) {}

    override fun navigateToEmployeeProfileScreen(employeeId: Int) {}

    override fun navigateToGuardiansScreen(childId: Int){}
    override fun navigateToVaccinationTableScreen(childId: Int) {

    }

    override fun navigateToAppointments(childId: Int,name: String) {
    }

    override fun navigateToMedicalRecords(childId: Int) {
    }

    override fun navigateToPrescriptions(childId: Int) {
    }

        override fun navigateToAppointmentDetails(appointmentId: Int) {

        }
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
    dateOfBirth = LocalDate.now(),
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
    dateOfBirth = LocalDate.of(2003,10,10),
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
                childId = 1,
                state = ScreenState.LOADING,
                childProfileMode = ChildProfileMode.DOCTOR_ACCESS
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
                childId = 1,
                state = ScreenState.ERROR,
                childProfileMode = ChildProfileMode.DOCTOR_ACCESS
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
            childId = 1,
            child = fakeChild,
            state = ScreenState.SUCCESS,
            childProfileMode = ChildProfileMode.DOCTOR_ACCESS

        )) }
        ChildProfileScreen(
            uiState =uiState,
            navigationActions = mockActions,
            onAction ={}
        )
    }
}