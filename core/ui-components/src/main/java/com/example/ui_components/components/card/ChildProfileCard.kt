package com.example.ui_components.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateDateFormat
import com.example.model.child.LastAppointmentData
import com.example.model.child.LastVaccineData
import com.example.model.child.NextVaccineData
import com.example.model.enums.Gender
import com.example.model.helper.ext.capitalFirstChar
import com.example.model.helper.ext.clickableTextRange
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.items.SubDetailsItem
import com.example.ui_components.components.list.ChildProfileActions
import java.time.LocalDate

@Composable
fun ChildProfileCard(
    fatherName:String,
    motherName:String,
    gender: Gender,
    dateOfBirth: LocalDate,
    employeeName: String,
    guardiansNumber: Int,
    onBirthCertificateItemClick: () -> Unit,
    onBirthCertificateItemDescriptionClick: () -> Unit,
    onGuardianTagItemClick: () -> Unit,
    onAppointmentItemClick: (Int) -> Unit,
    onVaccinationTableClick: () -> Unit,
    onAppointmentsClick: () -> Unit,
    onPrescriptionsClick: () -> Unit,
    onMedicalRecordsClick: () -> Unit,
    hasAdminAccess: Boolean,
    modifier: Modifier = Modifier,
    lastVaccination: LastVaccineData? = null,
    nextVaccination: NextVaccineData? = null,
    lastAppointment: LastAppointmentData? = null,
    shape: Shape = MaterialTheme.shapes.small,
    colors: CardColors =  CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background
    )
) {
    val detailsItemModifier = Modifier
        .fillMaxWidth()
    val genderIcon=if(gender==Gender.MALE)AppIcons.Outlined.male else AppIcons.Outlined.female
    val birthCertificateDescription=stringResource(R.string.uploaded_by,employeeName)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = shape,
            colors = colors
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailsItem(
                    iconRes = AppIcons.Outlined.father,
                    title = stringResource(id = R.string.father_name),
                    description = fatherName,
                    modifier = detailsItemModifier,
                    contentPadding = PaddingValues(
                        top = MaterialTheme.spacing.large24,
                        bottom = MaterialTheme.spacing.small12,
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                    )
                )
                DetailsItem(
                    iconRes = AppIcons.Outlined.female,
                    title = stringResource(id = R.string.mother_name),
                    description = motherName,
                    modifier = detailsItemModifier,
                )
                DetailsItem(
                    iconRes = genderIcon,
                    title = stringResource(id = R.string.gender),
                    description = gender.name.capitalFirstChar(),
                    modifier = detailsItemModifier,
                )
                DetailsItem(
                    iconRes = AppIcons.Outlined.specificDate,
                    title = stringResource(id = R.string.date_of_birth),
                    description = dateOfBirth.toAppropriateDateFormat(),
                    modifier = detailsItemModifier,
                )
                DetailsItem(
                    iconRes = AppIcons.Outlined.certificate,
                    title = stringResource(id = R.string.birth_certificate),
                    description = birthCertificateDescription,
                    onClick = onBirthCertificateItemClick,
                    modifier = detailsItemModifier,
                    descriptionClickableTextRange = birthCertificateDescription.clickableTextRange(
                        employeeName
                    ),
                    onDescriptionClick = onBirthCertificateItemDescriptionClick,
                    contentPadding = PaddingValues(
                        bottom = MaterialTheme.spacing.large24,
                        top = MaterialTheme.spacing.small12,
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                    )
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large24),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium16)
                        .align(Alignment.Start)
                ) {
                    lastAppointment?.date?.let{date->
                        SubDetailsItem(
                            title = stringResource(R.string.last_appointment),
                            description = date.toAppropriateDateFormat(),
                            onClick = {
                                lastAppointment.id?.let {id->
                                    onAppointmentItemClick(id)
                                }
                            },
                        )
                    }
                    lastVaccination?.date?.let{date->
                        SubDetailsItem(
                            title = stringResource(R.string.last_vaccination),
                            description =date.toAppropriateDateFormat(),
                            onClick = {
                                lastVaccination.id?.let {id->
                                    onAppointmentItemClick(id)
                                }
                            },
                        )
                    }
                    nextVaccination?.date?.let{date->
                        SubDetailsItem(
                            title = stringResource(R.string.next_vaccination),
                            description = date.toAppropriateDateFormat(),
                            onClick = {
                                nextVaccination.id?.let {id->
                                    onAppointmentItemClick(id)
                                }
                            },
                        )
                    }
                    SubDetailsItem(
                        title = stringResource(R.string.guardians),
                        description = stringResource(R.string.guardians_number, guardiansNumber),
                        onClick = onGuardianTagItemClick,
                    )
                }
                Spacer(Modifier.height(MaterialTheme.spacing.large24))
            }
        }
        Spacer(Modifier.height(MaterialTheme.spacing.large24))
        if(hasAdminAccess){
            ChildProfileActions(
                onVaccinationTableClick = onVaccinationTableClick,
                onAppointmentsClick = onAppointmentsClick,
                onPrescriptionsClick = onPrescriptionsClick,
                onMedicalRecordsClick = onMedicalRecordsClick,
                colors = colors
            )
        }
    }
}

@Preview
@Composable
fun ChildProfileCardPreview() {
    Hospital_AutomationTheme {
        Surface{
            ->
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
                dateOfBirth = LocalDate.now(),
                gender = Gender.MALE,
                guardiansNumber = 2,
                onVaccinationTableClick = {},
                onAppointmentsClick = {},
                onPrescriptionsClick = {},
                onMedicalRecordsClick = {},
                hasAdminAccess = true,
                lastVaccination = lastVaccination,
                lastAppointment = lastAppointment,
                nextVaccination = nextVaccination,
                onAppointmentItemClick = {},
            )
        }
    }
}
private val lastVaccination = LastVaccineData(
    id = 1,
    date = LocalDate.of(2024,3,30)
)
private val nextVaccination = NextVaccineData(
    id = 2,
    date = LocalDate.of(2025,3,30)
)
private val lastAppointment = LastAppointmentData(
    id = 3 ,
    date = LocalDate.of(2025,3,30)
)