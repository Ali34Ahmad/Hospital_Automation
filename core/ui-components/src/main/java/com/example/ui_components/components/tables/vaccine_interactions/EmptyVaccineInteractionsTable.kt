package com.example.ui_components.components.tables.vaccine_interactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.example.constants.icons.AppIcons
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton

@Composable
fun EmptyVaccineInteractionsTable(
    modifier: Modifier = Modifier,
    onAddInteractionButtonClick: () -> Unit,
) {
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(AppIcons.Outlined.interactions),
            contentDescription = null,
            modifier = Modifier.size(
                MaterialTheme.sizing.medium64
            ),
            tint = MaterialTheme.colorScheme.secondary,
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
        Text(
            text = stringResource(R.string.no_interaction_added_yet),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(MaterialTheme.spacing.small8))
        Text(
            text = stringResource(R.string.interactions_will_be_displayed_here_in_structured_table),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
        HospitalAutomationOutLinedButton(
            onClick = onAddInteractionButtonClick,
            text = stringResource(R.string.add_interaction),
            modifier = Modifier.fillMaxWidth(),
        )

    }
}

