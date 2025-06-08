package com.example.add_child_screen.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.add_child_screen.presentation.AddChildNavigationAction
import com.example.add_child_screen.presentation.AddChildScreen
import com.example.add_child_screen.presentation.AddChildUIState
import com.example.model.enums.BottomBarState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.utility.validation.validator.TextValidator

private val mockActions = object : AddChildNavigationAction{
    override fun navigateUp() {}
    override fun navigateToNextScreen(childId: Int) {}
}
@DarkAndLightModePreview
@Composable
fun AddChildScreenPreview() {
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(AddChildUIState()) }
        AddChildScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
@DarkAndLightModePreview
@Composable
fun AddChildScreenSuccessPreview() {
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(AddChildUIState(
            sendingDataButtonState = BottomBarState.SUCCESS,
            isValid = true,
            isSendingDataButtonVisible = false
        )) }
        AddChildScreen(
            uiState = uiState,
            onAction = {},
            navigationActions = mockActions
        )
    }
}
