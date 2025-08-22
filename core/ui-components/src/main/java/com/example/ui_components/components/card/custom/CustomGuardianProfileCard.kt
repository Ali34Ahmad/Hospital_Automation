package com.example.ui_components.components.card.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.GuardianProfileCard
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.items.ProfileActionsItem
import com.example.ui_components.components.list.GuardianProfileActionsCard

@Composable
fun CustomGuardianProfileCard(
    fullName: String,
    phoneNumber: String,
    imageUrl: String,
    address: String?,
    gender: String?,
    isActive: Boolean,
    hasAdminAccess: Boolean,
    onlyCommunicationInfo: Boolean,
    onNavigateUp: () -> Unit,
    onPhoneNumberButtonClick: ()-> Unit,
    onEmailButtonClick: () -> Unit,
    onChildrenButtonClick: () -> Unit,
    onAppointmentsClick: ()-> Unit,
    onPrescriptionsClick: ()-> Unit,
    onMedicalRecordClick: ()-> Unit,
    onSuspendUserAccountClick: ()-> Unit,
    onReactivateUserAccount: ()-> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GuardianProfileCard(
            name = fullName,
            details = {
               address?.let{
                   DetailsItem(
                       iconRes = AppIcons.Outlined.location,
                       iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                       iconColor = MaterialTheme.colorScheme.primary,
                       title = stringResource(id = R.string.residential_address),
                       description = it,
                       modifier = Modifier
                           .fillMaxWidth(),
                       contentPadding = PaddingValues(
                           horizontal = MaterialTheme.spacing.medium16,
                           vertical = MaterialTheme.spacing.small12
                       )
                   )
               }
                gender?.let {
                    DetailsItem(
                        iconRes = AppIcons.Outlined.female,
                        iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                        iconColor = MaterialTheme.colorScheme.primary,
                        title = stringResource(id = R.string.gender),
                        description = it,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(
                            horizontal = MaterialTheme.spacing.medium16,
                            vertical = MaterialTheme.spacing.small12
                        )
                    )
                }
            },
            onPhoneNumberButtonClick = onPhoneNumberButtonClick,
            onEmailButtonClick = onEmailButtonClick,
            phoneNumber = phoneNumber,
            profileImageUrl = imageUrl,
            onNavigateUpButtonClick = onNavigateUp,
        )
        if(!onlyCommunicationInfo){
            GuardianProfileActionsCard(
                onChildrenClick = onChildrenButtonClick,
                onAppointmentsClick = onAppointmentsClick,
                onPrescriptionsClick = onPrescriptionsClick,
                onMedicalRecordClick = onMedicalRecordClick,
                onSuspendUserAccountClick = onSuspendUserAccountClick,
                onReactivateUserAccount = onReactivateUserAccount,
                hasAdminAccess = hasAdminAccess,
                isActive = isActive,
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.large24,
                ),
            )
        }
    }

}

@DarkAndLightModePreview
@Composable
fun GuardianProfileCardPreview(){
    Hospital_AutomationTheme {
        Surface{
            CustomGuardianProfileCard(
                fullName = "Ali Ahmad",
                phoneNumber = "09984244331",
                imageUrl = "images/123kfsf.png",
                address = "Lattakia - jableh",
                gender = "male",
                onNavigateUp = {},
                onPhoneNumberButtonClick = {},
                onEmailButtonClick = { },
                modifier = Modifier.padding(MaterialTheme.spacing.medium16).fillMaxWidth(),
                onChildrenButtonClick = {},
                isActive = true,
                hasAdminAccess = true,
                onAppointmentsClick = {},
                onPrescriptionsClick = {},
                onMedicalRecordClick = {},
                onSuspendUserAccountClick = {},
                onReactivateUserAccount = {},
                onlyCommunicationInfo = false
            )
        }
    }
}