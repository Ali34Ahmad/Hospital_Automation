package com.example.ui_components.components.bottomBars.custom

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.model.enums.FetchingDataState
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.R
@Composable
fun GuardianProfileBottomBar(
    onButtonClick: ()-> Unit,
    state: FetchingDataState,
    modifier: Modifier = Modifier,
    errorMessage: String?,
) {
    when(state){
        FetchingDataState.READY -> {
            HospitalAutomationButton(
                onClick = onButtonClick,
                text = stringResource(id = R.string.set_as_guardian),
                modifier = modifier,
                isLoading = false,
                enabled = true,
                hasError = false
            )
        }
        FetchingDataState.DOING_PROCESS ->{
            HospitalAutomationButton(
                onClick = onButtonClick,
                text = stringResource(id = R.string.wait),
                modifier = modifier,
                isLoading = true,
                enabled = false,
                hasError = false
            )
        }
        FetchingDataState.ERROR ->{
            HospitalAutomationButton(
                onClick = onButtonClick,
                text = stringResource(id = R.string.error),
                modifier = modifier,
                isLoading = false,
                enabled = false,
                hasError = true
            )
            Toast.makeText(
                LocalContext.current,
                errorMessage?:stringResource(R.string.something_went_wrong),
                Toast.LENGTH_SHORT
            ).show()
        }
        FetchingDataState.Success ->{
            Toast.makeText(
                LocalContext.current,
                 stringResource(R.string.success),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}