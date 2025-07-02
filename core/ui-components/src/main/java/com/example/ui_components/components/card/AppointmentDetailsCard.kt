package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.ext.toAppropriateFormat
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.helper.ext.toCapitalized
import com.example.ui.fake.createSampleAppointments
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.tag.OutlinedTagItem

@Composable
fun AppointmentDetailsCard(
    appointment: AppointmentData,
    onDepartmentItemClick: () -> Unit,
    onAppointmentTypeTagClick: () -> Unit,
    modifier: Modifier = Modifier,
    onVaccineItemClick: (() -> Unit)? = null,
) {
    val detailsItemModifier = Modifier.fillMaxWidth()

    val statusIcon = when (appointment.state) {
        AppointmentState.PASSED -> {
            AppIcons.Outlined.checkWithBorder
        }

        AppointmentState.UPCOMMING, AppointmentState.PENDING -> {
            AppIcons.Outlined.clock
        }

        else -> {
            AppIcons.Outlined.error
        }
    }

    val (statusIconColor, statusBackgroundColor) =
        if (appointment.state != AppointmentState.MISSED) {
            Pair(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
            )
        } else {
            Pair(
                MaterialTheme.colorScheme.error,
                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f)
            )
        }


    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                top = MaterialTheme.spacing.medium16,
            )
    ) {
        OutlinedTagItem(
            text = appointment.appointmentType.name,
            onClick = onAppointmentTypeTagClick,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
        DetailsItem(
            iconRes = AppIcons.Outlined.department,
            title = stringResource(id = R.string.department),
            description = appointment.clinic.name,
            modifier = detailsItemModifier,
            onClick = onDepartmentItemClick,
        )
        appointment.vaccine?.let {
            DetailsItem(
                iconRes = AppIcons.Outlined.syringe,
                title = stringResource(id = R.string.vaccine),
                description = it.name,
                modifier = detailsItemModifier,
                onClick = onVaccineItemClick,
            )
        }

        DetailsItem(
            iconRes = AppIcons.Outlined.specificDate,
            title = stringResource(id = R.string.date_and_time),
            description = appointment.dateTime.toAppropriateFormat(),
            modifier = detailsItemModifier,
        )
        appointment.vaccine?.let {
            DetailsItem(
                iconRes = AppIcons.Outlined.guardian,
                title = stringResource(id = R.string.booked_by),
                description = appointment.fullName,
                modifier = detailsItemModifier,
                onClick = onVaccineItemClick,
            )
        }

        DetailsItem(
            iconRes = statusIcon,
            title = stringResource(id = R.string.status),
            description = appointment.state.name.toCapitalized(),
            modifier = detailsItemModifier,
            iconColor = statusIconColor,
            iconBackgroundColor = statusBackgroundColor,
        )
        appointment.recommendedVisitDate?.let {
            DetailsItem(
                iconRes = AppIcons.Outlined.upcomingEvent,
                title = stringResource(id = R.string.next_recommended_visit),
                description = it.toAppropriateFormat(),
                modifier = detailsItemModifier,
            )
        }
        appointment.recommendedVisitNote?.let {
            DetailsItem(
                iconRes = AppIcons.Outlined.note,
                title = stringResource(id = R.string.note),
                description = it,
                modifier = detailsItemModifier,
                isMultipleLines = true,
            )
        }
        DetailsItem(
            iconRes = AppIcons.Outlined.medicalDiagnosis,
            title = stringResource(id = R.string.medical_diagnosis),
            description = appointment.medicalDiagnosis,
            modifier = detailsItemModifier,
            isMultipleLines = true,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun UpcomingOrPendingAppointmentDetailsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            AppointmentDetailsCard(
                appointment = createSampleAppointments()[0],
                onDepartmentItemClick = {},
                onAppointmentTypeTagClick = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PassedAppointmentDetailsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            AppointmentDetailsCard(
                appointment = createSampleAppointments()[1],
                onDepartmentItemClick = {},
                onAppointmentTypeTagClick = {},
                onVaccineItemClick = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MissedAppointmentDetailsCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            AppointmentDetailsCard(
                appointment = createSampleAppointments()[2],
                onDepartmentItemClick = {},
                onAppointmentTypeTagClick = {},
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}

