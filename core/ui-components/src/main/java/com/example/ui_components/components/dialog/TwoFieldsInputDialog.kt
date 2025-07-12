package com.example.ui_components.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoFieldsInputDialog(
    title: String,
    firstFieldText: String,
    firstFieldTextError: String?,
    isFirstFieldError: Boolean,
    onFirstFieldTextChange: (String) -> Unit,
    firstFieldMaxLines: Int = 1,
    @StringRes firstFieldLabel: Int,
    secondFieldText: String,
    isSecondFieldError: Boolean,
    secondFieldTextError: String?,
    onSecondFieldTextChange: (String) -> Unit,
    secondFieldMaxLines: Int = 1,
    @StringRes secondFieldLabel: Int,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    showCancelButton: Boolean,
    dismissButtonText: String,
    onConfirm: () -> Unit,
    confirmButtonText: String,
    isConfirmButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = onDismiss,
            modifier = modifier
                .clip(MaterialTheme.additionalShapes.large28)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Column(
                modifier = Modifier.padding(MaterialTheme.spacing.large24),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                HospitalAutomationTextFiled(
                    value = firstFieldText,
                    onValueChange = onFirstFieldTextChange,
                    label = firstFieldLabel,
                    supportingText = firstFieldTextError,
                    isError = isFirstFieldError,
                    isRequired = true,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    maxLines = firstFieldMaxLines,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                HospitalAutomationTextFiled(
                    value = secondFieldText,
                    onValueChange = onSecondFieldTextChange,
                    label = secondFieldLabel,
                    supportingText = secondFieldTextError,
                    isError = isSecondFieldError,
                    isRequired = true,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    maxLines = secondFieldMaxLines
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (showCancelButton) {
                        TextButton(
                            onClick = onDismiss,
                        ) {
                            Text(text = dismissButtonText)
                        }
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                    TextButton(
                        onClick = onConfirm,
                        enabled = isConfirmButtonEnabled,
                    ) {
                        Text(text = confirmButtonText)
                    }

                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TwoFieldsInputDialogPreview() {
    Hospital_AutomationTheme {
        Surface {
            TwoFieldsInputDialog(
                title = stringResource(R.string.new_interaction),
                firstFieldText = "Egg allergy",
                firstFieldTextError = "This is my error",
                isFirstFieldError = true,
                onFirstFieldTextChange = { },
                secondFieldText = "",
                isSecondFieldError = false,
                secondFieldTextError = "",
                onSecondFieldTextChange = { },
                showDialog = true,
                onDismiss = { },
                showCancelButton = true,
                dismissButtonText = stringResource(R.string.cancel),
                onConfirm = { },
                confirmButtonText = stringResource(R.string.add),
                firstFieldLabel = R.string.interaction_name,
                secondFieldLabel = R.string.interaction_description,
                isConfirmButtonEnabled = true,
            )
        }
    }
}