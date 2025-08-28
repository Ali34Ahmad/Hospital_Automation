package com.example.ui_components.components.card

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.constants.enums.FileUploadingState
import com.example.constants.icons.AppIcons
import com.example.model.FileInfo
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader

@Composable
fun ImageUploaderCard(
    imageUri: Uri,
    fileUploadingState: FileUploadingState,
    fileInfo: FileInfo,
    onReplaceFileButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    enableNextButton: Boolean,
    modifier: Modifier = Modifier,
) {
    val icon = when (fileUploadingState) {
        FileUploadingState.UPLOADING -> {
            AppIcons.Outlined.pause
        }

        FileUploadingState.PAUSED -> {
            AppIcons.Outlined.download
        }

        FileUploadingState.COMPLETE -> {
            AppIcons.Outlined.check
        }

        else -> {
            AppIcons.Outlined.error
        }
    }

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier=Modifier.heightIn(max = MaterialTheme.sizing.profileImageHeight)
        ) {
            NetworkImage(
                model = imageUri,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                errorCompose = {
                    NetworkImageError()
                },
                loading = {
                    NetworkImageLoader(
                        modifier=Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.sizing.profileImageHeight)
                    )
                }
            )
            IconButton(
                onClick = {},
                modifier=Modifier
                    .clip(CircleShape)
                    .background(
                    MaterialTheme.colorScheme.background.copy(alpha = 0.6f)
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(MaterialTheme.sizing.small18),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            CircularProgressIndicator(
                progress = { fileInfo.uploadingProgress.toFloat()/100 },
                modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize36)
                    .padding(MaterialTheme.spacing.extraSmall4),
                strokeWidth = 3.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.large24,
                    horizontal = MaterialTheme.spacing.medium16
                ),
        ) {
            HospitalAutomationOutLinedButton(
                onClick = onReplaceFileButtonClick,
                text = stringResource(R.string.replace_image),
                icon = AppIcons.Outlined.file,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.large24))
            HospitalAutomationButton(
                onClick = onNextButtonClick,
                text = stringResource(R.string.finish),
                modifier = Modifier.weight(1f),
                enabled = enableNextButton,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ImageUploaderCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            ImageUploaderCard(
                imageUri = "".toUri(),
                fileUploadingState = FileUploadingState.UPLOADING,
                fileInfo = FileInfo(
                    uploadingProgress = 50,
                    fileSizeWithBytes = 0,
                    fileName = ""
                ),
                onReplaceFileButtonClick = {},
                onNextButtonClick = {},
                enableNextButton = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}