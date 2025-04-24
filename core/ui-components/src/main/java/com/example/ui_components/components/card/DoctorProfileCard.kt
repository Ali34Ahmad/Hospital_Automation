package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.constants.enums.AvailabilityStatus
import com.example.constants.enums.Gender
import com.example.constants.icons.AppIcons
import com.example.ext.toCapitalizedString
import com.example.model.WorkDay
import com.example.ui.fake.createSampleAppointments
import com.example.ui.fake.createSampleWorkDayList
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.flow_row.TagsFlowRow
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.list.AvailabilityScheduleLazyRow
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.screen_section.SectionWithTitle

@Composable
fun DoctorProfileCard(
    name: String,
    specialty: String,
    profileImageUrl: String,
    departmentName: String,
    onDepartmentItemClick: () -> Unit,
    gender: Gender,
    address: String,
    currentStatus: AvailabilityStatus,
    workDays: List<WorkDay>,
    appointmentTypes: List<String>,
    onServiceItemClick: (index: Int) -> Unit,
    phoneNumber: String,
    email: String,
    onPhoneNumberButtonClick: () -> Unit,
    onEmailButtonClick: () -> Unit,
    onNavigateUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val genderIcon = if (gender == Gender.MALE) AppIcons.Outlined.male else AppIcons.Outlined.female
    val sectionTitlePadding = PaddingValues(horizontal = MaterialTheme.spacing.medium16)
    val detailsItemModifier = Modifier
        .fillMaxWidth()

    val (currentStatusItemDescriptionColor, availabilityStatus) = if (currentStatus == AvailabilityStatus.OPEN) {
        Pair(
            MaterialTheme.additionalColorScheme.green,
            stringResource(R.string.open)
        )
    } else {
        Pair(
            MaterialTheme.colorScheme.error,
            stringResource(R.string.closed)
        )
    }

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(bottom = MaterialTheme.spacing.large24)
    ) {
        Box(modifier = Modifier) {
            NetworkImage(
                imageUrl = profileImageUrl,
                contentScale = ContentScale.Fit,
            )
            IconButton(
                onClick = onNavigateUpButtonClick,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraSmall4)
                    .clip(MaterialTheme.shapes.small),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
            ) {
                Icon(
                    painter = painterResource(AppIcons.Outlined.arrowBack),
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium16)
                    .weight(1f)
            )
            Text(
                text = specialty,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)
            )

        }
        // TODO: Add Suspended and Resigned states 
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))
        DetailsItem(
            iconRes = AppIcons.Outlined.department,
            title = stringResource(id = R.string.department),
            description = departmentName,
            modifier = detailsItemModifier,
            isMultipleLines = true,
            onClick = onDepartmentItemClick,
        )
        DetailsItem(
            iconRes = AppIcons.Outlined.availabilityStatus,
            title = stringResource(id = R.string.current_status),
            description = availabilityStatus,
            modifier = detailsItemModifier,
            descriptionColor = currentStatusItemDescriptionColor
        )

        DetailsItem(
            iconRes = AppIcons.Outlined.location,
            title = stringResource(id = R.string.residential_address),
            description = address,
            modifier = detailsItemModifier,
            isMultipleLines = true,
        )
        DetailsItem(
            iconRes = genderIcon,
            title = stringResource(id = R.string.gender),
            description = gender.name.toCapitalizedString(),
            modifier = detailsItemModifier,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))

        SectionWithTitle(
            title = stringResource(R.string.availability_schedule),
            modifier = Modifier.fillMaxWidth(),
            textPadding = sectionTitlePadding,
        ) {
            AvailabilityScheduleLazyRow(
                workDays = workDays,
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large24))
        SectionWithTitle(
            title = stringResource(R.string.appointment_types),
            modifier = Modifier.fillMaxWidth(),
            textPadding = sectionTitlePadding,
        ) {
            TagsFlowRow(
                tagsList = appointmentTypes,
                onTagClick = onServiceItemClick,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))

        Row(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)) {
            HospitalAutomationButton(
                onClick = onPhoneNumberButtonClick,
                text = phoneNumber,
                icon = AppIcons.Outlined.call,
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small8))
            HospitalAutomationOutLinedButton(
                onClick = onEmailButtonClick,
                text = stringResource(R.string.email),
                icon = AppIcons.Outlined.email,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DoctorProfileCardOpenedPreview() {
    Hospital_AutomationTheme {
        Surface {
            DoctorProfileCard(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium16)
                    .fillMaxWidth(),
                phoneNumber = "+963 931 661 772",
                email = "aliahmad@gmail.com",
                name = "Ali Ahmad",
                onEmailButtonClick = {},
                onPhoneNumberButtonClick = {},
                onNavigateUpButtonClick = {},
                profileImageUrl = "",
                gender = Gender.FEMALE,
                address = "Syria - Lattakia - Massaya Streen, opposite of Anbar Coffee",
                specialty = "Cardiologist",
                onDepartmentItemClick = {},
                departmentName = "Department of mental illnesses",
                onServiceItemClick = {},
                workDays = createSampleWorkDayList(),
                appointmentTypes = createSampleAppointments().map { it.appointmentType },
                currentStatus = AvailabilityStatus.OPEN
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun DoctorProfileCardClosedPreview() {
    Hospital_AutomationTheme {
        Surface {
            DoctorProfileCard(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium16)
                    .fillMaxWidth(),
                phoneNumber = "+963 931 661 772",
                email = "aliahmad@gmail.com",
                name = "Ali Ahmad",
                onEmailButtonClick = {},
                onPhoneNumberButtonClick = {},
                onNavigateUpButtonClick = {},
                profileImageUrl = "",
                gender = Gender.FEMALE,
                address = "Syria - Lattakia - Massaya Streen, opposite of Anbar Coffee",
                specialty = "Cardiologist",
                onDepartmentItemClick = {},
                departmentName = "Department of mental illnesses",
                onServiceItemClick = {},
                workDays = createSampleWorkDayList(),
                appointmentTypes = createSampleAppointments().map { it.appointmentType },
                currentStatus = AvailabilityStatus.OPEN
            )
        }
    }
}

