package com.example.employment_history

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateFormat
import com.example.fake.createSampleEmploymentHistoryResponse
import com.example.hospital_automation.core.components.card.IllustrationCard
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.EmploymentHistoryCard
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun EmploymentHistoryScreen(
    uiState: EmploymentHistoryUiState,
    uiActions: EmploymentHistoryUiActions,
    modifier: Modifier = Modifier,
) {
    MessageDialog(
        showDialog = uiState.showErrorDialog,
        title = stringResource(R.string.network_error),
        description = uiState.errorDialogText,
        onConfirm = { uiActions.hideErrorDialog() },
        confirmButtonText = stringResource(R.string.ok),
        showCancelButton = false,
    )

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            val currentUserName =
                uiState.employmentHistory?.currentUser?.fullName?.toAppropriateFormat()

            HospitalAutomationTopBar(
                title = currentUserName?:"",
                onNavigationIconClick = { uiActions.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
                imageUrl = null,
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize)
                    )
                }
            } else if (uiState.fetchingHistoryError != null) {
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
            if (uiState.employmentHistory != null) {
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
                        EmploymentHistoryCard(
                            employmentDate = uiState.employmentHistory.currentUser.workStartDate,
                            acceptedBy = uiState.employmentHistory.acceptedBy,
                            suspendedBy = uiState.employmentHistory.suspendedBy,
                            resignationDate = uiState.employmentHistory.currentUser.workEndDate,
                            resignedBy = uiState.employmentHistory.resignedBy,
                            onDocumentsItemClick = {},
                            onAcceptedByItemClick = { uiActions.navigateToAcceptedByAdminProfileScreen() },
                            onResignedByItemClick = { uiActions.navigateToToResignedByAdminProfileScreen() },
                            onSuspendedByItemClick = { uiActions.navigateToToSuspendedByAdminProfileScreen() },
                            filesNumber = 1,
                            modifier = modifier.fillMaxWidth(),
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
            EmploymentHistoryScreen(
                uiState = EmploymentHistoryUiState(
                    employmentHistory = createSampleEmploymentHistoryResponse()
                ),
                uiActions = EmploymentHistoryUiActions(
                    navigationActions = mockEmploymentHistoryNavigationUiActions(),
                    businessActions = mockEmploymentHistoryBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}