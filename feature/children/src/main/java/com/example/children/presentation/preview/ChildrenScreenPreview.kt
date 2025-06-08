package com.example.children.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.children.presentation.ChildrenNavigationAction
import com.example.children.presentation.ChildrenScreen
import com.example.children.presentation.ChildrenUIState
import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

private val mockActions = object : ChildrenNavigationAction{
    override fun navigateUp() {}
    override fun navigateToChildProfile(childId: Int) {}
}

private val children = listOf(
    ChildFullData(
        numberOfGuardians = 10,
        childId = 1,
        firstName = "Ali",
        lastName = "Ahmad",
        fatherFirstName = "Shafeek",
        fatherLastName = "Ahmad",
        motherFirstName = "Sara",
        motherLastName = "Sara",
        dateOfBirth = "2002-11-11",
        birthCertificateImgUrl = "",
        gender = "Male",
        employeeId = 0
    ),
    ChildFullData(
        numberOfGuardians = 10,
        childId = 2,
        firstName = "Ali",
        lastName = "Ahmad",
        fatherFirstName = "Shafeek",
        fatherLastName = "Ahmad",
        motherFirstName = "Sara",
        motherLastName = "Sara",
        dateOfBirth = "2002-01-01",
        birthCertificateImgUrl = "",
        gender = "Male",
        employeeId = 0
    ),
    ChildFullData(
        numberOfGuardians = 10,
        childId = 3,
        firstName = "Sara",
        lastName = "Momo",
        fatherFirstName = "Shafeek",
        fatherLastName = "Ahmad",
        motherFirstName = "Sara",
        motherLastName = "Sara",
        dateOfBirth = "2002-01-01",
        birthCertificateImgUrl = "",
        gender = "Female",
        employeeId = 0
    )
)

@DarkAndLightModePreview
@Composable
fun ChildrenScreenLoadingPreview(){
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(
            ChildrenUIState(
                guardianId = 0,
                screenState = ScreenState.LOADING,
            )
        )}
        ChildrenScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildrenScreenSuccessPreview(){
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(
            ChildrenUIState(
                guardianId = 0,
                screenState = ScreenState.SUCCESS,
                userChildren = children
            )
        )}
        ChildrenScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildrenScreenEmptyPreview(){
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(
            ChildrenUIState(
                guardianId = 0,
                screenState = ScreenState.SUCCESS,
            )
        )}
        ChildrenScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
@DarkAndLightModePreview
@Composable
fun ChildrenScreenErrorPreview(){
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(
            ChildrenUIState(
                guardianId = 0,
                screenState = ScreenState.ERROR,
            )
        )}
        ChildrenScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}


