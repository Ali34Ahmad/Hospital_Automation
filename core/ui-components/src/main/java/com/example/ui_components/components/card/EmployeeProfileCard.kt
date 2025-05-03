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
import com.example.constants.enums.Gender
import com.example.constants.icons.AppIcons
import com.example.ext.toCapitalizedString
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.network_image.NetworkImage

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
    modifier: Modifier = Modifier,
) {

    val genderIcon=if(gender==Gender.MALE)AppIcons.Outlined.male else AppIcons.Outlined.female

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
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))
        DetailsItem(
            iconRes = AppIcons.Outlined.location,
            iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.primary,
            title = stringResource(id = R.string.residential_address),
            description = address,
            modifier = Modifier
                .fillMaxWidth(),
            isMultipleLines = true,
        )
        DetailsItem(
            iconRes = genderIcon,
            iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconColor = MaterialTheme.colorScheme.primary,
            title = stringResource(id = R.string.gender),
            description = gender.name.toCapitalizedString(),
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
fun EmployeeProfileCardPreview() {
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
                address = "Syria - Lattakia - Massaya Streen, opposite of Anbar Coffee"
            )
        }
    }
}