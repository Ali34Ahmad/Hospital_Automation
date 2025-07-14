package com.example.ui_components.components.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.text_field.HospitalAutomationTextFiled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(
    title: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    subtitle: String = stringResource(R.string.add_note_subtitle),
    note: String,
    onNoteChanged: (String)-> Unit,
    onSave: ()-> Unit,
    @DrawableRes closeIcon : Int = AppIcons.Outlined.close,
    @StringRes placeholder: Int = R.string.type_your_note,
    @StringRes buttonText: Int = R.string.save
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
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            IconButton(
                onClick = onDismissRequest,
                modifier = Modifier
                    .size(
                        MaterialTheme.sizing.small24
                    )
                    .align(Alignment.End) ,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Icon(
                    painter = painterResource(closeIcon),
                    modifier = Modifier.padding(MaterialTheme.spacing.extraSmall4),
                    contentDescription = null
                )
            }
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
            Spacer(Modifier.height(MaterialTheme.spacing.small12))
            HospitalAutomationTextFiled(
                value = note,
                onValueChange = onNoteChanged,
                placeholder = placeholder,
                minLines = 2,
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small12))
            HospitalAutomationButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSave,
                text = stringResource(buttonText),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddNoteDialogPreview() {
    Hospital_AutomationTheme {
        var note by remember{ mutableStateOf("") }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AddNoteDialog(
                title = "Citamol",
                onDismissRequest = {
                },
                note = note,
                onNoteChanged = {note=it},
                onSave = {},
            )
        }
    }
}