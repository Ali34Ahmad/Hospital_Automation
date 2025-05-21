package com.example.ui_components.components.card

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import coil3.toUri
import com.example.constants.enums.FileUploadingState
import com.example.constants.icons.AppIcons
import com.example.model.File
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.additionalShapes
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.icon.IconWithBackground
import com.example.ui_components.components.network_image.NetworkImage

@Composable
fun ImageUploaderCard(
    imageUri: Uri,
    fileUploadingState: FileUploadingState,
    file: File,
    onReplaceFileButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUri)
        .crossfade(true)
        .scale(Scale.FIT)
        .build()

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
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            IconButton(
                onClick = {},
                modifier=Modifier
                    .clip(CircleShape)
                    .background(
                    MaterialTheme.colorScheme.background.copy(alpha = 0.4f)
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            CircularProgressIndicator(
                progress = { file.uploadingProgress.toFloat()/100 },
                modifier = Modifier.size(36.dp)
                    .padding(MaterialTheme.spacing.extraSmall4),
                strokeWidth = 3.dp,
                color = MaterialTheme.colorScheme.secondary
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
                modifier = Modifier.weight(1f)
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
                file = File(
                    uploadingProgress = 50,
                    fileSizeWithBytes = 0,
                    fileName = ""
                ),
                onReplaceFileButtonClick = {},
                onNextButtonClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}