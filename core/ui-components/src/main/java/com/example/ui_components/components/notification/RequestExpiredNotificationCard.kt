package com.example.ui_components.components.notification

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.model.Notification
import com.example.ui.fake.createSampleNotifications
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton

@Composable
fun RequestNotificationCard(
    notification: Notification,
    onResendRequestButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NotificationWrapperCard(
        notification = notification,
        actions = {
            HospitalAutomationButton(
                onClick = onResendRequestButtonClick,
                text = stringResource(R.string.resend_request),
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier,
    )
}

@DarkAndLightModePreview
@Composable
fun RequestNotificationCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            RequestNotificationCard(
                notification = createSampleNotifications()[0],
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onResendRequestButtonClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RequestNotificationCardExpiredPreview() {
    Hospital_AutomationTheme {
        Surface {
            RequestNotificationCard(
                notification = createSampleNotifications()[1],
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onResendRequestButtonClick = {},
            )
        }
    }
}

