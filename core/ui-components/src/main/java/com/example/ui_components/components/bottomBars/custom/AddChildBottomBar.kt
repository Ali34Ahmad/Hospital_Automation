package com.example.ui_components.components.bottomBars.custom

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.model.enums.BottomBarState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun AddChildBottomBar(
    onNavigateToNextScreen:()-> Unit,
    onSendingData: () -> Unit,
    modifier: Modifier = Modifier,
    bottomBarState: BottomBarState = BottomBarState.IDLE,
    scope: CoroutineScope = rememberCoroutineScope()
) {

}

@DarkAndLightModePreview
@Composable
fun AddChildBottomBarPreview(){
    Hospital_AutomationTheme {
        Surface {
            var state by remember { mutableStateOf(BottomBarState.FAILURE) }
            AddChildBottomBar(
                bottomBarState = state,
                onNavigateToNextScreen = {

                },
                onSendingData = {

                }
            )
        }
    }
}


















