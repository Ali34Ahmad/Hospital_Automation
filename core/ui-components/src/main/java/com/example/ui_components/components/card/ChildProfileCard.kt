package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ext.clickableTextRange
import com.example.ext.toAppropriateFormat
import com.example.model.enums.Gender
import com.example.model.helper.ext.toCapitalized
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.items.SubDetailsItem
import java.time.Instant
import java.util.Date

@Composable
fun ChildProfileCard(
    fatherName:String,
    motherName:String,
    gender: Gender,
    dateOfBirth: Date,
    employeeName: String,
    guardiansNumber: Int,
    onBirthCertificateItemClick: () -> Unit,
    onBirthCertificateItemDescriptionClick: () -> Unit,
    onGuardianTagItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val detailsItemModifier = Modifier
        .fillMaxWidth()
    val genderIcon=if(gender==Gender.MALE)AppIcons.Outlined.male else AppIcons.Outlined.female
    val birthCertificateDescription=stringResource(R.string.uploaded_by,employeeName)

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                bottom = MaterialTheme.spacing.large24,
                top = MaterialTheme.spacing.small12,
            )
    ) {
        DetailsItem(
            iconRes = AppIcons.Outlined.father,
            title = stringResource(id = R.string.father_name),
            description = fatherName,
            modifier = detailsItemModifier,
        )
        DetailsItem(
            iconRes = AppIcons.Outlined.female,
            title = stringResource(id = R.string.mother_name),
            description = motherName,
            modifier =detailsItemModifier,
        )
        DetailsItem(
            iconRes = genderIcon,
            title = stringResource(id = R.string.gender),
            description = gender.name.toCapitalized(),
            modifier = detailsItemModifier,
        )
        DetailsItem(
            iconRes = AppIcons.Outlined.specificDate,
            title = stringResource(id = R.string.date_of_birth),
            description = dateOfBirth.toAppropriateFormat(),
            modifier = detailsItemModifier,
        )
        DetailsItem(
            iconRes = AppIcons.Outlined.certificate,
            title = stringResource(id = R.string.birth_certificate),
            description = birthCertificateDescription,
            onClick = onBirthCertificateItemClick,
            modifier = detailsItemModifier,
            descriptionClickableTextRange = birthCertificateDescription.clickableTextRange(employeeName),
            onDescriptionClick = onBirthCertificateItemDescriptionClick,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))
        SubDetailsItem(
            title = stringResource(R.string.guardians),
            description = stringResource(R.string.guardians_number,guardiansNumber),
            onClick = onGuardianTagItemClick,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
        )
    }
}

@DarkAndLightModePreview
@Composable
fun ChildProfileCardPreview() {
    Hospital_AutomationTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ChildProfileCard(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium16)
                    .fillMaxWidth(),
                onGuardianTagItemClick = {},
                onBirthCertificateItemClick = {},
                onBirthCertificateItemDescriptionClick = {},
                fatherName = "Bassam Mansoura",
                motherName = "Mariam Mansoura",
                employeeName = "Elias Fares",
                dateOfBirth = Date.from(Instant.now()),
                gender = Gender.MALE,
                guardiansNumber = 2,
            )
        }
    }
}