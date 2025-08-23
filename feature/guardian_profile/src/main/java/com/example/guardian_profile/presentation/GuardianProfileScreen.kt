package com.example.guardian_profile.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.model.enums.Gender
import com.example.model.enums.ScreenState
import com.example.navigation.extesion.navigateToCallApp
import com.example.navigation.extesion.navigateToEmailApp
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.SendingDataBottomBar
import com.example.ui_components.components.buttons.HospitalAutomationButton
import com.example.ui_components.components.card.custom.CustomGuardianProfileCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.dialog.WarningDialogWithInputField
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.custom.GuardianProfileTopBar

@Composable
fun GuardianProfileScreen(
    viewModel: GuardianProfileViewModel,
    navigationActions: GuardianProfileNavigationAction,
    modifier: Modifier = Modifier
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    GuardianProfileScreen(
        uiState = uiState.value,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier
    )
}
@Composable
fun GuardianProfileScreen(
    uiState: GuardianProfileUIState,
    navigationActions: GuardianProfileNavigationAction,
    onAction: (GuardianProfileActions)-> Unit,
    modifier: Modifier = Modifier
) {
    val defaultReason = stringResource(R.string.no_detials_available)
    val context = LocalContext.current
    val toastMessage = uiState.toastMessage
    LaunchedEffect(toastMessage) {
        if(toastMessage != null){
            Toast.makeText(context, toastMessage.asString(context), Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            if(uiState.screenState != ScreenState.SUCCESS){
                GuardianProfileTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    onNavigateUp = navigationActions::navigateUp,
                )
            }
        },
        bottomBar = {
            if(uiState.screenState == ScreenState.SUCCESS){
                AnimatedContent(uiState.userProfileMode) { state ->
                    when (state) {
                        UserProfileMode.SET_AS_GUARDIAN -> {
                            SendingDataBottomBar(
                                onSuccess = {

                                },
                                onButtonClick = {
                                    onAction(
                                        GuardianProfileActions.SetAsGuardian
                                    )
                                },
                                state = uiState.bottomBarState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium16),
                                text = stringResource(R.string.set_as_guardian),
                            )
                        }
                        UserProfileMode.ADD_CHILD -> {
                            HospitalAutomationButton(
                                onClick = {
                                    navigationActions.navigateToAddChild(uiState.guardianId)
                                },
                                text = stringResource(R.string.add_child),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .navigationBarsPadding()
                                    .padding(MaterialTheme.spacing.medium16),
                            )
                        }
                        else -> Unit
                    }
                }
            }
        }
    ) { innerPadding ->
            AnimatedContent(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(
                        MaterialTheme.spacing.medium16
                    ),
                targetState = uiState.screenState
            ) {state->
                when(state){
                    ScreenState.IDLE -> Unit
                    ScreenState.LOADING ->{
                        FetchingDataItem(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    ScreenState.ERROR ->{
                        PullToRefreshColumn(
                            refreshing = uiState.isRefreshing,
                            modifier = Modifier.fillMaxSize(),
                            onRefresh = {
                                onAction(GuardianProfileActions.Refresh)
                            },
                            verticalArrangement = Arrangement.Center
                        ){
                            ErrorComponent(
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                    ScreenState.SUCCESS -> {
                        uiState.guardianData?.let { data->
                            data.run {
                                PullToRefreshColumn(
                                    refreshing = uiState.isRefreshing,
                                    onRefresh = {
                                        onAction(GuardianProfileActions.Refresh)
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    CustomGuardianProfileCard(
                                        fullName = fullName,
                                        phoneNumber = phoneNumber,
                                        imageUrl = imgUrl ?: "",
                                        address = fullAddress,
                                        gender = gender ?: Gender.MALE.name.lowercase(),
                                        onNavigateUp = navigationActions::navigateUp,
                                        onPhoneNumberButtonClick = {
                                            context.navigateToCallApp(phoneNumber)
                                        },
                                        onEmailButtonClick = {
                                            context.navigateToEmailApp(email)
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        onChildrenButtonClick = {
                                            navigationActions.navigateToChildren(userId)
                                        },
                                        isActive = !isSuspended,
                                        hasAdminAccess = uiState.hasAdminAccess,
                                        onAppointmentsClick = {
                                            navigationActions.navigateToAppointments(
                                                guardianId = userId
                                            )
                                        },
                                        onPrescriptionsClick = {
                                            navigationActions.navigateToPrescriptions(
                                                guardianId = userId
                                            )
                                        },
                                        onMedicalRecordClick = {
                                            navigationActions.navigateToMedicalRecord(
                                                guardianId = userId
                                            )
                                        },
                                        onSuspendUserAccountClick = {
                                            onAction(
                                                GuardianProfileActions.ShowWarningDialog
                                            )
                                        },
                                        onReactivateUserAccount = {
                                            onAction(
                                                GuardianProfileActions.ReactivateAccount
                                            )
                                        },
                                        onlyCommunicationInfo = uiState.userProfileMode == UserProfileMode.ONLY_COMMUNICATION_INFO,
                                    )

                                    //Show warning dialog when clicking on deactivation button
                                    if (uiState.isWarningDialogShown) {
                                        WarningDialogWithInputField(
                                            title = stringResource(R.string.deactivation_msg_title),
                                            subtitle = stringResource(R.string.deactivation_msg_subtitle),
                                            text = uiState.deactivationReason,
                                            onTextValueChange = {
                                                onAction(
                                                    GuardianProfileActions.UpdateDeactivationReason(it)
                                                )
                                            },
                                            onDismissRequest = {
                                                onAction(GuardianProfileActions.HideWarningDialog)
                                                onAction(
                                                    GuardianProfileActions.ClearDeactivationReason
                                                )
                                            },
                                            onConfirm = {
                                                if(uiState.deactivationReason.isBlank()){
                                                    onAction(
                                                        GuardianProfileActions.UpdateDeactivationReason(
                                                            defaultReason
                                                        )
                                                    )
                                                }
                                                onAction(GuardianProfileActions.HideWarningDialog)
                                                onAction(
                                                    GuardianProfileActions.DeactivateAccount
                                                )
                                            },
                                            onDismiss = {
                                                onAction(GuardianProfileActions.HideWarningDialog)
                                            },
                                            enableConfirmButton = uiState.isValidInput == true,
                                            hasError = uiState.isValidInput == false,
                                            isRequired = true,
                                        )
                                    }
                                    LoadingDialog(
                                        title = stringResource(R.string.please_wait),
                                        subtitle = stringResource(R.string.fetching_data_subtitle),
                                        showDialog = uiState.isLoadingDialogShown,
                                    )
                                }
                            }
                        }

                    }
                }
            }
    }
}