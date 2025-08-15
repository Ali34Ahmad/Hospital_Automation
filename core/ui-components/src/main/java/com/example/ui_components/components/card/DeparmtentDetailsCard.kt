package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.Department
import com.example.model.enums.DoctorStatus
import com.example.ui.fake.createSampleDepartmentData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.flow_row.TagsFlowRow
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.list.AvailabilityScheduleLazyRow
import com.example.ui_components.components.list.DepartmentDoctorsLazyRow
import com.example.ui_components.components.overlapping_image.OverlappingImagesTriangleBoxWithNumber
import com.example.ui_components.components.screen_section.SectionWithTitle

@Composable
fun DepartmentDetailsCard(
    department: Department,
    currentStatus: DoctorStatus,
    onVaccinesItemClick: () -> Unit,
    onDoctorItemClick: (Int) -> Unit,
    onServiceItemClick: (index: Int) -> Unit,
    onLearnMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val detailsItemModifier = Modifier.fillMaxWidth()

    val (currentStatusItemDescriptionColor, availabilityStatus) =

        if (department.isDeactivated || currentStatus == DoctorStatus.OPENED) {
            Pair(
                MaterialTheme.colorScheme.error,
                stringResource(R.string.closed)
            )
        } else {
            Pair(
                MaterialTheme.additionalColorScheme.green,
                stringResource(R.string.open)
            )
        }
    val sectionTitlePadding = PaddingValues(horizontal = MaterialTheme.spacing.medium16)

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(vertical = MaterialTheme.spacing.large24)
    ) {

        Row(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
        ) {
            OverlappingImagesTriangleBoxWithNumber(
                imagesUrls = department.activeDoctors.take(3).map { it.imageUrl },
                number = department.activeDoctors.size - 3
            )
            Text(
                text = department.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = MaterialTheme.spacing.small8)
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))
        if (department.isDeactivated) {
            WarningCard(
                text = stringResource(
                    R.string.department_deactivation_reason,
                    department.deactivatedBy?.name ?: ""
                ),
                clickableText = stringResource(R.string.learn_more),
                onTextClick =onLearnMoreClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16)
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))

        DetailsItem(
            iconRes = AppIcons.Outlined.availabilityStatus,
            title = stringResource(id = R.string.current_status),
            description = availabilityStatus,
            modifier = detailsItemModifier,
            descriptionColor = currentStatusItemDescriptionColor
        )
        if (department.providesVaccine) {
            DetailsItem(
                iconRes = AppIcons.Outlined.syringe,
                title = stringResource(id = R.string.vaccines),
                description = stringResource(R.string.click_to_see_vaccines),
                modifier = detailsItemModifier,
                onClick = onVaccinesItemClick
            )
        }


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))

        SectionWithTitle(
            title = stringResource(R.string.availability_schedule),
            modifier = Modifier.fillMaxWidth(),
            titleAreaPadding = sectionTitlePadding,
        ) {
            AvailabilityScheduleLazyRow(
                workDays = department.workDays,
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))
        SectionWithTitle(
            title = stringResource(R.string.active_doctors),
            modifier = Modifier.fillMaxWidth(),
            titleAreaPadding = sectionTitlePadding,
        ) {
            DepartmentDoctorsLazyRow(
                doctors = department.activeDoctors,
                onItemClick = onDoctorItemClick,
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))

        SectionWithTitle(
            title = stringResource(R.string.services),
            modifier = Modifier.fillMaxWidth(),
            titleAreaPadding = sectionTitlePadding,
        ) {
            TagsFlowRow(
                tagsList = department.services.map { it.name },
                onTagClick = onServiceItemClick,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DepartmentDetailsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            DepartmentDetailsCard(
                department = createSampleDepartmentData()[0],
                currentStatus = DoctorStatus.OPENED,
                onVaccinesItemClick = {},
                onDoctorItemClick = {},
                onServiceItemClick = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onLearnMoreClick={},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DepartmentDetailsCardDeactivatedPreview() {
    Hospital_AutomationTheme {
        Surface {
            DepartmentDetailsCard(
                department = createSampleDepartmentData()[1],
                currentStatus = DoctorStatus.OPENED,
                onVaccinesItemClick = {},
                onDoctorItemClick = {},
                onServiceItemClick = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                onLearnMoreClick={},
            )
        }
    }
}
