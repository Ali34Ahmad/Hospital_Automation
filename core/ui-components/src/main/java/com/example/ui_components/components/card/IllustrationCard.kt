package com.example.ui_components.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.ui_components.R
import com.example.ui_components.components.icon.SystemStateIcon
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun IllustrationCard(
    modifier: Modifier = Modifier,
    illustration: (@Composable () -> Unit)?=null,
    actionButtonsSection: (@Composable () -> Unit)?=null,
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.primary,
    description: String,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                vertical = MaterialTheme.spacing.large24,
                horizontal = MaterialTheme.spacing.medium16
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        illustration?.let {
            illustration()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = titleColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        actionButtonsSection?.let {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))
            actionButtonsSection()
        }
    }
}

@DarkAndLightModePreview
@Composable
fun IllustrationCardSystemStatePreview(){
    Hospital_AutomationTheme{
        Surface{
            IllustrationCard(
                modifier = Modifier.fillMaxWidth(),
                illustration = {
                    SystemStateIcon(iconRes = R.drawable.ic_check,
                        modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize))
                },
                actionButtonsSection = {
                    Button(onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.additionalShapes.small12) {
                        Text(text = stringResource(R.string.next))
                    }
                },
                title = stringResource(R.string.verified),
                description = stringResource(R.string.email_verified_description),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun IllustrationCardPreview(){
    Hospital_AutomationTheme{
        Surface{
            IllustrationCard(
                modifier=Modifier.fillMaxWidth(),
                illustration = {
                    Image(painter = painterResource(R.drawable.ill_permission),
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize))
                },
                title = stringResource(R.string.permission_required),
                description = stringResource(R.string.permission_required_description),
            )
        }
    }
}

