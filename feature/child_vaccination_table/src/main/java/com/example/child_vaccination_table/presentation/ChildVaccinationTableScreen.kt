package com.example.child_vaccination_table.presentation

import android.widget.Toast
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
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.constants.icons.AppIcons
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.NotSpecifiedErrorCard
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.tables.vaccination_table.child_table.ChildVaccinationTable
import com.example.ui_components.components.tables.vaccination_table.child_table.createChildVaccinationTableDataSample
import com.example.ui_components.components.topbars.HospitalAutomationTopBar


@Composable
fun ChildVaccinationTableScreen(
    uiState: ChildVaccinationTableUiState,
    uiActions: ChildVaccinationTableUiActions,
    windowSizeClass: WindowSizeClass,
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
                            ChildVaccinationTable(
                                childVaccinationTable = uiState.vaccinationTable,

                                onVaccineItemClick = { vaccineId ->
                                    uiActions.navigateToVaccineDetailsScreen(vaccineId)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium16),
                                showDetails = windowSizeClass.widthSizeClass== WindowWidthSizeClass.Expanded||
                                        windowSizeClass.widthSizeClass== WindowWidthSizeClass.Medium,
                            )
                        }
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@DarkAndLightModePreview
@Composable
fun ChildVaccinationTableScreenPreview() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(400.dp, 600.dp))

    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTableScreen(
                uiState = ChildVaccinationTableUiState(
                    vaccinationTable = createChildVaccinationTableDataSample(),
                    screenState = ScreenState.SUCCESS,
                ),
                uiActions = ChildVaccinationTableUiActions(
                    navigationActions = mockNavigationUiActions(),
                    businessActions = mockBusinessUiActions()
                ),
                windowSizeClass =windowSizeClass,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(widthDp=600)
@Composable
fun ChildVaccinationTableScreenNonEditablePreview() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(1280.dp, 800.dp))
    Hospital_AutomationTheme {
        Surface {
            ChildVaccinationTableScreen(
                uiState = ChildVaccinationTableUiState(
                    vaccinationTable = createChildVaccinationTableDataSample(),
                    screenState = ScreenState.SUCCESS,
                ),
                uiActions = ChildVaccinationTableUiActions(
                    navigationActions = mockNavigationUiActions(),
                    businessActions = mockBusinessUiActions()
                ),
                windowSizeClass = windowSizeClass,
            )
        }
    }
}