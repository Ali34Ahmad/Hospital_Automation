package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun EditableAppointmentTypeCard(
    index: Int,
    title: String,
    onTitleChanged: (String) -> Unit,
    subtitle: String?,
    onSubtitleClick: () -> Unit,
    onDelete: () -> Unit,
    description: String?,
    onDescriptionChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes deleteIcon: Int = HospitalAutomationIcons.delete,
    @StringRes warningMessage: Int = R.string.description_not_added,
    @DrawableRes noteLeadingIcon: Int = HospitalAutomationIcons.problem,
    @DrawableRes noteTrailingIcon : Int = HospitalAutomationIcons.add
) {
    var showWarningMessage by remember{mutableStateOf(description.isNullOrBlank())}
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.extraSmall4,
                    bottom = MaterialTheme.spacing.medium16,
                    start = MaterialTheme.spacing.medium16,
                    end = MaterialTheme.spacing.small8,
                )
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //First section of column
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                //index with title and subtitle
                Column {
                    Text(
                        text = "$index.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.additionalColorScheme.onBackgroundVariantLight
                    )
                    //Medicine with drug or appointment type with time
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            modifier = Modifier.wrapContentWidth()
                                .padding(end = MaterialTheme.spacing.extraSmall4),
                            value = title,
                            onValueChange = onTitleChanged,
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                                ),
                            singleLine = true
                        )
                        subtitle?.let {
                            Text(
                                text = "( $subtitle )",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.clickable{onSubtitleClick()}
                            )
                        }

                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = onDelete
                ) {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.sizing.small24),
                        painter = painterResource(deleteIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }

            }
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.surface,
                thickness = 2.dp
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            //Second section of column which is the description section
            AnimatedContent(
                targetState = showWarningMessage
            ) { target ->
                when(target){
                    true -> {
                        WarningCard(
                            text = warningMessage,
                            leadingIcon = noteLeadingIcon,
                            trailingIcon = noteTrailingIcon,
                            onTrailingIconClick = {
                                showWarningMessage = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    false -> {
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = description?.toString().orEmpty(),
                            onValueChange = onDescriptionChanged,
                            textStyle = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun CardWithNoteAndActionsPreview() {
    Hospital_AutomationTheme {
        var description by remember { mutableStateOf<String?>(null) }
        var title by remember { mutableStateOf("Check up") }
        EditableAppointmentTypeCard(
            index = 1,
            title = title,
            onTitleChanged = {
                title = it
            },
            subtitle = "1000",
            onDelete = {},
            description = description,
            onDescriptionChanged = {
                description = it
            },
            onSubtitleClick = {}
        )
    }
}
