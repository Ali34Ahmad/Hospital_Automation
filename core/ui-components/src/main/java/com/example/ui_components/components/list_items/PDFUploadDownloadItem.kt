package com.example.ui_components.components.list_items

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.constants.enums.FileUploadingState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.custom_file.PdfFileWithText
import kotlin.math.roundToInt


@Composable
fun PDFUploadDownloadItem(
    name: String,
    fileSize: Float,
    progress: Int,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onOpen: () -> Unit,
    state: FileUploadingState,
    modifier: Modifier = Modifier,
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
            .clickable {
                when (state) {
                    FileUploadingState.UPLOADING -> onPause()
                    FileUploadingState.PAUSED -> onResume()
                    FileUploadingState.DOWNLOADING -> onPause()
                    FileUploadingState.COMPLETE -> onOpen()
                }
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PdfFileWithText(
            modifier = Modifier
                .width(MaterialTheme.sizing.medium32)
                .height(MaterialTheme.sizing.medium40)
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
                    text = "$fileSize MB",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.additionalColorScheme.onBackgroundVariant
                )
                val text = when (state) {
                    FileUploadingState.UPLOADING -> stringResource(R.string.uploading) + dots
                    FileUploadingState.PAUSED -> stringResource(R.string.paused)
                    FileUploadingState.DOWNLOADING -> stringResource(R.string.downloading) + dots
                    FileUploadingState.COMPLETE -> stringResource(R.string.open)
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Visible
                )
            }
            // Linear progress indicator with percentage
            //when the state of the pdf file is not complete show the linear progress indicator
            if (state != FileUploadingState.COMPLETE) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val color = if (state == FileUploadingState.PAUSED)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else
                        MaterialTheme.colorScheme.primary
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
                        color = MaterialTheme.additionalColorScheme.onBackgroundVariant
                    )
                }
            }

        }
    }
}

@DarkAndLightModePreview
@Composable
fun PDFDownloaderDownloadingPreview() {
    Hospital_AutomationTheme {
        Surface {
            PDFUploadDownloadItem(
                modifier = Modifier.width(348.dp),
                name = "Cam-Scanner-152314",
                fileSize = 1.7f,
                progress = 70,
                state = FileUploadingState.DOWNLOADING,
                onPause = {},
                onOpen = {},
                onResume = {}
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PDFDownloaderUploadingPreview() {
    Hospital_AutomationTheme {
        Surface {
            PDFUploadDownloadItem(
                modifier = Modifier.width(348.dp),
                name = "Cam-Scanner-152314",
                fileSize = 1.7f,
                progress = 70,
                state = FileUploadingState.UPLOADING,
                onPause = {},
                onOpen = {},
                onResume = {}
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PDFDownloaderPausedPreview() {
    Hospital_AutomationTheme {
        Surface {
            PDFUploadDownloadItem(
                modifier = Modifier.width(348.dp),
                name = "Cam-Scanner-152314",
                fileSize = 1.7f,
                progress = 70,
                state = FileUploadingState.PAUSED,
                onPause = {},
                onOpen = {},
                onResume = {}
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PDFDownloadedCompletePreview() {
    Hospital_AutomationTheme {
        Surface {
            PDFUploadDownloadItem(
                modifier = Modifier.width(348.dp),
                name = "Cam-Scanner-152314",
                fileSize = 21f,
                progress = 100,
                state = FileUploadingState.COMPLETE,
                onPause = {},
                onOpen = {},
                onResume = {}
            )
        }
    }
}
