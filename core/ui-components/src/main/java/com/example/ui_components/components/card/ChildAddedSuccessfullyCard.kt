package com.example.ui_components.components.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.icon.SystemStateIcon

@Composable
fun ChildAddedSuccessfullyCard(
    title:String,
    description:String,
    onBackToHomeButtonClick:()->Unit,
    onAddGuardianButtonClick:()->Unit,
    modifier: Modifier = Modifier,
) {
    IllustrationCard(
        title = title,
        description = description,
        modifier = modifier,
        illustration = {
            SystemStateIcon(
                iconRes = AppIcons.Outlined.check,
                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
            )
        },
        actionButtonsSection = {
            Row(modifier = Modifier.fillMaxWidth()) {
                HospitalAutomationOutLinedButton(
                    onClick = onBackToHomeButtonClick,
                    text = stringResource(R.string.replace_file),
                    icon = AppIcons.Outlined.file,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.large24))
                HospitalAutomationButton(
                    onClick = onAddGuardianButtonClick,
                    text = stringResource(R.string.next),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    )
}

@DarkAndLightModePreview
@Composable
fun ChildAddedSuccessfullyCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            ChildAddedSuccessfullyCard(
                title = stringResource(R.string.child_added_successfully),
                description = stringResource(R.string.child_added_successfully_description),
                onAddGuardianButtonClick = {},
                onBackToHomeButtonClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}