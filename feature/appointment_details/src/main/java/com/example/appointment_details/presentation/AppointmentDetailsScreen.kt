package com.example.appointment_details.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.constants.icons.AppIcons
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.components.bottomBars.custom.AppointmentBottomBar
import com.example.ui_components.components.card.AppointmentDetailsCard
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.R

@Composable
fun AppointmentDetailsScreen(
    viewModel: AppointmentDetailsViewModel,
    navigationActions: AppointmentNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    AppointmentDetailsScreen(
        uiState = uiState.value,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier,
    )
}
@Composable
internal fun AppointmentDetailsScreen(
    uiState: AppointmentDetailsUIState,
    onAction: (AppointmentDetailsAction) -> Unit,
    navigationActions: AppointmentNavigationActions,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Crossfade(
                uiState.appointment != null,
            ) {state->
                when(state){
                    true -> HospitalAutomationTopBar(
                        showImagePlaceHolder = uiState.appointment?.child != null,
                        imageUrl = uiState.appointment?.user?.img,
                        navigationIcon = AppIcons.Outlined.arrowBack,
                        title = uiState.appointment?.fullName.toString(),
                        onNavigationIconClick = navigationActions::navigateUp,
                    )
                    false -> HospitalAutomationTopBar(
                        navigationIcon = AppIcons.Outlined.arrowBack,
                        title = stringResource(R.string.appointment_details),
                        onNavigationIconClick = navigationActions::navigateUp,
                    )
                }
            }
        },
        bottomBar = {
            uiState.appointment?.let{
                AnimatedVisibility(it.state == AppointmentState.UPCOMMING) {
                    AppointmentBottomBar(
                        markAsMissedButtonState = uiState.markAsMissedButtonState,
                        markAsPassedButtonState = uiState.markAsPassedButtonState,
                        onMarkAsMissed = {
                            onAction(
                                AppointmentDetailsAction.MarkAsMissed
                            )
                        },
                        onMarkAsPassed = {
                            onAction(
                                AppointmentDetailsAction.MarkAsPassed
                            )
                        },
                        onMissedMarkingSucceeded = {
                            navigationActions.navigateUp()
                        },
                        onPassedMarkingSucceeded = {
                            navigationActions.navigateToAddMedicalDiagnosis(uiState.appointmentId)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.medium16)
                    )
                }
            }
        }
    ) {innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
        ) {
            val context = LocalContext.current
            val toastMessage = uiState.toastMessage?.asString()
            LaunchedEffect(toastMessage) {
                if (toastMessage!=null){
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                }
            }

            when(uiState.screenState){
                ScreenState.IDLE -> Unit
                ScreenState.LOADING -> FetchingDataItem(Modifier.fillMaxSize())
                ScreenState.ERROR -> {
                    PullToRefreshColumn(
                        refreshing = uiState.isRefreshing,
                        onRefresh = {
                            onAction(
                                AppointmentDetailsAction.Refresh
                            )
                        }
                    ) {
                        SomeThingWentWrong(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                ScreenState.SUCCESS ->{
                    PullToRefreshColumn(
                        refreshing = uiState.isRefreshing,
                        onRefresh = {
                            onAction(
                                AppointmentDetailsAction.Refresh
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        uiState.appointment?.let {
                            AppointmentDetailsCard(
                                appointment = it,
                                onDepartmentItemClick = {
                                    navigationActions.navigateToDepartmentDetails(it.clinicId)
                                },
                                onVaccineItemClick = {
                                    it.vaccine?.let { vaccine ->
                                        navigationActions.navigateToVaccineDetails(vaccine.id)
                                    }
                                },
                                onAppointmentTypeTagClick = {

                                },
                            )
                        }
                    }
                }
            }
        }
    }
}