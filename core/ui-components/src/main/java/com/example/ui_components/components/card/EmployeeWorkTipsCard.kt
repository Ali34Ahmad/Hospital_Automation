package com.example.ui_components.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton

@Composable
fun EmployeeWorkTipsCard(
    title:String,
    description:String,
    onAddChildButtonClick:()->Unit,
    onAddGuardianButtonClick:()->Unit,
    modifier: Modifier = Modifier,
) {
    IllustrationCard(
        title = title,
        description = description,
        modifier = modifier,
        illustration = {
            Image(
                painter = painterResource(R.drawable.ill_hand_lamp),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize),
            )
        },
        actionButtonsSection = {
            Column {
                HospitalAutomationButton(
                    onClick = onAddChildButtonClick,
                    text = stringResource(R.string.add_child),
                    modifier = Modifier.fillMaxWidth()
                )
                HospitalAutomationButton(
                    onClick = onAddGuardianButtonClick,
                    text = stringResource(R.string.add_guardian),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@DarkAndLightModePreview
@Composable
fun WorkTipsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmployeeWorkTipsCard(
                title = stringResource(R.string.work_tips),
                description = stringResource(R.string.work_tips_description),
                modifier = Modifier.fillMaxWidth(),
                onAddChildButtonClick = {},
                onAddGuardianButtonClick = {},
            )
        }
    }
}