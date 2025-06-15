package com.example.ui_components.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateFormat
import com.example.ext.toFileSizeFormatReadable
import com.example.model.FileInfo
import com.example.model.enums.FileDownloadingState
import com.example.model.user.FullName
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationTextButton
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader

@Composable
fun FileDownloaderDialog(
    showDialog: Boolean,
    fileDownloadingState: FileDownloadingState,
    fileInfo: FileInfo,
    userFullName: String,
    onDismiss: () -> Unit,
    onDownload: () -> Unit,
    onCancelDownload: () -> Unit,
    profileImageUrl: String,
    modifier: Modifier = Modifier
) {
    if (showDialog) {
        val icon = when (fileDownloadingState) {
            FileDownloadingState.IDLE -> {
                AppIcons.Outlined.download
            }

            FileDownloadingState.DOWNLOADING -> {
                AppIcons.Outlined.close
            }

            FileDownloadingState.PAUSED -> {
                AppIcons.Outlined.download
            }

            FileDownloadingState.COMPLETE -> {
                AppIcons.Outlined.check
            }

            FileDownloadingState.PENDING -> {
                AppIcons.Outlined.close
            }

            FileDownloadingState.FAILED -> {
                AppIcons.Outlined.refresh
            }

            FileDownloadingState.CANCELLED -> {
                AppIcons.Outlined.refresh
            }
        }

        val onFileClick = when (fileDownloadingState) {
            FileDownloadingState.IDLE,
            FileDownloadingState.FAILED,
            FileDownloadingState.CANCELLED -> {
                onDownload
            }

            FileDownloadingState.PAUSED,
            FileDownloadingState.PENDING -> {
                null
            }

            FileDownloadingState.DOWNLOADING -> {
                onCancelDownload
            }

            FileDownloadingState.COMPLETE -> {
                null
            }
        }

        val fileDownloadStateText = when (fileDownloadingState) {
            FileDownloadingState.IDLE -> stringResource(R.string.download)
            FileDownloadingState.PAUSED -> stringResource(R.string.download)
            FileDownloadingState.PENDING -> stringResource(R.string.pending_with_dots)
            FileDownloadingState.DOWNLOADING -> stringResource(R.string.downloading_with_dots)
            FileDownloadingState.COMPLETE -> stringResource(R.string.open)
            FileDownloadingState.FAILED -> stringResource(R.string.failed)
            FileDownloadingState.CANCELLED -> stringResource(R.string.cancelled)
        }

        val fileDownloadStateColor = when (fileDownloadingState) {
            FileDownloadingState.IDLE,
            FileDownloadingState.PAUSED,
            FileDownloadingState.PENDING,
            FileDownloadingState.DOWNLOADING,
            FileDownloadingState.COMPLETE -> MaterialTheme.colorScheme.primary

            FileDownloadingState.FAILED,
            FileDownloadingState.CANCELLED -> MaterialTheme.colorScheme.error
        }

        Dialog(
            onDismissRequest = onDismiss,
        ) {
            Surface(
                modifier = modifier
                    .clip(MaterialTheme.additionalShapes.small12)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                    ),
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                    modifier = Modifier.padding(
                        horizontal = MaterialTheme.spacing.medium16,
                        vertical = MaterialTheme.spacing.large24
                    ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.spacing.extraLarge104)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(0.3f)
                        ) {
                            NetworkImage(
                                model = profileImageUrl,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop,
                                errorCompose = {
                                    NetworkImageError(
                                        textStyle = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                loading = {
                                    NetworkImageLoader(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                                }
                            )
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        MaterialTheme.colorScheme.background.copy(alpha = 0.6f)
                                    )
                                    .clickable {
                                        if (onFileClick != null)
                                            onFileClick()
                                    }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(MaterialTheme.spacing.small8)
                                        .size(MaterialTheme.sizing.small18),
                                    tint = fileDownloadStateColor
                                )
                            }
                            CircularProgressIndicator(
                                progress = { fileInfo.uploadingProgress.toFloat() / 100 },
                                modifier = Modifier
                                    .size(MaterialTheme.sizing.medium32)
                                    .padding(MaterialTheme.spacing.extraSmall4),
                                strokeWidth = 3.dp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(
                                    start = MaterialTheme.spacing.small12,
                                )
                                .fillMaxHeight()
                                .weight(0.4f),
                            verticalArrangement = Arrangement.SpaceBetween

                        ) {
                            Text(
                                text = userFullName,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Spacer(
                                Modifier.height(
                                    MaterialTheme.spacing.extraSmall4
                                )
                            )
                            Text(
                                text = fileInfo.fileName,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = ((fileInfo.uploadingProgress * fileInfo.fileSizeWithBytes) / 100).toFileSizeFormatReadable() + "/" +
                                        fileInfo.fileSizeWithBytes.toFileSizeFormatReadable(),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Spacer(
                                Modifier.height(
                                    MaterialTheme.spacing.extraSmall4
                                )
                            )
                            Text(
                                text = fileDownloadStateText,
                                style = MaterialTheme.typography.labelMedium,
                                color = fileDownloadStateColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )

                        }
                    }
                    HospitalAutomationTextButton(
                        text = R.string.close,
                        onClick = onDismiss
                    )
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun FileDownloaderDialogPreview() {
    Hospital_AutomationTheme {
        Surface {
            FileDownloaderDialog(
                showDialog = true,
                fileDownloadingState = FileDownloadingState.DOWNLOADING,
                fileInfo = FileInfo(
                    uploadingProgress = 52,
                    fileSizeWithBytes = 111560233,
                    fileName = "documents.pdf"
                ),
                onDismiss = {},
                profileImageUrl = "",
                userFullName = "Ali Shafik Ahmad",
                onDownload = {},
                onCancelDownload = {},
            )
        }
    }
}