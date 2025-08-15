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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarningDialogWithInputField(
    title: String,
    subtitle: String,
    text: String,
    onTextValueChange: (String)-> Unit,
    onDismissRequest: ()-> Unit,
    onConfirm:()-> Unit,
    onDismiss: ()-> Unit,
    modifier: Modifier = Modifier,
    dismissButtonText: String = stringResource(R.string.cancel) ,
    confirmButtonText: String = stringResource(R.string.ok),
    @StringRes placeholder: Int = R.string.deactivating_reason,
    showCancelButton: Boolean = true,
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.sizing.small16))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(
                vertical = MaterialTheme.spacing.medium16,
                horizontal = MaterialTheme.spacing.large24,
            ),
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))
            HospitalAutomationTextFiled(
                value = text,
                onValueChange = onTextValueChange,
                placeholder = placeholder,
                minLines = 2,
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

@Preview
@Composable
fun WarningDialogWithInputFieldPreview() {
    Hospital_AutomationTheme {
        WarningDialogWithInputField(
            title = "Are you sure?",
            subtitle = "you can reactivate the department again.",
            text = "",
            onTextValueChange = {},
            onDismissRequest = {},
            modifier = Modifier.fillMaxWidth(),
            onConfirm = {},
            onDismiss = {  },

        )
    }
}