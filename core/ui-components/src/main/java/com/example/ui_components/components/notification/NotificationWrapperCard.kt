package com.example.ui_components.components.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.constants.enums.NotificationType
import com.example.constants.enums.RequestState
import com.example.ext.toAppropriateFormat
import com.example.model.Notification
import com.example.ui.fake.createSampleNotifications
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.tag.FilledTagItem


@Composable
fun NotificationWrapperCard(
    notification: Notification,
    modifier: Modifier = Modifier,
    actions: @Composable (() -> Unit)? = null,
) {

    val notificationType = if (notification.type != null) {
        notification.type.text
    } else {
        notification.request?.state?.text
    }
    val (tagTextColor, tagBackgroundColor) =
        if (notification.request != null) {
            if (notification.request.state == RequestState.PENDING ||
                notification.request.state == RequestState.CONFIRMED
            ) {
                Pair(
                    MaterialTheme.additionalColorScheme.onPrimaryContainerBlue,
                    MaterialTheme.colorScheme.primaryContainer
                )
            } else {
                Pair(
                    MaterialTheme.colorScheme.onErrorContainer,
                    MaterialTheme.colorScheme.errorContainer
                )
            }
        } else {
            if (notification.type == NotificationType.BOOKING_APPOINTMENT ||
                notification.type == NotificationType.ADDING_VACCINE ||
                notification.type == NotificationType.DISPENSING_PRESCRIPTION
            ) {
                Pair(
                    MaterialTheme.additionalColorScheme.onPrimaryContainerBlue,
                    MaterialTheme.colorScheme.primaryContainer
                )
            } else {
                Pair(
                    MaterialTheme.colorScheme.onErrorContainer,
                    MaterialTheme.colorScheme.errorContainer
                )
            }
        }

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.medium16)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FilledTagItem(
                    text = notificationType.toString(),
                    textColor = tagTextColor,
                    backgroundColor = tagBackgroundColor,
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = notification.sendTime.toLocalTime().toAppropriateFormat(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
            Text(
                text = notification.message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium16))
            actions?.let {
                actions()
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun NotificationWrapperCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            NotificationWrapperCard(
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                actions = {},
                notification = createSampleNotifications()[1]
            )
        }
    }
}