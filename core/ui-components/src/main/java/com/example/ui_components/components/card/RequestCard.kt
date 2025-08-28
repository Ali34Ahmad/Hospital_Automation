package com.example.ui_components.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.helper.ext.capitalFirstChar
import com.example.model.work_request.ClinicMainInfo
import com.example.model.work_request.RequestState
import com.example.model.work_request.RequestType
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalColorScheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.HospitalAutomationTextButton
import com.example.ui_components.components.items.DetailsItem
import com.example.ui_components.components.network_image.NetworkImage
import com.example.ui_components.components.network_image.NetworkImageLoader
import com.example.ui_components.components.network_image.SmallNetworkImageError
import com.example.ui_components.components.tag.FilledTagItem

@Composable
fun RequestCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(MaterialTheme.spacing.small8),
    onClick: (requestType: RequestType) -> Unit,
    requestType: RequestType,
    requestState: RequestState,
    userProfileImageUrl: String,
    username: String,
    requestId: Int,
    clinicMainInfo: ClinicMainInfo?,
    onAcceptButton: (requestId: Int) -> Unit,
    onRejectButton: (requestId: Int) -> Unit,
    onProfileItemClick: () -> Unit,
) {
    val tagTextColor = when (requestType) {
        RequestType.EMPLOYEE -> MaterialTheme.colorScheme.tertiary
        RequestType.DOCTOR -> MaterialTheme.additionalColorScheme.onPrimaryContainerBlue
        RequestType.PHARMACIST -> MaterialTheme.colorScheme.secondary
    }

    val tagBackgroundColor = when (requestType) {
        RequestType.EMPLOYEE -> MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
        RequestType.DOCTOR -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
        RequestType.PHARMACIST -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
    }

    Column(
        modifier = modifier
            .clip(shape)
            .background(color = MaterialTheme.colorScheme.background, shape)
            .clickable { onClick(requestType) }
            .padding(
                MaterialTheme.spacing.medium16
            )
    ) {
        FilledTagItem(
            text = requestType.toString().capitalFirstChar(),
            textColor = tagTextColor,
            backgroundColor = tagBackgroundColor
        )

        Spacer(Modifier.height(MaterialTheme.spacing.medium16))

        clinicMainInfo?.let {
            DetailsItem(
                iconRes = AppIcons.Outlined.department,
                title = stringResource(R.string.department),
                description = clinicMainInfo.name,
            )
        }
        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NetworkImage(
                    model = userProfileImageUrl,
                    modifier = Modifier
                        .size(
                            MaterialTheme.sizing.medium32
                        )
                        .clip(CircleShape)
                        .clickable(onClick = onProfileItemClick),
                    contentScale = ContentScale.Crop,
                    errorCompose = {
                        SmallNetworkImageError()
                    },
                    loading = {
                        NetworkImageLoader(
                            modifier = Modifier
                                .size(
                                    MaterialTheme.sizing.medium32
                                )
                                .clip(CircleShape)
                        )
                    }
                )
                Spacer(Modifier.width(MaterialTheme.spacing.small8))
                Text(
                    modifier = Modifier
                        .clickable(onClick = onProfileItemClick),
                    text = username,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            when (requestState) {
                RequestState.PENDING -> Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HospitalAutomationTextButton(
                        onClick = { onRejectButton(requestId) },
                        text = R.string.reject,
                        textColor = MaterialTheme.colorScheme.error,
                    )
                    Spacer(Modifier.height(MaterialTheme.spacing.small8))
                    HospitalAutomationTextButton(
                        onClick = { onAcceptButton(requestId) },
                        text = R.string.accept,
                    )
                }

                RequestState.ACCEPTED -> Text(
                    text = stringResource(R.string.accepted),
                    color = MaterialTheme.additionalColorScheme.green,
                    style = MaterialTheme.typography.bodySmall,
                )

                RequestState.REJECTED -> Text(
                    text = stringResource(R.string.rejected),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )

            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RequestCardEmployeePreview() {
    Hospital_AutomationTheme {
        Surface {
            RequestCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                requestType = RequestType.EMPLOYEE,
                userProfileImageUrl = "",
                username = "Ali Ahmad",
                onAcceptButton = {},
                onRejectButton = {},
                onProfileItemClick = {},
                requestId = 1,
                requestState = RequestState.PENDING,
                clinicMainInfo = null
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RequestCardDoctorPreview() {
    Hospital_AutomationTheme {
        Surface {
            RequestCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                requestType = RequestType.DOCTOR,
                userProfileImageUrl = "",
                username = "Ali Ahmad",
                onAcceptButton = {},
                onRejectButton = {},
                onProfileItemClick = {},
                requestId = 1,
                requestState = RequestState.PENDING,
                clinicMainInfo = ClinicMainInfo(clinicId = 1, name = "Vaccines Department")
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RequestCardPharmacyPreview() {
    Hospital_AutomationTheme {
        Surface {
            RequestCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                requestType = RequestType.PHARMACIST,
                userProfileImageUrl = "",
                username = "Ali Ahmad",
                onAcceptButton = {},
                onRejectButton = {},
                onProfileItemClick = {},
                requestId = 1,
                requestState = RequestState.PENDING,
                clinicMainInfo = null
            )
        }
    }
}

