package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.ext.toAppropriateFormat
import com.example.model.Department
import com.example.ui.fake.createSampleDepartmentData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.overlapping_image.OverlappingImagesVerticalBoxWithNumber

@Composable
fun DepartmentCard(
    department: Department,
    modifier: Modifier = Modifier,
) {
    val (availabilityState, availabilityTextColor) = if (department.isAvailableNow) {
        Pair(
            stringResource(R.string.open),
            MaterialTheme.additionalColorScheme.green
        )
    } else {
        Pair(
            stringResource(R.string.closed),
            MaterialTheme.colorScheme.error
        )
    }

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.small12,
                    horizontal = MaterialTheme.spacing.medium16
                ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
        ) {
            OverlappingImagesVerticalBoxWithNumber(
                imagesUrls = department.activeDoctors.map { it.imageUrl },
                number = department.activeDoctors.size - 3
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall4)
            ) {
                Text(
                    text = department.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = availabilityState,
                    style = MaterialTheme.typography.bodySmall,
                    color =availabilityTextColor,
                )
                Text(
                    text = department.creatingDate.toAppropriateFormat(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DepartmentCardPreview(){
    Hospital_AutomationTheme{
        Surface{
            DepartmentCard(
                department = createSampleDepartmentData()[0],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}