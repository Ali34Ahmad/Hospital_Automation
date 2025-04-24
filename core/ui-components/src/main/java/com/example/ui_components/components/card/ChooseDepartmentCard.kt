package com.example.ui_components.components.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.hospital_automation.core.components.card.IllustrationCard
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton

@Composable
fun ChooseDepartmentCard(
    onStartButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IllustrationCard(
        title = stringResource(R.string.choose_department),
        description = stringResource(R.string.choose_department_description),
        modifier = modifier,
        actionButtonsSection = {
            HospitalAutomationButton(
                text = stringResource(R.string.start),
                onClick = onStartButtonClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    )
}

@DarkAndLightModePreview
@Composable
fun ChooseDepartmentCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChooseDepartmentCard(
                onStartButtonClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}