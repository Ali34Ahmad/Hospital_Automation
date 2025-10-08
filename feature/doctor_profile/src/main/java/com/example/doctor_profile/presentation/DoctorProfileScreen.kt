package com.example.doctor_profile.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.doctor_profile.fake.createSampleDoctorProfileResponse
import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.ext.toAppropriateAddressFormat
import com.example.ext.toAppropriateNameFormat
import com.example.model.enums.Gender
import com.example.model.enums.ScreenState
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.DoctorProfileCard
import com.example.ui_components.components.card.IllustrationCard
import com.example.ui_components.components.dialog.DialogWithDescription
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.ConfirmationDialog
import com.example.ui_components.components.list.DoctorProfileAdminActionsCard
import com.example.ui_components.components.list.DoctorProfileOwnerActionsCard
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun DoctorProfileScreen(
    uiState: DoctorProfileUiState,
    uiActions: DoctorProfileUiActions,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let {
            Toast.makeText(
                context,
                it.asString(context),
                Toast.LENGTH_SHORT
            ).show()
            uiActions.onClearToastMessage()
        }
    }

    LaunchedEffect(uiState.isLoggedOutSuccessfully) {
        if (uiState.isLoggedOutSuccessfully) {
            uiActions.navigateToLoginScreen()
        }
    }

    ConfirmationDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.network_error),
        description = uiState.errorDialogText?.asString() ?: "",
        onConfirm = { uiActions.onHideErrorDialog() },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
        subtitle = uiState.loadingDialogText?.asString()
    )

    if (uiState.selectedAppointmentTypeIndex != null) {
        val appointmentType =
            uiState.doctorInfo?.profile?.appointmentTypes[uiState.selectedAppointmentTypeIndex]
        DialogWithDescription(
            onDismissRequest = {
                uiActions.onUpdateSelectedAppointmentTypeDialog(null)
            },
            onActionClick = {
                uiActions.onUpdateSelectedAppointmentTypeDialog(null)
            },
            title = appointmentType?.name + " " + "(${appointmentType?.standardDurationInMinutes})",
            subtitle = appointmentType?.description ?: "",
        )
    }

    val isActionsItemsEnabledInDoctorRole = uiState.doctorInfo?.profile?.acceptedBy != null &&
            uiState.doctorInfo.profile.resignedBy == null &&
            uiState.doctorInfo.profile.suspendedBy == null

    val isResigned = uiState.doctorInfo?.profile?.isResigned == true
    val isAccepted = uiState.doctorInfo?.profile?.acceptedBy != null
    val isSuspended = uiState.doctorInfo?.profile?.isSuspended == true
    val isSuspendedByHimself = (isSuspended &&
            uiState.doctorInfo.profile.suspendedBy == uiState.doctorInfo.profile.userId)
    val showDeactivationItemInDoctorRole =
        !isResigned && isAccepted && (isSuspendedByHimself || !isSuspended)

    val isAccessedByOwner = uiState.doctorInfo?.isAccessedByOwner
    val showActionsCardInDoctorRole = if (isAccessedByOwner == null)
        uiState.doctorProfileAccessType == DoctorProfileAccessType.TOKEN_ACCESS
    else
        isAccessedByOwner
                && (uiState.doctorProfileAccessType == DoctorProfileAccessType.OTHER_DOCTOR_ACCESS)

    val showDeactivationItemInAdminRole = !isResigned && isAccepted


    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            if (uiState.profileScreenState == ScreenState.ERROR ||
                uiState.profileScreenState == ScreenState.LOADING
            ) {
                HospitalAutomationTopBar(
                    title = "",
                    onNavigationIconClick = { uiActions.navigateUp() },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = AppIcons.Outlined.arrowBack,
                    imageUrl = null,
                )
            }
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {

            if (uiState.profileScreenState == ScreenState.LOADING) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize)
                    )
                }
            } else if (uiState.profileScreenState == ScreenState.ERROR) {
                PullToRefreshBox(
                    refreshing = uiState.isRefreshing,
                    onRefresh = { uiActions.onRefresh() }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        IllustrationCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MaterialTheme.spacing.medium16),
                            illustration = {
                                Image(
                                    painter = painterResource(R.drawable.ill_error),
                                    contentDescription = null,
                                    modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
                                )
                            },
                            title = stringResource(R.string.network_error),
                            description = stringResource(R.string.something_went_wrong),
                        )
                    }
                }
            }
            if (uiState.profileScreenState == ScreenState.SUCCESS &&
                uiState.doctorInfo != null
            ) {
                val name = uiState.doctorInfo.profile.fullName.toAppropriateNameFormat()
                val subject = stringResource(R.string.medicare)
                PullToRefreshBox(
                    refreshing = uiState.isRefreshing,
                    onRefresh = { uiActions.onRefresh() }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(
                            modifier = modifier.padding(
                                MaterialTheme.spacing.medium16
                            ),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large24),
                        ) {
                            DoctorProfileCard(
                                name = name,
                                onNavigateUpButtonClick = {
                                    uiActions.navigateUp()
                                },
                                onEmailButtonClick = {
                                    context.navigateToEmailApp(
                                        email = uiState.doctorInfo.profile.email,
                                        subject = subject
                                    )
                                },
                                onPhoneNumberButtonClick = {
                                    context.navigateToCallApp(
                                        phoneNumber = uiState.doctorInfo.profile.phoneNumber
                                    )
                                },
                                phoneNumber = uiState.doctorInfo.profile.phoneNumber,
                                profileImageUrl = uiState.doctorInfo.profile.imageUrl ?: "",
                                address = uiState.doctorInfo.profile.address.toAppropriateAddressFormat(),
                                gender = uiState.doctorInfo.profile.gender ?: Gender.MALE,
                                email = uiState.doctorInfo.profile.email,
                                isResigned = uiState.doctorInfo.profile.isResigned,
                                isSuspended = uiState.doctorInfo.profile.isSuspended,
                                isAccepted = uiState.doctorInfo.profile.acceptedBy != null,
                                showNavigateUp = true,
                                specialty = uiState.doctorInfo.profile.specialty ?: "",
                                departmentName = uiState.doctorInfo.profile.department ?: "",
                                onDepartmentItemClick = {
                                    uiActions.navigateToDepartmentDetailsScreen(
                                        uiState.doctorInfo.profile.clinicId ?: -1
                                    )
                                },
                                currentStatus = uiState.doctorInfo.profile.currentStatus,
                                workDays = uiState.doctorInfo.profile.availabilitySchedule,
                                appointmentTypes = uiState.doctorInfo.profile.appointmentTypes,
                                onAppointmentTypeItemClick = { index ->
                                    uiActions.onUpdateSelectedAppointmentTypeDialog(index)
                                },
                                modifier = Modifier.fillMaxWidth(),
                            )
                            when {
                                showActionsCardInDoctorRole ->
                                    DoctorProfileOwnerActionsCard(
                                        onAppointmentsHistoryClick = {
                                            uiActions.navigateToAppointmentsScreen(
                                                null,
                                                uiState.doctorInfo.profile.fullName.toAppropriateNameFormat(),
                                                uiState.doctorInfo.profile.specialty,
                                                uiState.doctorInfo.profile.imageUrl,
                                                )
                                        },
                                        isAppointmentsItemEnabled = isActionsItemsEnabledInDoctorRole,
                                        onPrescriptionsClick = {
                                            uiActions.navigateToPrescriptionsScreen(null)
                                        },
                                        isPrescriptionsItemEnabled = isActionsItemsEnabledInDoctorRole,
                                        onMedicalRecordsClick = {
                                            uiActions.navigateToMedicalRecordsScreen(null)
                                        },
                                        isMedicalRecordsItemEnabled = isActionsItemsEnabledInDoctorRole,
                                        onEmploymentHistoryClick = {
                                            uiActions.navigateToEmploymentHistoryScreen(null)
                                        },
                                        isEmploymentHistoryItemEnabled = isAccepted,
                                        onDeactivateAccountClick = {
                                            uiActions.onDeactivateAccount()
                                        },
                                        showDeactivationItem = showDeactivationItemInDoctorRole,
                                        onLogoutClick = {
                                            uiActions.onLogout()
                                        },
                                        isAccountDeactivated = isSuspended,
                                        modifier = Modifier.fillMaxWidth(),
                                        onReactivateAccountClick = {
                                            uiActions.onReactivateAccount()
                                        },
                                    )

                                uiState.doctorProfileAccessType == DoctorProfileAccessType.ADMIN_ACCESS ->
                                    DoctorProfileAdminActionsCard(
                                        onAppointmentsHistoryClick = {
                                            uiActions.navigateToAppointmentsScreen(
                                                uiState.doctorId,
                                                uiState.doctorInfo.profile.fullName.toAppropriateNameFormat(),
                                                uiState.doctorInfo.profile.specialty,
                                                uiState.doctorInfo.profile.imageUrl,
                                                )
                                        },
                                        isAppointmentsItemEnabled = isAccepted,
                                        onPrescriptionsClick = {
                                            uiActions.navigateToPrescriptionsScreen(uiState.doctorId)
                                        },
                                        isPrescriptionsItemEnabled = isAccepted,
                                        onMedicalRecordsClick = {
                                            uiActions.navigateToMedicalRecordsScreen(uiState.doctorId)
                                        },
                                        isMedicalRecordsItemEnabled = isAccepted,
                                        onEmploymentHistoryClick = {
                                            uiActions.navigateToEmploymentHistoryScreen(uiState.doctorId)
                                        },
                                        isEmploymentHistoryItemEnabled = isAccepted,
                                        onDeactivateAccountClick = {
                                            uiActions.onDeactivateAccount()
                                        },
                                        showDeactivationItem = showDeactivationItemInAdminRole,
                                        onResignClick = {
                                            uiActions.onResignDoctor()
                                        },
                                        showResignItem = !isResigned,
                                        modifier = Modifier.fillMaxWidth(),
                                        onReactivateAccountClick = {
                                            uiActions.onReactivateAccount()
                                        },
                                        isAccountDeactivated = isSuspended,
                                    )

                                else -> null
                            }
                        }
                    }
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileOwnerScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            DoctorProfileScreen(
                uiState = DoctorProfileUiState(
                    doctorInfo = createSampleDoctorProfileResponse()[0],
                    profileScreenState = ScreenState.SUCCESS,
                ),
                uiActions = DoctorProfileUiActions(
                    navigationActions = mockDoctorProfileNavigationUiActions(),
                    businessActions = mockDoctorProfileBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun EmployeeProfileAnotherEmployeeScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            DoctorProfileScreen(
                uiState = DoctorProfileUiState(
                    doctorInfo = createSampleDoctorProfileResponse()[1],
                    profileScreenState = ScreenState.SUCCESS,
                ),
                uiActions = DoctorProfileUiActions(
                    navigationActions = mockDoctorProfileNavigationUiActions(),
                    businessActions = mockDoctorProfileBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun LoginScreenErrorFetchingProfilePreview() {
    Hospital_AutomationTheme {
        Surface {
            DoctorProfileScreen(
                uiState = DoctorProfileUiState(
                    profileScreenState = ScreenState.ERROR,
                ),
                uiActions = DoctorProfileUiActions(
                    navigationActions = mockDoctorProfileNavigationUiActions(),
                    businessActions = mockDoctorProfileBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}

