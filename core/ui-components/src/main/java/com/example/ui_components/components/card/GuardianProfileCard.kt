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
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.buttons.HospitalAutomationOutLinedButton
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.network_image.NetworkImage

@Composable
fun GuardianProfileCard(
    name: String,
    details: (@Composable () -> Unit)? = null,
    onPhoneNumberButtonClick: () -> Unit,
    onEmailButtonClick: () -> Unit,
    phoneNumber: String,
    email: String,
    profileImageUrl: String,
    onNavigateUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(bottom = MaterialTheme.spacing.large24)
    ) {
        Box(modifier = Modifier) {
            NetworkImage(
                model = profileImageUrl,
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
        details?.let {
            details()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large32))
        }
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
fun GuardianProfileCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            GuardianProfileCard(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium16)
                    .fillMaxWidth(),
                phoneNumber = "+963 931 661 772",
                email = "aliahmad@gmail.com",
                name = "Ali Ahmad",
                onEmailButtonClick = {},
                onPhoneNumberButtonClick = {},
                details = {
                    DetailsItem(
                        iconRes = AppIcons.Outlined.location,
                        iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                        iconColor = MaterialTheme.colorScheme.primary,
                        title = stringResource(id = R.string.residential_address),
                        description = "Lattakia - Masaya Street",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MaterialTheme.spacing.medium16,
                                vertical = MaterialTheme.spacing.small12
                            ),
                    )
                    DetailsItem(
                        iconRes = AppIcons.Outlined.female,
                        iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                        iconColor = MaterialTheme.colorScheme.primary,
                        title = stringResource(id = R.string.gender),
                        description = stringResource(R.string.female),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MaterialTheme.spacing.medium16,
                                vertical = MaterialTheme.spacing.small12
                            ),
                    )
                },
                onNavigateUpButtonClick = {},
                profileImageUrl = "https://www.bing.com/images/search?q=doctor%20image&view=detailv2&FORM=IQFRBA&id=74928BEED5D0A38EE438C01E82948451C0F94FD6&selectedindex=0&&expw=1200&exph=1200&ccid=0Ttue7YQ&ck=A75C9DAA1BADE50716E013E4D286ECFF&simid=608009908073151178&thid=OIP.0Ttue7YQAsl2DKGwh4POzgHaHa&idpp=serp&idpbck=1&ajaxhist=0&ajaxserp=0"
            )
        }
    }
}