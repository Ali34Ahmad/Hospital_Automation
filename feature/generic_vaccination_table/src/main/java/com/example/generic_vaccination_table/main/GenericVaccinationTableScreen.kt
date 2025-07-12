package com.example.generic_vaccination_table.main

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.vaccine.GenericVaccinationTable
import com.example.ui.fake.createFakeVaccinationData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.SendingDataBottomBar
import com.example.ui_components.components.card.NotSpecifiedErrorCard
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.tables.vaccination_table.VaccinationTable
import com.example.ui_components.components.topbars.HospitalAutomationTopBar


@Composable
fun GenericVaccinationTableScreen(
    uiState: GenericVaccinationTableUiState,
    uiActions: GenericVaccinationTableUiActions,
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
                title = stringResource(R.string.vaccination_table),
                onNavigationIconClick = { uiActions.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
                imageUrl = null,
            )
        },
        bottomBar = {
            if (uiState.showSaveButton) {
                SendingDataBottomBar(
                    onSuccess = {},
                    text = stringResource(R.string.submit),
                    onButtonClick = {
                        uiActions.onGetGenericVaccinationTable()
                    },
                    state = BottomBarState.IDLE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            MaterialTheme.spacing.medium16
                        )
                        .background(MaterialTheme.colorScheme.surface),
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

                uiState.screenState == ScreenState.SUCCESS && uiState.vaccinationTable != null -> {
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
                            VaccinationTable(
                                genericVaccinationTable = uiState.vaccinationTable,
                                onAddNewVisit = {
                                    uiActions.onAddNewVisit()
                                },
                                onAddNewVaccineToVisit = {
                                    uiActions.onAddVaccineToVisit(it)
                                },
                                onDeleteVaccine = {visitNumber,vaccineIndex->
                                    uiActions.onRemoveVaccineFromVisit(visitNumber,vaccineIndex)
                                },
                                onVaccineItemClick = {
                                    uiActions.navigateToVaccineDetailsScreen()
                                },
                                modifier = Modifier.fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium16),
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
fun GenericVaccinationTableScreenPreview(){
    Hospital_AutomationTheme {
        Surface{
            GenericVaccinationTableScreen(
                uiState = GenericVaccinationTableUiState(
                    vaccinationTable = GenericVaccinationTable(createFakeVaccinationData()),
                    screenState = ScreenState.SUCCESS,
                    showSaveButton = true
                ),
                uiActions = GenericVaccinationTableUiActions(
                    navigationActions = mockEmployeeProfileNavigationUiActions(),
                    businessActions = mockEmployeeProfileBusinessUiActions()
                ),
            )
        }
    }
}