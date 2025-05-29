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
import com.example.model.employment_history.UserReference
import com.example.model.user.FullName
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.items.SubDetailsItem
import java.time.LocalDate

@Composable
fun EmploymentHistoryCard(
    employmentDate: LocalDate?,
    acceptedBy: UserReference?,
    suspendedBy: UserReference?,
    resignationDate: LocalDate?,
    resignedBy: UserReference?,
    onDocumentsItemClick: () -> Unit,
    onAcceptedByItemClick: () -> Unit,
    onResignedByItemClick: () -> Unit,
    onSuspendedByItemClick: () -> Unit,
    filesNumber: Int,
    modifier: Modifier = Modifier,
) {
    val detailsItemModifier = Modifier.fillMaxWidth()

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(
                bottom = MaterialTheme.spacing.large24,
            )
    ) {
        employmentDate?.let {
            DetailsItem(
                iconRes = AppIcons.Outlined.workBriefcase,
                title = stringResource(id = R.string.employment_date),
                description = employmentDate.toAppropriateFormat(),
                modifier = detailsItemModifier,
            )
        }
        acceptedBy?.userId?.let{
            DetailsItem(
                iconRes = AppIcons.Outlined.admin,
                title = stringResource(id = R.string.accepted_by),
                description = acceptedBy.fullName.toAppropriateFormat(),
                modifier = detailsItemModifier,
                onClick = onAcceptedByItemClick,
            )
        }

        suspendedBy?.userId?.let{
            DetailsItem(
                iconRes = AppIcons.Outlined.admin,
                title = stringResource(id = R.string.suspended_by),
                description = suspendedBy.fullName.toAppropriateFormat(),
                modifier = detailsItemModifier,
                onClick = onSuspendedByItemClick,
            )
        }

        resignationDate?.let {
            DetailsItem(
                iconRes = AppIcons.Outlined.finishLineFlag,
                title = stringResource(id = R.string.date_and_time),
                description = resignationDate.toAppropriateFormat(),
                modifier = detailsItemModifier,
            )
        }

        resignedBy?.userId?.let{
            DetailsItem(
                iconRes = AppIcons.Outlined.admin,
                title = stringResource(id = R.string.resigned_by),
                description = resignedBy.fullName.toAppropriateFormat(),
                modifier = detailsItemModifier,
                onClick = onResignedByItemClick,
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small12))
        SubDetailsItem(
            title = stringResource(R.string.documents),
            description = stringResource(R.string.files_number, filesNumber),
            onClick = onDocumentsItemClick,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium16)
                .fillMaxWidth(0.3f),
        )
    }
}

@DarkAndLightModePreview
@Composable
fun EmploymentHistoryCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmploymentHistoryCard(
                employmentDate = LocalDate.of(2022, 8, 15),
                acceptedBy = UserReference(1, FullName("Jane" ,null,"Smith")),
                resignationDate = LocalDate.of(2024, 7, 31),
                resignedBy = UserReference(1, FullName("Jane" ,null,"Smith")),
                onDocumentsItemClick = { },
                onAcceptedByItemClick = { },
                onResignedByItemClick = { },
                onSuspendedByItemClick = { },
                filesNumber = 3,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                suspendedBy = UserReference(1, FullName("Jane" ,null,"Smith")),
            )
        }
    }
}