package com.example.doctor_profile.main

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
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.list.DoctorProfileActionsCard
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
            uiActions.clearToastMessage()
        }
    }

    LaunchedEffect(uiState.isLoggedOutSuccessfully) {
        if (uiState.isLoggedOutSuccessfully) {
            uiActions.navigateToLoginScreen()
        }
    }

    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.network_error),
        description = uiState.errorDialogText?.asString() ?: "",
        onConfirm = { uiActions.hideErrorDialog() },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
        text = uiState.loadingDialogText?.asString()
    )

    val isActionsItemsEnabled = uiState.doctorInfo?.profile?.acceptedBy != null

    val isResigned = uiState.doctorInfo?.profile?.isResigned == true
    val isAccepted = uiState.doctorInfo?.profile?.acceptedBy != null
    val isSuspended = uiState.doctorInfo?.profile?.isSuspended == true
    val isSuspendedByHimself = (isSuspended &&
            uiState.doctorInfo.profile.suspendedBy == uiState.doctorInfo.profile.userId)
    val showDeactivationItem = !isResigned && isAccepted && (isSuspendedByHimself || !isSuspended)


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
                                specialty = uiState.doctorInfo.profile.specialty?:"",
                                departmentName = uiState.doctorInfo.profile.department?:"",
                                onDepartmentItemClick = {
                                    uiActions.navigateToDepartmentDetailsScreen()
                                },
                                currentStatus = uiState.doctorInfo.profile.currentStatus,
                                workDays = uiState.doctorInfo.profile.availabilitySchedule,
                                appointmentTypes = uiState.doctorInfo.profile.appointmentTypes,
                                onAppointmentTypeItemClick = {
                                    TODO()
//                                    uiActions.showAppointmentTypeDetails()
                                },
                                modifier = Modifier.fillMaxWidth(),
                            )
                            DoctorProfileActionsCard(
                                onAppointmentsHistoryClick = {
                                    uiActions.navigateToAppointmentsScreen()
                                },
                                isAppointmentsItemEnabled=isActionsItemsEnabled,
                                onPrescriptionsClick = {
                                    uiActions.navigateToPrescriptionsScreen()
                                },
                                isPrescriptionsItemEnabled=isActionsItemsEnabled,
                                onMedicalRecordsClick = {
                                    uiActions.navigateToMedicalRecordsScreen()
                                },
                                isMedicalRecordsItemEnabled=isActionsItemsEnabled,
                                onEmploymentHistoryClick = {
                                    uiActions.navigateToEmploymentHistoryScreen()
                                },
                                isEmploymentHistoryItemEnabled=isActionsItemsEnabled,
                                onDeactivateAccountClick = {
                                    uiActions.onDeactivateMyAccount()
                                },
                                showDeactivationItem=showDeactivationItem,
                                onLogoutClick = {
                                    uiActions.onLogout()
                                },
                                modifier = Modifier.fillMaxWidth(),
                            )
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

