package com.example.vaccine_details_screen.main

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.ui.fake.createSampleVaccineList
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.SendingDataBottomBar
import com.example.ui_components.components.card.NotSpecifiedErrorCard
import com.example.ui_components.components.complex_components.VaccineDetailsCard
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen

@Composable
fun VaccineDetailsScreen(
    uiState: VaccineDetailsUiState,
    uiActions: VaccineDetailsUiActions,
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

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBar(
                title = uiState.vaccine?.name?:"",
                onNavigationIconClick = { uiActions.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
                imageUrl = null,
            )
        },
        bottomBar = {
            if (uiState.vaccinePreviousScreen== VaccinePreviousScreen.ADD_NEW_VACCINE){
                SendingDataBottomBar(
                    onSuccess = {},
                    text = stringResource(R.string.submit),
                    onButtonClick = {
                        uiActions.navigateToVaccinationTableScreen()
                    },
                    state = BottomBarState.IDLE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(
                            MaterialTheme.spacing.medium16
                        ),
                )
            }
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {

            when {
                uiState.screenState == ScreenState.LOADING -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize)
                        )
                    }
                }

                uiState.screenState == ScreenState.ERROR -> {
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
                            NotSpecifiedErrorCard(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium16)
                            )
                        }
                    }
                }

                uiState.screenState == ScreenState.SUCCESS && uiState.vaccine != null -> {
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
                            VaccineDetailsCard(
                                vaccine = uiState.vaccine,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium16)
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun VaccineDetailsScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            VaccineDetailsScreen(
                uiState = VaccineDetailsUiState(
                    vaccine = createSampleVaccineList()[0],
                    screenState = ScreenState.SUCCESS,
                    vaccinePreviousScreen = VaccinePreviousScreen.ADD_NEW_VACCINE
                ),
                uiActions = VaccineDetailsUiActions(
                    navigationActions = mockEmployeeProfileNavigationUiActions(),
                    businessActions = mockEmployeeProfileBusinessUiActions()
                ),
                modifier = Modifier
            )
        }
    }
}