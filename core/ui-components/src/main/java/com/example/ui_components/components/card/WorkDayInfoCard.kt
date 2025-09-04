package com.example.ui_components.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.constants.icons.AppIcons
import com.example.model.WorkDayItem
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun WorkDayInfoCard(
    workDay: WorkDayItem,
    onEditClick: (WorkDayItem)->Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    containerColor: Color = MaterialTheme.colorScheme.background,
    paddingValues: PaddingValues = PaddingValues(
        vertical = MaterialTheme.spacing.medium16,
        horizontal = MaterialTheme.spacing.small12
    ),
    border: BorderStroke =  BorderStroke(
        width = MaterialTheme.sizing.extraSmall1,
        color = MaterialTheme.additionalColorScheme.onBackgroundVariant
    ),
    titleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    subtitleStyle: TextStyle = MaterialTheme.typography.bodySmall,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    subtitleColor: Color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
) {
    Card(
        modifier = modifier
            .clip(
                shape
            ),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        border = border
    ){
        Row(
            modifier = Modifier.padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //title and subtitle
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall2),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = workDay.dayName,
                    style = titleStyle,
                    color = titleColor,
                )
                Text(
                    text = workDay.timeRange,
                    style = subtitleStyle,
                    color = subtitleColor,
                )
            }
            Spacer(Modifier.weight(1f))
            //edit item : icon with text
            Row(
                modifier = Modifier.clickable{
                    onEditClick(workDay)
                },
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall4),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .size(MaterialTheme.sizing.small16),
                    painter = painterResource(AppIcons.Outlined.edit),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.edit),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun WorkInfoCardPreview(){
    Hospital_AutomationTheme {
        Surface(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium16)
        ) {
            WorkDayInfoCard(
                workDay = WorkDayItem(
                    isChecked = false,
                    enabled = true,
                    dayOfWeek = DayOfWeek.SUNDAY,
                    endTime = LocalTime.of(14,0,0)
                ),
                onEditClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}