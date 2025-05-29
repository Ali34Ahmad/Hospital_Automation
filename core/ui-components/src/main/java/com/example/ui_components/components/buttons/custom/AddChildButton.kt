package com.example.ui_components.components.buttons.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.model.enums.FetchingDataState
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.R
@Composable
fun AddChildButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fetchingDataState: FetchingDataState = FetchingDataState.READY
) {

    val text = when(fetchingDataState){
        FetchingDataState.READY -> stringResource(R.string.send_data)
        FetchingDataState.DOING_PROCESS -> stringResource(R.string.sending)
        FetchingDataState.ERROR -> stringResource(R.string.error)
        FetchingDataState.Success -> stringResource(R.string.next)
    }

    val isLoading = fetchingDataState == FetchingDataState.DOING_PROCESS
    val hasError = fetchingDataState == FetchingDataState.ERROR

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