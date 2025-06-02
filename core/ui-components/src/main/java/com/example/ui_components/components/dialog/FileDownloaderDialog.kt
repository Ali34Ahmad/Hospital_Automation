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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.constants.enums.FileLoadingState
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateFormat
import com.example.ext.toFileSizeFormatReadable
import com.example.model.File
import com.example.model.helper.ext.toCapitalizedString
import com.example.model.user.FullName
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader

@Composable
fun FileDownloaderDialog(
    fileLoadingState: FileLoadingState,
    file: File,
    userFullName: FullName,
    onDismiss: () -> Unit,
    profileImageUrl: String,
    modifier: Modifier = Modifier
) {
    val icon = when (fileLoadingState) {
        FileLoadingState.UPLOADING -> {
            AppIcons.Outlined.pause
        }

        FileLoadingState.PAUSED -> {
            AppIcons.Outlined.download
        }

        FileLoadingState.COMPLETE -> {
            AppIcons.Outlined.check
        }

        else -> {
            AppIcons.Outlined.error
        }
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium16,
                    vertical = MaterialTheme.spacing.large24
                ),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .height(MaterialTheme.spacing.extraLarge104),
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
                            contentScale = ContentScale.FillHeight,
                            errorCompose = {
                                NetworkImageError(

                                )
                            },
                            loading = {
                                NetworkImageLoader(
                                    modifier=Modifier
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

                                }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(MaterialTheme.spacing.small8)
                                    .size(MaterialTheme.sizing.small18),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        CircularProgressIndicator(
                            progress = { file.uploadingProgress.toFloat() / 100 },
                            modifier = Modifier
                                .size(MaterialTheme.sizing.medium32)
                                .padding(MaterialTheme.spacing.extraSmall4),
                            strokeWidth = 3.dp,
                            color = MaterialTheme.colorScheme.primary
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
                            text = userFullName.toAppropriateFormat(),
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
                            text = file.fileName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = ((file.uploadingProgress * file.fileSizeWithBytes) / 100).toFileSizeFormatReadable() + "/" +
                                    file.fileSizeWithBytes.toFileSizeFormatReadable(),
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
                            text = fileLoadingState.name.toCapitalizedString(),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                    }
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
                fileLoadingState = FileLoadingState.UPLOADING,
                file = File(
                    uploadingProgress = 52,
                    fileSizeWithBytes = 111560233,
                    fileName = "documents.pdf"
                ),
                onDismiss = {},
                profileImageUrl = "",
                userFullName = FullName(
                    "Ali",
                    "Shafik",
                    "Ahmad"
                )
            )
        }
    }
}