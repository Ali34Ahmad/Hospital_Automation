package com.example.ui_components.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun HospitalAutomationButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int?=null,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    hasError: Boolean = false
) {

    val disabledContainerColor =
        if (hasError) MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)
        else MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)

    val disabledContentColor =
        if (hasError) MaterialTheme.colorScheme.onErrorContainer
        else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)


    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
        shape = MaterialTheme.additionalShapes.small12,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        ),
        enabled = enabled,
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(isLoading){
                CircularProgressIndicator(
                    strokeWidth = MaterialTheme.sizing.small2,
                    modifier = Modifier.size(MaterialTheme.sizing.small24)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium16))
            }
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription =  null,
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small8))
            }
            Text(
                text,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small8),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationButtonPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.signup)
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationButtonWithIconPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationButton(
            onClick = {},
            icon = AppIcons.Outlined.call,
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.signup)
        )
    }
}

@DarkAndLightModePreview
@Composable
fun HospitalAutomationButtonLoadingPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.loading),
            isLoading = true
        )
    }
}
@DarkAndLightModePreview
@Composable
fun HospitalAutomationButtonDisabledPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.loading),
            enabled = false,
            isLoading = true
        )
    }
}

