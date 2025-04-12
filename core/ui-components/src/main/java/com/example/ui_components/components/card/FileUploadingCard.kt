package com.example.ui_components.components.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.enums.FileUploadingState
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.hospital_automation.core.components.card.IllustrationCard
import com.example.model.File
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.buttons.HospitalAutomationOutlinedButtonPreview
import com.example.ui_components.components.list_items.PDFDownloader
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun FileUploadingCard(
    title: String,
    description: String,
    file: File? = null,
    onFileClick: () -> Unit,
    onUploadFileButtonClick: () -> Unit,
    onReplaceFileButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IllustrationCard(
        modifier = modifier,
        title = title,
        titleColor = MaterialTheme.colorScheme.onBackground,
        description = description,
        actionButtonsSection = {
            if (file==null) {
                HospitalAutomationOutLinedButton(
                    onClick = onUploadFileButtonClick,
                    text = stringResource(R.string.upload_file),
                    icon = AppIcons.Outlined.file,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            file?.let {
                PDFDownloader(
                    name = file.fileName,
                    currentSize = file.fileSize,
                    progress = file.uploadingProgress,
                    uploadingState = FileUploadingState.UPLOADING,
                    onClick = onFileClick
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))
                Row(modifier = Modifier.fillMaxWidth()) {
                    HospitalAutomationOutLinedButton(
                        onClick = onReplaceFileButtonClick,
                        text = stringResource(R.string.replace_file),
                        icon = AppIcons.Outlined.file,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.large24))
                    HospitalAutomationButton(
                        onClick = onNextButtonClick,
                        text = stringResource(R.string.next),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    )
}

@DarkAndLightModePreview
@Composable
fun FileUploadingCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            FileUploadingCard(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.child_birth_certification_is_required),
                description = stringResource(R.string.upload_file_description),
                file = File(uploadingProgress = 70,
                fileSize = 1.7f,
                fileName = "Cam-Scanner-152314",),
                onUploadFileButtonClick = {},
                onFileClick = {},
                onNextButtonClick = {},
                onReplaceFileButtonClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun FileUploadingCardInit2Preview() {
    Hospital_AutomationTheme {
        Surface {
            FileUploadingCard(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.child_birth_certification_is_required),
                description = stringResource(R.string.upload_file_description),
                onUploadFileButtonClick = {},
                onFileClick = {},
                onNextButtonClick = {},
                onReplaceFileButtonClick = {},
            )
        }
    }
}

