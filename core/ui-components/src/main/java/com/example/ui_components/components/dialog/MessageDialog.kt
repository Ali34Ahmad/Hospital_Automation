package com.example.ui_components.components.dialog

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageDialog(
    showDialog:Boolean,
    title: String,
    description: String,
    modifier:Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit={},
    showCancelButton: Boolean = true,
    dismissButtonText:String= stringResource(R.string.cancel),
    confirmButtonText:String= stringResource(R.string.ok),
) {
    if(showDialog){
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
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
fun MessageDialogPreview() {
    Hospital_AutomationTheme {
        Surface {
            MessageDialog(
                showDialog=true,
                title = stringResource(R.string.doctor_request),
                description = stringResource(R.string.doctor_request_description),
                onConfirm = {},
            )
        }
    }
}