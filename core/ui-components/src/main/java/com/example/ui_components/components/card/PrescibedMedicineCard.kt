package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion.then
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ext.shimmerEffect
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.FailedImage
import com.example.ui_components.components.network_image.NetworkImage

@Composable
fun PrescribedMedicineCard(
    medicineName: String,
    drug: Int,
    imageUrl: String?,
    hasNote: Boolean,
    modifier: Modifier = Modifier,
    onAddNote: ()-> Unit ={},
    onEditNote: ()-> Unit ={},
    onTrailingIconClick:()-> Unit = {},
    canEdit: Boolean = true,
    onClick: ()-> Unit = {},
    @DrawableRes drugIcon: Int = AppIcons.Outlined.pill,
    @DrawableRes addNoteIcon: Int = AppIcons.Outlined.addNotes,
    @DrawableRes editNoteIcon: Int = AppIcons.Outlined.editNote,
    @DrawableRes removeIcon: Int = AppIcons.Outlined.close,
    shape: Shape = RoundedCornerShape(MaterialTheme.sizing.small8)
) {
    Card(
        modifier = modifier.clickable(
            enabled = !canEdit
        ){
            onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = shape
    ){
        Row(
            modifier = Modifier.padding(
                MaterialTheme.spacing.medium16
            ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NetworkImage(
                model = imageUrl,
                modifier = Modifier
                    .size(MaterialTheme.sizing.medium48)
                    .clip(RoundedCornerShape(MaterialTheme.sizing.small6)),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(MaterialTheme.sizing.medium48)
                            .clip(RoundedCornerShape(MaterialTheme.sizing.small6))
                            .shimmerEffect()
                    )
                },
                errorCompose = {
                    FailedImage(
                        size = MaterialTheme.sizing.medium48,
                        shape = RoundedCornerShape(MaterialTheme.sizing.small6)
                    )
                },
            )
            Spacer(Modifier.width(MaterialTheme.spacing.small8))
            //Medicine name and its drug amount
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = medicineName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                // Drug amount
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.sizing.small16),
                        painter = painterResource(drugIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(Modifier.width(MaterialTheme.spacing.extraSmall2))
                    Text(
                        text = drug.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

            }
            Spacer(Modifier.weight(1f))
            //note actions
            if (canEdit) {
                Crossfade(
                    targetState = hasNote,
                    label = "note action cross fade"
                ) { sate ->
                    if (sate) { //add note icon button
                        IconButton(
                            onClick = onAddNote,
                            modifier = Modifier
                                .size(
                                    MaterialTheme.sizing.small24
                                ),
                        ) {
                            Icon(
                                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall4),
                                painter = painterResource(addNoteIcon),
                                contentDescription = null,
                            )
                        }
                    } else {//edit note icon button
                        IconButton(
                            onClick = onEditNote,
                            modifier = Modifier
                                .background(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.secondaryContainer
                                )
                                .size(
                                    MaterialTheme.sizing.small24
                                ),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Icon(
                                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall4),
                                painter = painterResource(editNoteIcon),
                                contentDescription = null,
                            )
                        }
                    }
                }
                Spacer(Modifier.width(MaterialTheme.sizing.small16))
            // remove icon button
            IconButton(
                onClick = onTrailingIconClick,
                modifier = Modifier
                    .size(
                        MaterialTheme.sizing.small24
                    ),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Icon(
                    modifier = Modifier.padding(MaterialTheme.spacing.extraSmall4),
                    painter = painterResource(removeIcon),
                    contentDescription = null,
                )
            }
        }
        }
    }
}

@Preview
@Composable
fun EditablePrescribedMedicineCardPreview() {
    Hospital_AutomationTheme {
        var hasNote by remember { mutableStateOf(false) }
        PrescribedMedicineCard(
            medicineName = "Citamol",
            drug = 500,
            imageUrl = null,
            hasNote = hasNote,
            onAddNote = {},
            onEditNote = {},
            onTrailingIconClick = {},
            modifier = Modifier.padding(MaterialTheme.spacing.medium16),
        )
    }
}
@Preview
@Composable
fun PrescribedMedicineCardPreview() {
    Hospital_AutomationTheme {
        var hasNote by remember { mutableStateOf(false) }
        PrescribedMedicineCard(
            canEdit = false,
            medicineName = "Citamol",
            drug = 500,
            imageUrl = null,
            hasNote = hasNote,
            onAddNote = {},
            onEditNote = {},
            onTrailingIconClick = {},
            modifier = Modifier.padding(MaterialTheme.spacing.medium16),
        )
    }
}