package com.example.ui_components.components.bottomBars.custom

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.enums.BottomBarState
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.R
@Composable
fun SendingDataBottomBar(
    text: String ,
    onButtonClick: ()-> Unit,
    state: BottomBarState,
    onSuccess:()->Unit,
    modifier: Modifier = Modifier,
    failureText:String = stringResource(id = R.string.error),
    successContent: @Composable () -> Unit = {},
) {
    LaunchedEffect(state) {
        if(state == BottomBarState.SUCCESS){
            onSuccess()
        }
    }
    //add animations here
    AnimatedContent(
        targetState = state
    ) { state->
        when(state){
            BottomBarState.IDLE -> {
                HospitalAutomationButton(
                    onClick = onButtonClick,
                    text = text,
                    modifier = modifier,
                    isLoading = false,
                    enabled = true,
                    hasError = false
                )
            }
            BottomBarState.LOADING ->{
                HospitalAutomationButton(
                    onClick = onButtonClick,
                    text = text,
                    modifier = modifier,
                    isLoading = true,
                    enabled = false,
                    hasError = false
                )
            }
            BottomBarState.FAILURE ->{
                HospitalAutomationButton(
                    onClick = onButtonClick,
                    text = failureText,
                    modifier = modifier,
                    isLoading = false,
                    enabled = false,
                    hasError = true
                )
            }
            BottomBarState.SUCCESS ->{
                successContent
            }

            BottomBarState.DISABLED ->{
                HospitalAutomationButton(
                    onClick = onButtonClick,
                    text = text,
                    modifier = modifier,
                    isLoading = false,
                    enabled = false,
                    hasError = false
                )
            }
        }
    }
}

@Preview
@Composable
fun SendingDataBottomBarPreview() {
    Hospital_AutomationTheme {
        Scaffold(
            bottomBar = {
                SendingDataBottomBar(
                    onSuccess = {},
                    text = stringResource(R.string.send_data),
                    onButtonClick = {
                    },
                    state = BottomBarState.LOADING,
                    modifier = Modifier.fillMaxWidth().padding(
                        MaterialTheme.spacing.medium16
                    ),
                )
            },
        ) {
            Box(modifier = Modifier.padding(it)){}
        }
    }
}