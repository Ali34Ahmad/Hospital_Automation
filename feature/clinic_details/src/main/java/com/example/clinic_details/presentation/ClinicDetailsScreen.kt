package com.example.clinic_details.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.constants.icons.AppIcons
import com.example.model.Department
import com.example.model.Doctor
import com.example.model.Service
import com.example.model.User
import com.example.model.doctor.day_scedule.DoctorStatusChecker
import com.example.model.enums.DoctorStatus
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.ClinicDetailsBottomBar
import com.example.ui_components.components.card.DepartmentDetailsCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.dialog.DialogWithDescription
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn


@Composable
fun ClinicDetailsScreen(
    navigationActions: ClinicNavigationAction,
    viewModel: ClinicDetailsViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    ClinicDetailsScreen(
        uiState = uiState.value,
        onAction = viewModel::onAction,
        navigationAction = navigationActions,
        modifier = modifier
    )
}

@Composable
fun ClinicDetailsScreen(
    uiState: ClinicDetailsUIState,
    navigationAction: ClinicNavigationAction,
    onAction: (ClinicDetailsUIAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val message = uiState.toastMessage?.asString()
    LaunchedEffect(message) {
        if(message!=null){
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
        }
        onAction(ClinicDetailsUIAction.ClearToast)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier,
        topBar = {
            HospitalAutomationTopBar(
                title = stringResource(R.string.clinic_details),
                onNavigationIconClick = navigationAction::navigateUp,
                modifier = Modifier,
                navigationIcon = AppIcons.Outlined.arrowBack,
            )
        },
        bottomBar = {
           ClinicDetailsBottomBar(
                    title = stringResource(R.string.send_request),
                    state = uiState.sendRequestState,
                    onClick = {
                        onAction(
                            ClinicDetailsUIAction.SendRequest
                        )
                    },
                    onSuccess = {
                        navigationAction.navigateToScheduleScreen()
                    },
                    modifier = Modifier.padding(MaterialTheme.spacing.medium16)
                )
        }
    ) { innerPadding->
        AnimatedContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    MaterialTheme.spacing.medium16
                ),
            targetState = uiState.screenState
        ){state->
            when (state) {
                ScreenState.IDLE -> Unit
                ScreenState.LOADING -> FetchingDataItem(Modifier.fillMaxSize())
                ScreenState.ERROR -> {
                    PullToRefreshColumn(
                        refreshing = uiState.isRefreshing,
                        modifier = Modifier.fillMaxSize(),
                        onRefresh = {
                            onAction(ClinicDetailsUIAction.Refresh)
                        },
                        verticalArrangement = Arrangement.Center
                    ){
                        ErrorComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                ScreenState.SUCCESS ->{
                    uiState.clinic?.run {
                        PullToRefreshColumn(
                            refreshing = uiState.isRefreshing,
                            onRefresh = {
                                onAction(ClinicDetailsUIAction.Refresh)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ){
                            DepartmentDetailsCard(
                                department = Department(
                                    id = clinicId,
                                    name = name,
                                    workDays = workDays,
                                    activeDoctors = activeDoctors.map { Doctor(
                                        imageUrl = it.imageUrl?:"",
                                        name = it.fullName,
                                        specialty = it.speciality
                                    ) },
                                    services = clinicServices.map {
                                        Service.fromClinicService(it)
                                    },
                                    providesVaccine = providesVaccines,
                                    creatingDate = creationDate,
                                    isAvailableNow = DoctorStatusChecker.getDoctorStatus(workDays) == DoctorStatus.OPENED,
                                    isDeactivated = isDeactivated,
                                    deactivationReason = deactivationReason,
                                    deactivatedBy = deactivatedByUser?.let {user-> User(
                                        id = user.id?:-1,
                                        name = user.fullName
                                    ) }
                                ),
                                currentStatus = DoctorStatusChecker.getDoctorStatus(workDays),
                                onVaccinesItemClick = {
                                    navigationAction.navigateToVaccines()
                                },
                                onDoctorItemClick = {
                                    navigationAction.navigateToDoctorProfile()
                                },
                                onServiceItemClick = {index ->
                                    val service = clinicServices[index]
                                    onAction(
                                        ClinicDetailsUIAction.ShowDialog(
                                            title = service.name,
                                            subtitle = service.description
                                        )
                                    )
                                },
                                onLearnMoreClick = {
                                    TODO("not yet implemented")
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    if (uiState.isDialogShown){
                        DialogWithDescription(
                            onDismissRequest = {
                                onAction(
                                    ClinicDetailsUIAction.HideDialog
                                )
                            },
                            onActionClick = {
                                onAction(
                                    ClinicDetailsUIAction.HideDialog
                                )
                            },
                            title = uiState.dialogTitle,
                            subtitle = uiState.dialogSubtitle,
                        )
                    }
                }
            }
        }
    }
}