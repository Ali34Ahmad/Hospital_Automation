package com.example.ui_components.components.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateDateFormat
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import java.time.LocalDate

@Composable
fun FilterItem(
    title: String,
    subtitle: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
        Row(
            modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.width(MaterialTheme.spacing.extraSmall4))
            Box(
                modifier = Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.extraSmall4
                    )
                    .width(MaterialTheme.sizing.small2)
                    .height(MaterialTheme.sizing.medium44)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(Modifier.width(MaterialTheme.spacing.extraSmall4))
                Text(
                    subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = onClose,
            ) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.sizing.small16),
                    painter = painterResource(AppIcons.Outlined.close),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FilterItemPreview() {
    Hospital_AutomationTheme {
        var isShown by remember { mutableStateOf(true) }
        AnimatedVisibility(
            isShown,
            enter = slideInVertically(initialOffsetY ={-it}),
            exit = slideOutVertically(targetOffsetY = {-it})
        ) {
            FilterItem(
                title = stringResource(R.string.date_filter),
                subtitle = LocalDate.now().toAppropriateDateFormat(),
                onClose = {
                    isShown = false
                }
                ,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}