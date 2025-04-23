package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun WarningCard(
    @StringRes text: Int,
    @DrawableRes leadingIcon: Int,
    @DrawableRes trailingIcon: Int,
    onTrailingIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.additionalColorScheme.warningContainer,
            contentColor = MaterialTheme.additionalColorScheme.warningLight
        ),
        border = BorderStroke(
            width = MaterialTheme.sizing.small1,
            color = MaterialTheme.additionalColorScheme.warningLight,
        ),
        shape = MaterialTheme.shapes.small
    ){
        Row(
            modifier = Modifier.padding(
                MaterialTheme.spacing.medium16
            ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                modifier = Modifier.size(
                    MaterialTheme.sizing.small18
                )
            )
            Spacer(Modifier.width(MaterialTheme.spacing.extraSmall4))
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = onTrailingIconClick,
                modifier = Modifier.size(
                    MaterialTheme.sizing.small24
                )
            ) {
               Icon(
                   modifier = Modifier.size(
                       MaterialTheme.sizing.small18
                   ),
                   painter = painterResource(trailingIcon),
                   contentDescription = null,
                   tint = MaterialTheme.colorScheme.onBackground
               )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaringCardPreview() {
    Hospital_AutomationTheme {
        WarningCard(
            text = R.string.description_not_added,
            leadingIcon = HospitalAutomationIcons.problem,
            trailingIcon = HospitalAutomationIcons.add,
            onTrailingIconClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}