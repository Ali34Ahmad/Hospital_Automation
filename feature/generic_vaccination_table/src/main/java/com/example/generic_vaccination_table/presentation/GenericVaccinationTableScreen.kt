package com.example.generic_vaccination_table.presentation

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.generic_vaccination_table.navigation.GenericVaccinationTableAccessType
import com.example.model.enums.ScreenState
import com.example.model.vaccine.GenericVaccinationTable
import com.example.ui.fake.createFakeVaccinationData
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.NotSpecifiedErrorCard
import com.example.ui_components.components.dialog.MessageDialog
import com.example.ui_components.components.dialog.MultiSelectionsOptionsPickerDialog
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.tables.vaccination_table.generic.GenericVaccinationTable
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.option.models.Option


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

    val datePickerState: UseCaseState = rememberUseCaseState()
    MultiSelectionsOptionsPickerDialog(
        state = datePickerState,
        onConfirm = { selectedVaccinesIndexes ->
            uiActions.onAddVaccinesToVisit(selectedVaccinesIndexes)
        },
        options = uiState.vaccines?.map { Option(titleText = it.name) } ?: emptyList()
    )

    val vaccineIdToDelete = uiState.vaccinationTable?.visits?.find {
        uiState.visitNumberToUse == it.visitNumber
    }
        ?.vaccines?.find { it.id == uiState.vaccineIdToDelete }?.id
    MessageDialog(
        showDialog = uiState.isDeleteConfirmationDialogVisible,
        title = uiState.dialogTitleText?.asString() ?: "",
        description = uiState.dialogDescriptionText?.asString() ?: "",
        onDismiss = { uiActions.onUpdateConfirmationDialogVisibility(false) },
        onConfirm = {
            uiActions.onRemoveVaccineFromVisit(
                uiState.visitNumberToUse ?: -1,
                vaccineIdToDelete ?: -1
            )
        },
        showCancelButton = true,
        dismissButtonText = uiState.dialogCancelText?.asString() ?: "",
        confirmButtonText = uiState.dialogConfirmText?.asString() ?: ""
    )

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
                            GenericVaccinationTable(
                                isEditable = uiState.genericVaccinationTableAccessType == GenericVaccinationTableAccessType.EDITOR_ACCESS,
                                genericVaccinationTable = uiState.vaccinationTable,
                                onAddNewVisit = {
                                    uiActions.onAddNewVisit()
                                },
                                onAddNewVaccineToVisit = {
                                    uiActions.onAddVaccineToVisitClick(it)
                                    datePickerState.show()
                                },
                                onDeleteVaccine = { visitNumber, vaccineIndex ->
                                    uiActions.onSetVisitNumberAndVaccineIndex(
                                        visitNumber,
                                        vaccineIndex
                                    )
                                },
                                onVaccineItemClick = {vaccineId->
                                    uiActions.navigateToVaccineDetailsScreen(vaccineId)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
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
fun GenericVaccinationTableScreenPreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTableScreen(
                uiState = GenericVaccinationTableUiState(
                    vaccinationTable = GenericVaccinationTable(createFakeVaccinationData().filter { it.vaccines.isNotEmpty() }),
                    screenState = ScreenState.SUCCESS,
                ),
                uiActions = GenericVaccinationTableUiActions(
                    navigationActions = mockNavigationUiActions(),
                    businessActions = mockBusinessUiActions()
                ),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun GenericVaccinationTableScreenNonEditablePreview() {
    Hospital_AutomationTheme {
        Surface {
            GenericVaccinationTableScreen(
                uiState = GenericVaccinationTableUiState(
                    vaccinationTable = GenericVaccinationTable(createFakeVaccinationData().filter { it.vaccines.isNotEmpty() }),
                    screenState = ScreenState.SUCCESS,
                ),
                uiActions = GenericVaccinationTableUiActions(
                    navigationActions = mockNavigationUiActions(),
                    businessActions = mockBusinessUiActions()
                ),
            )
        }
    }
}