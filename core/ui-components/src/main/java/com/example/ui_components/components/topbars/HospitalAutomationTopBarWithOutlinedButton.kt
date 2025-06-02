package com.example.ui_components.components.topbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalAutomationTopBarWithOutlinedButton(
    title: String,
    buttonText: String,
    onButtonClick:()-> Unit,
    isButtonEnable: Boolean=true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ){
        TopAppBar(
            title = {
                Text(text = title)
            },
            actions = {
                HospitalAutomationOutLinedButton(
                    text = buttonText,
                    onClick = onButtonClick,
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium16),
                    enabled = isButtonEnable,
                )
            },
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationTopBarWithOutlinedButtonPreview(){
    Hospital_AutomationTheme {
        Surface{
            HospitalAutomationTopBarWithOutlinedButton(
                title = stringResource(R.string.signup),
                buttonText = stringResource(R.string.login),
                onButtonClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(MaterialTheme.spacing.medium16)
                    ,
            )
        }
    }
}