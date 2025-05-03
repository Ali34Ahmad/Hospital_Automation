package com.example.ui_components.components.taps

import androidx.annotation.StringRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.model.LabeledBadgeData


@Composable
fun CustomTapsLayout(
    items: List<LabeledBadgeData>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val indicatorColor = MaterialTheme.colorScheme.primaryContainer
    val indicatorCornerRadius = MaterialTheme.spacing.extraSmall2
    val animation by animateFloatAsState(
        targetValue = selectedItemIndex.toFloat(),
        animationSpec = spring(
            stiffness = Spring.StiffnessLow
        )
    )
    Row(
        modifier = modifier
            .padding(
                MaterialTheme.spacing.extraSmall4
            )
            .drawBehind {
                val width = size.width / items.size
                drawRoundRect(
                    color = indicatorColor,
                    topLeft = Offset(width * animation, 0f),
                    size = Size(width, size.height),
                    cornerRadius = CornerRadius(indicatorCornerRadius.toPx())
                )
            }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEachIndexed { index, data ->
            LabeledBadge(
                modifier = Modifier.
                    weight(1f)
                    .clickable{
                        onItemSelected(index)
                    }
                    .padding(
                        vertical = MaterialTheme.spacing.small8
                    ),
                label = data.label,
                badge = data.badge,
                isSelected = index == selectedItemIndex,
            )
        }
    }
}

@Composable
fun LabeledBadge(
    @StringRes label: Int,
    badge: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val badgeColorAnimation  = updateTransition(
        isSelected,
        label = "badge color animation"
    )

    val badgeBackground by badgeColorAnimation.animateColor(
        transitionSpec = {spring(stiffness = Spring.StiffnessLow)}
    ) { target ->
        when(target){
            true->{MaterialTheme.colorScheme.background}
            false ->{MaterialTheme.colorScheme.primaryContainer}
        }
    }
    val textColor by badgeColorAnimation.animateColor(
        transitionSpec = {spring(stiffness = Spring.StiffnessLow)}
    ) { target ->
        when(target){
            true->{MaterialTheme.colorScheme.onPrimaryContainer}
            false ->{MaterialTheme.additionalColorScheme.onBackgroundVariant}
        }
    }

    val badgeColor by badgeColorAnimation.animateColor(
        transitionSpec = {spring(stiffness = Spring.StiffnessLow)}, label = ""
    ) { target ->
        when(target){
            true->{MaterialTheme.colorScheme.onBackground}
            false ->{MaterialTheme.additionalColorScheme.onBackgroundVariant}
        }
    }


    val fontWeight = if(isSelected) FontWeight.SemiBold else FontWeight.Normal

    val badgeText = if(badge > 99) "+99" else badge.toString()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(label),
            color = textColor,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = fontWeight),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(MaterialTheme.spacing.small8))
        Badge(
            modifier = Modifier
                .height(MaterialTheme.sizing.small18)
                .widthIn(MaterialTheme.sizing.small18, MaterialTheme.spacing.large32)
                .clip(CircleShape)
            ,
            containerColor = badgeBackground,
        ){
            Text(
                badgeText,
                style = MaterialTheme.typography.labelSmall,
                color = badgeColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTapPreview() {
    Hospital_AutomationTheme {
        var selectedIndex by remember { mutableIntStateOf(1) }
        CustomTapsLayout(
            items = fakeList,
            modifier = Modifier.fillMaxWidth(),
            selectedItemIndex = selectedIndex,
            onItemSelected = {
                selectedIndex = it
            },
        )
    }
}













val fakeList = listOf(
    LabeledBadgeData(
        label =  R.string.upcoming,
        badge = 12
    ),
    LabeledBadgeData(
        label =  R.string.passed,
        badge = 1
    ),
    LabeledBadgeData(
        label =  R.string.messed,
        badge = 150
    ),
)