package com.example.ui_components.components.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.hospital_automation.core.components.card.IllustrationCard
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.icon.SystemStateIcon

@Composable
fun VerifiedAccountCard(
    title:String,
    description:String,
    onButtonClick:()->Unit,
    isButtonLoading:Boolean,
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
            HospitalAutomationButton(
                onClick = onButtonClick,
                text = stringResource(R.string.next),
                modifier = Modifier.fillMaxWidth(),
                enabled = !isButtonLoading,
                isLoading = isButtonLoading,
            )
        }
    )
}

@DarkAndLightModePreview
@Composable
fun VerifiedAccountCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            VerifiedAccountCard(
                title = stringResource(R.string.verified),
                onButtonClick={},
                description = stringResource(R.string.email_verified_description),
                modifier = Modifier.fillMaxWidth(),
                isButtonLoading = true
            )
        }
    }
}