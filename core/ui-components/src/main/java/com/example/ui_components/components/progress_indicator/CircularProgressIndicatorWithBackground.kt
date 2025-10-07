package com.example.ui_components.components.progress_indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing


@Composable
fun CircularProgressIndicatorWithBackground(
    modifier: Modifier = Modifier,
    indicatorSize: Dp= MaterialTheme.sizing.small18,
    indicatorStrokeWidth: Dp= MaterialTheme.sizing.circularProgressIndicatorStrokeSizeSmall,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
    indicatorColor:Color= MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = modifier
            .size(MaterialTheme.sizing.small24)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            strokeWidth = indicatorStrokeWidth,
            strokeCap = StrokeCap.Round,
            color = indicatorColor,
            modifier = Modifier.size(indicatorSize)
        )
    }
}

@DarkAndLightModePreview
@Composable
fun CircularProgressIndicatorWithBackgroundPreview(){
    Hospital_AutomationTheme{
        Surface{
            CircularProgressIndicatorWithBackground(
            )
        }
    }
}