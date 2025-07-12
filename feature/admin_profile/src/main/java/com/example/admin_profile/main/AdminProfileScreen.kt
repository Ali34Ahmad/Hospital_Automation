package com.example.admin_profile.main

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
import com.example.fake.createSampleAdminProfile
import com.example.ui_components.components.card.IllustrationCard
import com.example.model.enums.Gender
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.profile.AdminProfileCard
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun AdminProfileScreen(
    uiState: AdminProfileUiState,
    uiActions: AdminProfileUiActions,
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

    LaunchedEffect(uiState.adminId) {
        if (uiState.adminId != null) {
            uiActions.onGetAdminProfile()
        }
    }

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            if (uiState.screenState == ScreenState.ERROR ||
                uiState.screenState == ScreenState.LOADING
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

            if (uiState.screenState == ScreenState.LOADING) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize)
                    )
                }
            } else if (uiState.screenState == ScreenState.ERROR) {
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
            if (uiState.userInfo != null &&
                uiState.screenState == ScreenState.SUCCESS
            ) {
                val name = uiState.userInfo.admin.fullName.toAppropriateNameFormat()
                val subject = stringResource(R.string.medicare)
                PullToRefreshBox(
                    refreshing = uiState.isRefreshing,
                    onRefresh = { uiActions.onRefresh() }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
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
                                adminImageUrl = uiState.userInfo.admin.imageUrl,
                                address = uiState.userInfo.admin.address.toAppropriateAddressFormat(),
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
}

@DarkAndLightModePreview
@Composable
fun LoginScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            AdminProfileScreen(
                uiState = AdminProfileUiState(
                    userInfo = createSampleAdminProfile(),
                    screenState = ScreenState.SUCCESS,
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
                    screenState = ScreenState.ERROR
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

