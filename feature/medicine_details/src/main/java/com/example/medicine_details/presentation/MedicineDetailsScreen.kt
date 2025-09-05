package com.example.medicine_details.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.constants.icons.AppIcons
import com.example.medicine_details.presentation.composable.BottomSheetContent
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineDetailsData
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.MedicineDetailsCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.HospitalAutomationTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailsScreen(
    viewModel: MedicineDetailsViewModel,
    navigationActions: MedicineDetailsNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    MedicineDetailsScreen(
        uiState = uiState.value,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailsScreen(
    uiState: MedicineDetailsUIState,
    onAction: (MedicineDetailsUIActions) -> Unit,
    navigationActions: MedicineDetailsNavigationActions,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    val context = LocalContext.current
    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let {
            Toast.makeText(
                context,
                it.asString(context),
                Toast.LENGTH_SHORT
            ).show()
            onAction(MedicineDetailsUIActions.ClearToastMessage)
        }
    }

    LaunchedEffect(uiState.isDialogShown) {
        if (uiState.isDialogShown) sheetState.show()
        else sheetState.hide()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HospitalAutomationTopBar(
                title = stringResource(R.string.medicine_details),
                onNavigationIconClick = navigationActions::navigateBack,
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = uiState.screenState,
            ) { state ->
                when (state) {
                    ScreenState.IDLE -> Unit
                    ScreenState.LOADING -> {
                        FetchingDataItem(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    ScreenState.ERROR -> {
                        PullToRefreshColumn(
                            refreshing = uiState.isRefreshing,
                            modifier = Modifier.fillMaxSize(),
                            onRefresh = {
                                onAction(MedicineDetailsUIActions.Refresh)
                            },
                            verticalArrangement = Arrangement.Center
                        ) {
                            ErrorComponent(
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }

                    ScreenState.SUCCESS -> {
                        uiState.data?.let { medicine: MedicineDetailsData ->
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(MedicineDetailsUIActions.Refresh)
                                },
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                MedicineDetailsCard(
                                    imageUrl = medicine.imageUrl,
                                    name = medicine.name,
                                    price = medicine.price,
                                    titer = medicine.pharmaceuticalTiter,
                                    companyName = medicine.companyName,
                                    isAllowedWithoutPrescription = medicine.isAllowedWithoutPrescription,
                                    composition = medicine.pharmaceuticalComposition,
                                    indications = medicine.pharmaceuticalIndications,
                                    onAlternativesItemClick = {
                                        onAction(
                                            MedicineDetailsUIActions.ShowDialog
                                        )
                                    },
                                    numberOfAlternatives = medicine.alternatives.size,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = MaterialTheme.spacing.medium16,
                                            start = MaterialTheme.spacing.medium16,
                                            end = MaterialTheme.spacing.medium16,
                                            bottom = MaterialTheme.spacing.large24,
                                        )
                                )
                            }
                        }
                        //bottom sheet
                        if (uiState.isDialogShown) {
                            ModalBottomSheet(
                                onDismissRequest = {
                                    onAction(
                                        MedicineDetailsUIActions.HideDialog
                                    )
                                },
                                sheetState = sheetState,
                            ) {
                                BottomSheetContent(
                                    selectedMedicines = uiState.data?.alternatives ?: emptyList(),
                                    onMedicineSelected = { medicineId ->
                                        onAction(
                                            MedicineDetailsUIActions.SelectNewMedicine(
                                                medicineId = medicineId
                                            )
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}