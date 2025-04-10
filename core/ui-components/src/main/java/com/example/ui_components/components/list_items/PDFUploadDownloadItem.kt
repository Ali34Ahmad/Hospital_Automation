package com.example.ui_components.components.list_items

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.theme.Hospital_AutomationTheme
import com.example.ui_components.theme.onBackgroundVariantLight
import com.example.ui_components.theme.primaryText
import kotlin.math.roundToInt


@Composable
fun PDFUploadDownloadItem(
    name: String,
    currentSize: Float,
    progress: Int,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onOpen: () -> Unit,
    state:FileUploadingState,
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.pdf,
) {

    val infiniteTransition = rememberInfiniteTransition("dots")
    val dotsAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 3.0f,
        animationSpec = infiniteRepeatable(tween(3000), repeatMode = RepeatMode.Restart),
        label = ""
    )
    val dots = ".".repeat(dotsAnimation.value.roundToInt())

    Row(
        modifier = modifier
            .clickable{
                when(state){
                    FileUploadingState.UPLOADING -> onPause()
                    FileUploadingState.PAUSED -> onResume()
                    FileUploadingState.DOWNLOADING -> onPause()
                    FileUploadingState.COMPLETE -> onOpen()
                }
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Image(
            modifier = Modifier.width(42.dp),
            painter = painterResource(image),
            contentDescription = null
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.width(120.dp),
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = "$currentSize MB",
                    style = MaterialTheme.typography.bodySmall,
                    color = onBackgroundVariantLight
                )
                val text = when(state){
                    FileUploadingState.UPLOADING -> stringResource(R.string.uploading) + dots
                    FileUploadingState.PAUSED -> stringResource(R.string.paused)
                    FileUploadingState.DOWNLOADING -> stringResource(R.string.downloading) + dots
                    FileUploadingState.COMPLETE -> stringResource( R.string.open)
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                    color = primaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Visible
                )
            }
            // Linear progress indicator with percentage
            //when the state of the pdf file is not complete show the linear progress indicator
            if(state != FileUploadingState.COMPLETE){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val color = if(state == FileUploadingState.PAUSED) Color(0xffBCC7D2) else primaryText
                    LinearProgressIndicator(
                        progress = {
                            progress.toFloat().div(100)
                        },
                        modifier = Modifier.weight(1f),
                        strokeCap = StrokeCap.Round,
                        color = color,
                    )
                    Text(
                        text = "$progress%",
                        style = MaterialTheme.typography.bodySmall,
                        color = onBackgroundVariantLight
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PDFDownloaderDownloadingPreview() {
    Hospital_AutomationTheme {
        PDFUploadDownloadItem(
            modifier = Modifier.width(348.dp),
            name = "Cam-Scanner-152314",
            currentSize = 1.7f,
            progress = 70,
            state = FileUploadingState.DOWNLOADING,
            onPause = {},
            onOpen = {},
            onResume = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PDFDownloaderUploadingPreview() {
    Hospital_AutomationTheme {
        PDFUploadDownloadItem(
            modifier = Modifier.width(348.dp),
            name = "Cam-Scanner-152314",
            currentSize = 1.7f,
            progress = 70,
            state = FileUploadingState.UPLOADING,
            onPause = {},
            onOpen = {},
            onResume = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PDFDownloaderPausedPreview() {
    Hospital_AutomationTheme {
        PDFUploadDownloadItem(
            modifier = Modifier.width(348.dp),
            name = "Cam-Scanner-152314",
            currentSize = 1.7f,
            progress = 70,
            state = FileUploadingState.PAUSED,
            onPause = {},
            onOpen = {},
            onResume = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PDFDownloadedCompletePreview() {
    Hospital_AutomationTheme {
        PDFUploadDownloadItem(
            modifier = Modifier.width(348.dp),
            name = "Cam-Scanner-152314",
            currentSize = 21f,
            progress = 100,
            state = FileUploadingState.COMPLETE,
            onPause = {},
            onOpen = {},
            onResume = {}
        )
    }
}

enum class FileUploadingState {
    UPLOADING,PAUSED,DOWNLOADING,COMPLETE
}