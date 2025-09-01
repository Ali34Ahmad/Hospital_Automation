package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.ui_components.components.icon.IconWithBackground
import com.example.ui_components.components.items.FailedImage
import com.example.ui_components.components.network_image.NetworkImage

@Composable
fun PrescribedMedicineViewerCard(
    medicineName: String,
    titer: Int?,
    imageUrl: String?,
    hasNote: Boolean,
    modifier: Modifier = Modifier,
    onShowNote: () -> Unit,
    onNavigateToFulfillingPharmacy: () -> Unit,
    isFulfilled: Boolean,
    onClick: () -> Unit,
    @DrawableRes titerIcon: Int = AppIcons.Outlined.pill,
    @DrawableRes showNoteIcon: Int = AppIcons.Outlined.note,
    @DrawableRes navigateToPharmacyIcon: Int = AppIcons.Outlined.pharmacy,
    shape: Shape = RoundedCornerShape(MaterialTheme.sizing.small8)
) {
    Card(
        modifier = modifier
            .clip(shape)
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = shape,
    ) {
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.extraSmall4
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (isFulfilled) {
                        IconWithBackground(
                            iconRes = AppIcons.Outlined.check,
                            modifier = Modifier.size(MaterialTheme.spacing.small12),
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            iconColor = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Text(
                        text = medicineName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                // Drug amount
                titer?.let {
                    Spacer(Modifier.height(MaterialTheme.spacing.extraSmall4))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(MaterialTheme.sizing.small16),
                            painter = painterResource(titerIcon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(Modifier.width(MaterialTheme.spacing.extraSmall2))
                        Text(
                            text = titer.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

            }
            Spacer(Modifier.weight(1f))
            if (isFulfilled) {
                IconButton(
                    onClick = onNavigateToFulfillingPharmacy
                ) {
                    IconWithBackground(
                        iconRes = navigateToPharmacyIcon,
                        contentDescription = null,
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
                        iconColor = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            if (hasNote) {
                IconButton(
                    onClick = onShowNote
                ) {
                    IconWithBackground(
                        iconRes = showNoteIcon,
                        contentDescription = null,
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
                        iconColor = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            //note actions
        }
    }
}

@Preview
@Composable
fun EditablePrescribedMedicineViewerCardPreview() {
    Hospital_AutomationTheme {
        var hasNote by remember { mutableStateOf(false) }
        PrescribedMedicineViewerCard(
            medicineName = "Citamol",
            titer = 500,
            imageUrl = null,
            hasNote = hasNote,
            onShowNote = {},
            onNavigateToFulfillingPharmacy = {},
            modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            isFulfilled = true,
            onClick = { },
        )
    }
}

@Preview
@Composable
fun PrescribedMedicineViewerCardPreview() {
    Hospital_AutomationTheme {
        var hasNote by remember { mutableStateOf(false) }
        PrescribedMedicineViewerCard(
            medicineName = "Citamol",
            titer = 500,
            imageUrl = null,
            hasNote = hasNote,
            onShowNote = {},
            onNavigateToFulfillingPharmacy = {},
            modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            isFulfilled = false,
            onClick = {},
        )
    }
}