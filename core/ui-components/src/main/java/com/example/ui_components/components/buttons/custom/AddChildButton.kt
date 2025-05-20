package com.example.ui_components.components.buttons.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.model.BottomBarState
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.R
@Composable
fun AddChildButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    bottomBarState: BottomBarState = BottomBarState.READY_TO_SEND
) {

    val text = when(bottomBarState){
        BottomBarState.READY_TO_SEND -> stringResource(R.string.send_data)
        BottomBarState.SENDING_DATA -> stringResource(R.string.sending)
        BottomBarState.ERROR_SENDING_DATA -> stringResource(R.string.error)
        BottomBarState.DATA_SENT_SUCCESSFULLY -> stringResource(R.string.next)
    }

    val isLoading = bottomBarState == BottomBarState.SENDING_DATA
    val hasError = bottomBarState == BottomBarState.ERROR_SENDING_DATA

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