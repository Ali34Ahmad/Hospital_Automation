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
import androidx.compose.ui.text.style.TextOverflow
import com.example.constants.icons.AppIcons
import com.example.model.doctor.day_scedule.DaySchedule
import com.example.model.enums.DoctorStatus
import com.example.model.enums.Gender
import com.example.model.helper.ext.toCapitalized
import com.example.ui.fake.createSampleWorkDayList
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.list.AvailabilityScheduleLazyRow
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader
import com.example.ui_components.components.screen_section.SectionWithTitle


@Composable
fun PharmacyDetailsCard(
    name: String,
    pharmacyName: String,
    profileImageUrl: String,
    gender: Gender,
    residentialAddress: String,
    currentStatus: DoctorStatus,
    workDays: List<DaySchedule>,
    phoneNumber: String,
    email: String,
    onPhoneNumberButtonClick: () -> Unit,
    onEmailButtonClick: () -> Unit,
    onNavigateUpButtonClick: () -> Unit,
    isAccepted: Boolean,
    isResigned: Boolean,
    isSuspended: Boolean,
    showNavigateUp: Boolean,
    pharmacyAddress: String,
    modifier: Modifier = Modifier,
) {
    val genderIcon = if (gender == Gender.MALE) AppIcons.Outlined.male else AppIcons.Outlined.female
    val sectionTitlePadding = PaddingValues(horizontal = MaterialTheme.spacing.medium16)
    val detailsItemModifier = Modifier
        .fillMaxWidth()

    val (currentStatusItemDescriptionColor, availabilityStatus) = if (currentStatus == DoctorStatus.OPENED) {
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
                model = profileImageUrl,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                loading = {
                    NetworkImageLoader(
                        modifier=Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.sizing.profileImageHeight)
                    )
                },
                errorCompose = {
                    NetworkImageError()
                },
            )
            if(showNavigateUp){
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
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = pharmacyName,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.additionalColorScheme.onBackgroundVariant,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)
            )

        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
        if (!isAccepted) {
            WarningCard(
                text = stringResource(R.string.not_accepted_warning),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16),
            )
        } else if (isResigned) {
            WarningCard(
                text = stringResource(R.string.resigned_warning),
                clickableText = stringResource(R.string.learn_more),
                onTextClick = {
//                    onResignedWaningTextClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16),
            )
        } else if (isSuspended) {
            WarningCard(
                text = stringResource(R.string.suspended_warning),
                clickableText = stringResource(R.string.learn_more),
                onTextClick = {
//                    onSuspendWarningTextClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16),
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))
        DetailsItem(
            iconRes = AppIcons.Outlined.pharmacyLocation,
            title = stringResource(id = R.string.pharmacy_location),
            description = pharmacyAddress,
            modifier = detailsItemModifier,
            isMultipleLines = true,
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
            description = residentialAddress,
            modifier = detailsItemModifier,
            isMultipleLines = true,
        )
        DetailsItem(
            iconRes = genderIcon,
            title = stringResource(id = R.string.gender),
            description = gender.name.toCapitalized(),
            modifier = detailsItemModifier,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))

        SectionWithTitle(
            title = stringResource(R.string.availability_schedule),
            modifier = Modifier.fillMaxWidth(),
            titleAreaPadding = sectionTitlePadding,
        ) {
            AvailabilityScheduleLazyRow(
                workDays = workDays,
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
fun PharmacyDetailsCardOpenedPreview() {
    Hospital_AutomationTheme {
        Surface {
            PharmacyDetailsCard(
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
                residentialAddress = "Syria - Lattakia - Massaya Streen, opposite of Anbar Coffee",
                pharmacyName = "Cardiologist",
                workDays = createSampleWorkDayList(),
                currentStatus = DoctorStatus.CLOSED,
                isAccepted = true,
                isResigned = true,
                isSuspended = false,
                pharmacyAddress = "adsfe",
                showNavigateUp = true,
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun PharmacyDetailsCardClosedPreview() {
    Hospital_AutomationTheme {
        Surface {
            PharmacyDetailsCard(
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
                residentialAddress = "Syria - Lattakia - Massaya Streen, opposite of Anbar Coffee",
                pharmacyName = "Cardiologist",
                workDays = createSampleWorkDayList(),
                currentStatus = DoctorStatus.OPENED,
                isAccepted = true,
                isResigned = false,
                isSuspended = false,
                showNavigateUp = true,
                pharmacyAddress = "Afd d- dfja weio- dfe",
            )
        }
    }
}

