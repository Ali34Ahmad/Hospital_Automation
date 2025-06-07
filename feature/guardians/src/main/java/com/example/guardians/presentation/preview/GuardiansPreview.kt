package com.example.guardians.presentation.preview

import androidx.compose.runtime.Composable
import com.example.guardians.presentation.GuardianNavigationAction
import com.example.guardians.presentation.GuardiansScreen
import com.example.guardians.presentation.GuardiansUIState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme

internal val fakeGuardians = listOf(
    GuardianData(
        id = 0,
        img = null,
        fullName ="Ali Ahmad"
    ), GuardianData(
        id = 1,
        img = null,
        fullName ="Salma Ahmad"
    ), GuardianData(
        id = 2,
        img = null,
        fullName ="Melad Ahmad"
    ),
)
@DarkAndLightModePreview
@Composable
fun GuardiansSuccessPreview(){
    Hospital_AutomationTheme {
        GuardiansScreen(
            uiState = GuardiansUIState(
                childId = 0,
                fakeGuardians,
                state = ScreenState.SUCCESS
            ),
            onAction = {},
            navigationAction = mockNavigationAction
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardiansEmptyPreview(){
    Hospital_AutomationTheme {
        GuardiansScreen(
            uiState = GuardiansUIState(
                childId = 0,
                state = ScreenState.SUCCESS
            ),
            onAction = {},
            navigationAction = mockNavigationAction
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardiansLoadingPreview(){
    Hospital_AutomationTheme {
        GuardiansScreen(
            uiState = GuardiansUIState(
                childId = 0,
                state = ScreenState.LOADING
            ),
            onAction = {},
            navigationAction = mockNavigationAction
        )
    }
}
@DarkAndLightModePreview
@Composable
fun GuardiansErrorPreview(){
    Hospital_AutomationTheme {
        GuardiansScreen(
            uiState = GuardiansUIState(
                childId = 0,
                state = ScreenState.ERROR
            ),
            navigationAction = mockNavigationAction,
            onAction = {}
        )
    }
}
val mockNavigationAction = object : GuardianNavigationAction{
    override fun navigateUp() {}
    override fun navigateToGuardianProfile(guardianId: Int) {}
}