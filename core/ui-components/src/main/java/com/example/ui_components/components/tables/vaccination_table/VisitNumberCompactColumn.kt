package com.example.ui_components.components.tables.vaccination_table

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.constants.icons.AppIcons
import com.example.ext.toOrdinalString
import com.example.ui.theme.additionalTypography
import com.example.ui.theme.spacing
import com.example.ui_components.components.icon.IconWithBackground

@Composable
fun VisitNumberCompactColumn(
    visitNumber: Int,
    onAddVaccineToVisit: (visitNumber:Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Box {
            Text(
                visitNumber.toOrdinalString(),
                style = MaterialTheme.additionalTypography.labelSmallest,
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.extraSmall2,
                    top = MaterialTheme.spacing.extraSmall2
                )
            )
            IconButton(
                onClick = { onAddVaccineToVisit(visitNumber) },
            ) {
                IconWithBackground(
                    iconRes = AppIcons.Outlined.add,
                    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}
