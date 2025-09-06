package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun AppointmentTypeCard(
    index: Int,
    title: String,
    subtitle: String?,
    onDelete: () -> Unit,
    description: String?,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes deleteIcon: Int = AppIcons.Outlined.delete,
    @DrawableRes editIcon: Int = AppIcons.Outlined.edit,
    warningMessage: String = stringResource(R.string.description_not_added),
    @DrawableRes noteLeadingIcon: Int = AppIcons.Outlined.errorMessage,
    @DrawableRes noteTrailingIcon : Int = AppIcons.Outlined.add,
) {
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
                        color = MaterialTheme.additionalColorScheme.onBackgroundVariant
                    )
                    //Medicine with drug or appointment type with time
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(end = MaterialTheme.spacing.extraSmall4),
                            text = title,
                            style = MaterialTheme.typography.bodyMedium,
                            color =  MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                        subtitle?.let {
                            Text(
                                text = "($subtitle)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }

                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.size(MaterialTheme.sizing.small24),
                    onClick = {
                        onEdit()
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        painter = painterResource(editIcon),
                        contentDescription = null,
                    )
                }
                Spacer(Modifier.width(MaterialTheme.spacing.medium16))
                IconButton(
                    modifier = Modifier.size(MaterialTheme.sizing.small24),
                    onClick = onDelete
                ) {
                    Icon(
                        painter = painterResource(deleteIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }

            }
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.surface,
                thickness = MaterialTheme.sizing.extraSmall2
            )
            Spacer(Modifier.height(MaterialTheme.spacing.small8))
            //Second section of column which is the description section
            if(description.isNullOrBlank()){
                WarningCard(
                    text = warningMessage,
                    leadingIcon = noteLeadingIcon,
                    trailingIcon = noteTrailingIcon,
                    onTrailingIconClick = {
                        onEdit()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }else{
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

@Preview
@Composable
fun CardWithNoteAndActionsPreview() {
    Hospital_AutomationTheme {
        var title by remember { mutableStateOf("Check up") }
        AppointmentTypeCard(
            index = 1,
            title = title,
            subtitle = "15 min",
            onDelete = {},
            description = """
                A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside. A Review uses a special type of energy to create pictures of the inside of your body. It's like taking a photograph, but instead of light, it uses invisible rays to see what's happening inside.
            """.trimIndent(),
            onEdit = {}
        )
    }
}
