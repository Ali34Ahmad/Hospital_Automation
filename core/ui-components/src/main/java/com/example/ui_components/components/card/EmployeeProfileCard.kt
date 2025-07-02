package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.enums.Gender
import com.example.model.helper.ext.toCapitalized
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageError
import com.example.ui_components.components.network_image.NetworkImageLoader

@Composable
fun EmployeeProfileCard(
    name: String,
    onPhoneNumberButtonClick: () -> Unit,
    onEmailButtonClick: () -> Unit,
    phoneNumber: String,
    email: String,
    gender: Gender,
    address: String,
    profileImageUrl: String,
    onNavigateUpButtonClick: () -> Unit,
    isAccepted: Boolean,
    isResigned: Boolean,
    isSuspended: Boolean,
    showNavigateUp: Boolean,
    modifier: Modifier = Modifier,
) {

    val genderIcon = if (gender == Gender.MALE) AppIcons.Outlined.male else AppIcons.Outlined.female

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
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)
        )
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
            iconRes = AppIcons.Outlined.location,
            title = stringResource(id = R.string.residential_address),
            description = address,
            modifier = Modifier
                .fillMaxWidth(),
            isMultipleLines = true,
        )
        DetailsItem(
            iconRes = genderIcon,
            title = stringResource(id = R.string.gender),
            description = gender.name.toCapitalized(),
            modifier = Modifier
                .fillMaxWidth(),
        )
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
fun EmployeeProfileCardAcceptedPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmployeeProfileCard(
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
                isAccepted = true,
                isResigned = false,
                isSuspended = false,
                showNavigateUp = false,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileCardNotAcceptedPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmployeeProfileCard(
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
                isAccepted = false,
                isResigned = false,
                isSuspended = false,
                showNavigateUp = true,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileCardResignedPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmployeeProfileCard(
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
                isAccepted = true,
                isResigned = true,
                isSuspended = false,
                showNavigateUp = false,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileCardSuspendedPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmployeeProfileCard(
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
                isAccepted = true,
                isResigned = false,
                isSuspended = true,
                showNavigateUp = false,
            )
        }
    }
}


