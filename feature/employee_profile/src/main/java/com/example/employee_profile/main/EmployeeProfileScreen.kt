package com.example.employee_profile.main

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
import com.example.employee_profile.navigation.EmployeeProfilePreviousDestination
import com.example.ext.toAppropriateFormat
import com.example.fake.createSampleEmployeeProfileResponse
import com.example.hospital_automation.core.components.card.IllustrationCard
import com.example.utility.network.NetworkError
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.EmployeeProfileActionsCard
import com.example.ui_components.components.card.EmployeeProfileCard
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.MessageDialog
import com.example.model.enums.Gender

@Composable
fun EmployeeProfileScreen(
    uiState: EmployeeProfileUiState,
    uiActions: EmployeeProfileUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.isLoggedOutSuccessfully) {
        if (uiState.isLoggedOutSuccessfully) {
            uiActions.navigateToLoginScreen()
        }
    }

    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.network_error),
        description = uiState.errorDialogText,
        onConfirm = { uiActions.hideErrorDialog() },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
        text = uiState.loadingDialogText
    )

    val isAddedChildrenEnabled = uiState.userInfo?.profile?.acceptedBy != null

    val isResigned = uiState.userInfo?.profile?.isResigned == true
    val isAccepted = uiState.userInfo?.profile?.acceptedBy != null
    val isSuspended = uiState.userInfo?.profile?.isSuspended == true
    val isSuspendedByHimself = (isSuspended &&
            uiState.userInfo.profile.suspendedBy == uiState.userInfo.profile.userId)
    val showDeactivationItem = !isResigned && isAccepted && (isSuspendedByHimself || !isSuspended)


    val context = LocalContext.current

    val scrollState = rememberScrollState()
    Scaffold { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {

            if (uiState.isLoadingProfile) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize)
                    )
                }
            } else if (uiState.fetchingProfileError != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
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
            if (uiState.userInfo != null) {
                val name = uiState.userInfo.profile.fullName.toAppropriateFormat()
                val subject = stringResource(R.string.medicare)
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState),
                ) {
                    Column(
                        modifier = modifier.padding(
                            MaterialTheme.spacing.medium16
                        ),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large24),
                    ) {
                        EmployeeProfileCard(
                            name = name,
                            onNavigateUpButtonClick = {
                                uiActions.navigateUp()
                            },
                            onEmailButtonClick = {
                                uiActions.navigateToEmail(
                                    context = context,
                                    email = uiState.userInfo.profile.email,
                                    subject = subject
                                )
                            },
                            onPhoneNumberButtonClick = {
                                uiActions.navigateToCallApp(
                                    context = context,
                                    phoneNumber = uiState.userInfo.profile.phoneNumber
                                )
                            },
                            phoneNumber = uiState.userInfo.profile.phoneNumber,
                            profileImageUrl = uiState.userInfo.profile.imageUrl ?: "",
                            address = uiState.userInfo.profile.address.toAppropriateFormat(),
                            gender = uiState.userInfo.profile.gender ?: Gender.MALE,
                            email = uiState.userInfo.profile.email,
                            isResigned = uiState.userInfo.profile.isResigned,
                            isSuspended = uiState.userInfo.profile.isSuspended,
                            isAccepted = uiState.userInfo.profile.acceptedBy != null,
                            showNavigateUp = true
                        )
                        EmployeeProfileActionsCard(
                            onAddedChildrenItemClick = {
                                uiActions.navigateToAddedChildrenScreen()
                            },
                            isAddedChildrenEnabled = isAddedChildrenEnabled,
                            onEmploymentHistoryItemClick = {
                                uiActions.navigateToEmploymentHistoryScreen()
                            },
                            onDeactivateAccountItemClick = {
                                uiActions.onDeactivateMyAccount()
                            },
                            onReactivateAccountItemClick = {
                                uiActions.onReactivateMyAccount()
                            },
                            showDeactivateMyAccountItem = showDeactivationItem,
                            isAccountDeactivated = isSuspended,
                            onLogoutItemClick = {
                                uiActions.onLogout()
                            },
                        )
                    }
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun LoginScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            EmployeeProfileScreen(
                uiState = EmployeeProfileUiState(
                    userInfo = createSampleEmployeeProfileResponse()
                ),
                uiActions = EmployeeProfileUiActions(
                    navigationActions = mockEmployeeProfileNavigationUiActions(),
                    businessActions = mockEmployeeProfileBusinessUiActions(),
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
            EmployeeProfileScreen(
                uiState = EmployeeProfileUiState(
                    fetchingProfileError = NetworkError.UNKNOWN,
                    isLoadingProfile = false,
                ),
                uiActions = EmployeeProfileUiActions(
                    navigationActions = mockEmployeeProfileNavigationUiActions(),
                    businessActions = mockEmployeeProfileBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}

