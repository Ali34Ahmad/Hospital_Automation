package com.example.admin_profile.main

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
import com.example.ext.toAppropriateFormat
import com.example.fake.createSampleAdminProfile
import com.example.hospital_automation.core.components.card.IllustrationCard
import com.example.model.enums.Gender
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.profile.AdminProfileCard
import com.example.utility.network.NetworkError

@Composable
fun AdminProfileScreen(
    uiState: AdminProfileUiState,
    uiActions: AdminProfileUiActions,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.adminId) {
        if (uiState.adminId != null) {
            uiActions.onRefreshProfile()
        }
    }


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
                val name = uiState.userInfo.admin.fullName.toAppropriateFormat()
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
                        AdminProfileCard(
                            name = name,
                            onNavigateUpButtonClick = {
                                uiActions.navigateUp()
                            },
                            onEmailButtonClick = {
                                uiActions.navigateToEmail(
                                    context = context,
                                    email = uiState.userInfo.admin.email,
                                    subject = subject
                                )
                            },
                            onPhoneNumberButtonClick = {
                                uiActions.navigateToCallApp(
                                    context = context,
                                    phoneNumber = uiState.userInfo.admin.phoneNumber
                                )
                            },
                            phoneNumber = uiState.userInfo.admin.phoneNumber,
                            adminImageUrl = "uiState.userInfo.admin.imageUrl ?: ",
                            address = uiState.userInfo.admin.address.toAppropriateFormat(),
                            gender = uiState.userInfo.admin.gender ?: Gender.MALE,
                            email = uiState.userInfo.admin.email,
                            isResigned = uiState.userInfo.admin.isResigned,
                            isSuspended = uiState.userInfo.admin.isSuspended,
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
            AdminProfileScreen(
                uiState = AdminProfileUiState(
                    userInfo = createSampleAdminProfile()
                ),
                uiActions = AdminProfileUiActions(
                    navigationActions = mockAdminProfileNavigationUiActions(),
                    businessActions = mockAdminProfileBusinessUiActions(),
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
            AdminProfileScreen(
                uiState = AdminProfileUiState(
                    fetchingProfileError = NetworkError.UNKNOWN,
                    isLoadingProfile = false,
                ),
                uiActions = AdminProfileUiActions(
                    navigationActions = mockAdminProfileNavigationUiActions(),
                    businessActions = mockAdminProfileBusinessUiActions(),
                ),
                modifier = Modifier,
            )
        }
    }
}

