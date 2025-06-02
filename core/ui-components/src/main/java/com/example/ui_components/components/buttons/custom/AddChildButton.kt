package com.example.ui_components.components.buttons.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.model.enums.BottomBarState
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.R
@Composable
fun AddChildButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    bottomBarState: BottomBarState = BottomBarState.IDLE
) {

    val text = when(bottomBarState){

        BottomBarState.IDLE  -> stringResource(R.string.send_data)
        BottomBarState.LOADING -> stringResource(R.string.sending)
        BottomBarState.FAILURE-> stringResource(R.string.error)
        BottomBarState.SUCCESS  -> stringResource(R.string.next)
    }

    val isLoading = bottomBarState == BottomBarState.LOADING
    val hasError = bottomBarState == BottomBarState.FAILURE

    val enabled = !isLoading && !hasError
    HospitalAutomationButton(
        onClick = onClick,
        text = text,
        modifier = modifier,
        isLoading = isLoading,
        enabled = enabled,
        hasError = hasError
    )
}