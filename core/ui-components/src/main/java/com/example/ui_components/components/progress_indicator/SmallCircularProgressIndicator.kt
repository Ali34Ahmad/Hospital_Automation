package com.example.ui_components.components.progress_indicator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun SmallCircularProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = MaterialTheme.sizing.circularProgressIndicatorStrokeSizeSmall,
            strokeCap = StrokeCap.Round,
            modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize24)
        )
    }
}