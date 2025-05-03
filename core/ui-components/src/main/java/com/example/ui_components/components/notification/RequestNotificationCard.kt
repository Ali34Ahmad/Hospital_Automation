package com.example.ui_components.components.notification

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.constants.enums.FileUploadingState
import com.example.model.Notification
import com.example.ui.fake.createSampleNotifications
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.list_items.PDFUploadDownloadItem

@Composable
fun RequestSentNotification(
    notification: Notification,
    fileName: String,
    fileSize: Float,
    progress: Int,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onOpen: () -> Unit,
    state: FileUploadingState,
    modifier: Modifier = Modifier,
) {
    NotificationWrapperCard(
        notification = notification,
        actions = {
            PDFUploadDownloadItem(
                name = fileName,
                fileSize = fileSize,
                progress = progress,
                onResume = onResume,
                onOpen = onOpen,
                onPause = onPause,
                state = state,
            )
        },
        modifier = modifier,
    )
}

@DarkAndLightModePreview
@Composable
fun RequestNotificationPreview() {
    Hospital_AutomationTheme {
        Surface {
            RequestSentNotification(
                notification = createSampleNotifications()[0],
                fileName = "Cam-Scanner-152314",
                fileSize = 21f,
                progress = 10,
                state = FileUploadingState.COMPLETE,
                onPause = {},
                onOpen = {},
                onResume = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RequestNotificationExpiredPreview() {
    Hospital_AutomationTheme {
        Surface {
            RequestSentNotification(
                notification = createSampleNotifications()[1],
                fileName = "Cam-Scanner-152314",
                fileSize = 21f,
                progress = 10,
                state = FileUploadingState.UPLOADING,
                onPause = {},
                onOpen = {},
                onResume = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

