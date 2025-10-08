package com.example.pharmacy_details.presentation

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
import com.example.ext.toAppropriateAddressFormat
import com.example.ext.toAppropriateNameFormat
import com.example.model.doctor.day_scedule.DoctorStatusChecker
import com.example.model.enums.ScreenState
import com.example.navigation.extesion.navigateToCallApp
import com.example.pharmacy_details.fake.createSamplePharmacyDetailsResponse
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.IllustrationCard
import com.example.ui_components.components.card.PharmacyDetailsCard
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.ConfirmationDialog
import com.example.ui_components.components.list.PharmacyProfileAdminActionsCard
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun PharmacyDetailsScreen(
    uiState: PharmacyDetailsUiState,
    uiActions: PharmacyDetailsUiActions,
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

    val isActionsItemsEnabled = uiState.pharmacyInfo?.userWithAddress?.acceptedBy != null

    val isResigned = uiState.pharmacyInfo?.userWithAddress?.isResigned == true
    val isAccepted = uiState.pharmacyInfo?.userWithAddress?.acceptedBy != null
    val isSuspended = uiState.pharmacyInfo?.isDeactivated == true
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
                uiState.pharmacyInfo != null
            ) {
                val name = uiState.pharmacyInfo.userWithAddress.fullName.toAppropriateNameFormat()
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
                            PharmacyDetailsCard(
                                name = name,
                                onNavigateUpButtonClick = {
                                    uiActions.navigateUp()
                                },
                                onEmailButtonClick = {
                                    uiActions.navigateToEmailApp(
                                        email = uiState.pharmacyInfo.userWithAddress.email,
                                        subject = subject
                                    )
                                },
                                onPhoneNumberButtonClick = {
                                    context.navigateToCallApp(
                                        phoneNumber = uiState.pharmacyInfo.phoneNumber
                                    )
                                },
                                phoneNumber = uiState.pharmacyInfo.phoneNumber,
                                profileImageUrl = uiState.pharmacyInfo.userWithAddress.imageUrl
                                    ?: "",
                                residentialAddress = uiState.pharmacyInfo.pharmacyAddress.toAppropriateAddressFormat(),
                                gender = uiState.pharmacyInfo.userWithAddress.gender,
                                email = uiState.pharmacyInfo.userWithAddress.email,
                                isResigned = uiState.pharmacyInfo.userWithAddress.isResigned,
                                isSuspended = isSuspended,
                                isAccepted = uiState.pharmacyInfo.userWithAddress.acceptedBy != null,
                                showNavigateUp = true,
                                pharmacyName = uiState.pharmacyInfo.phName,
                                currentStatus = DoctorStatusChecker.getDoctorStatus(uiState.pharmacyInfo.workDays),
                                workDays = uiState.pharmacyInfo.workDays,
                                modifier = Modifier.fillMaxWidth(),
                                pharmacyAddress = uiState.pharmacyInfo.pharmacyAddress.toAppropriateAddressFormat(),
                            )

                            when (uiState.pharmacyAccessType) {
                                PharmacyAccessType.ADMIN_ACCESS ->
                                    PharmacyProfileAdminActionsCard(
                                        onFulfilledPrescriptionsClick = {
                                            uiActions.navigateToFulfilledPrescriptionsScreen(
                                                uiState.pharmacyInfo.pharmacyId
                                            )
                                        },
                                        isFulfilledPrescriptionsItemEnabled = isActionsItemsEnabled,
                                        onMedicinesClick = {
                                            uiActions.navigateToMedicinesScreen(
                                                uiState.pharmacyInfo.pharmacyId
                                            )
                                        },
                                        isMedicinesItemEnabled = isActionsItemsEnabled,
                                        onContractHistoryClick = {
                                            uiActions.navigateToEmploymentHistoryScreen(
                                                uiState.pharmacyInfo.pharmacyId
                                            )
                                        },
                                        isContractHistoryItemEnabled = isActionsItemsEnabled,
                                        onDeactivateAccountClick = {
                                            uiActions.onDeactivateAccount()
                                        },
                                        onReactivateAccountClick = {
                                            uiActions.onReactivateAccount()
                                        },
                                        isAccountDeactivated = isSuspended,
                                        showDeactivationItem = showDeactivationItemInAdminRole,
                                        onStopPharmacyClick = {
                                        },
                                    )

                                PharmacyAccessType.NON_OWNER_ACCESS -> null
                                null -> null
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
            PharmacyDetailsScreen(
                uiState = PharmacyDetailsUiState(
                    pharmacyInfo = createSamplePharmacyDetailsResponse()[0],
                    profileScreenState = ScreenState.SUCCESS,
                ),
                uiActions = PharmacyDetailsUiActions(
                    navigationActions = mockPharmacyDetailsNavigationUiActions(),
                    businessActions = mockPharmacyDetailsBusinessUiActions(),
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
            PharmacyDetailsScreen(
                uiState = PharmacyDetailsUiState(
                    pharmacyInfo = createSamplePharmacyDetailsResponse()[1],
                    profileScreenState = ScreenState.SUCCESS,
                ),
                uiActions = PharmacyDetailsUiActions(
                    navigationActions = mockPharmacyDetailsNavigationUiActions(),
                    businessActions = mockPharmacyDetailsBusinessUiActions(),
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
            PharmacyDetailsScreen(
                uiState = PharmacyDetailsUiState(
                    profileScreenState = ScreenState.ERROR,
                ),
                uiActions = PharmacyDetailsUiActions(
                    navigationActions = mockPharmacyDetailsNavigationUiActions(),
                    businessActions = mockPharmacyDetailsBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}

