package com.example.medicines_search.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.medicines_search.presentation.composable.BottomSheetContent
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineData
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.MedicineCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.dialog.AddNoteDialog
import com.example.ui_components.components.dialog.LoadingDialog
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.custom.SearchBarWithTextAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicinesSearchScreen(
    viewModel: MedicineSearchViewModel,
    navigationActions: MedicinesSearchNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    val medicines = viewModel.medicinesFlow.collectAsLazyPagingItems()
    MedicinesSearchScreen(
        uiState = uiState.value,
        medicines = medicines,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicinesSearchScreen(
    uiState: MedicinesSearchUIState,
    onAction: (MedicinesSearchUIAction)-> Unit,
    navigationActions: MedicinesSearchNavigationActions,
    medicines: LazyPagingItems<MedicineData>?,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(uiState.isDataSentSuccessfully) {
        if(uiState.isDataSentSuccessfully){
            navigationActions.navigateToAppointmentDetails(uiState.appointmentId)
        }
    }
    medicines?.loadState?.run {
        when(refresh){
            is LoadState.Error -> onAction(MedicinesSearchUIAction.UpdateScreenState(ScreenState.ERROR))
            LoadState.Loading -> onAction(MedicinesSearchUIAction.UpdateScreenState(ScreenState.LOADING))
            is LoadState.NotLoading -> onAction(MedicinesSearchUIAction.UpdateScreenState(ScreenState.SUCCESS))
        }
    }

    BottomSheetScaffold(
        sheetPeekHeight = if(uiState.screenState == ScreenState.SUCCESS) MaterialTheme.spacing.sheetPeekHeight else  BottomSheetDefaults. SheetPeekHeight,
        modifier = modifier,
        sheetContent = {
            BottomSheetContent(
                onClear = {
                    onAction(
                        MedicinesSearchUIAction.Clear
                    )
                },
                openNoteDialog = {
                    onAction(
                        MedicinesSearchUIAction.OpenNoteDialog(it)
                    )
                },
                selectedMedicines = uiState.bottomSheetMedicines ,
                onMedicineDeleted = {
                    onAction(
                        MedicinesSearchUIAction.RemoveMedicineFromPrescription(it)
                    )
                },
            )
        },
        topBar = {
            SearchBarWithTextAction(
                query = uiState.query,
                onQueryChanged = {
                    onAction(
                        MedicinesSearchUIAction.UpdateQuery(it)
                    )
                },
                onClearIconClick = {
                    onAction(
                        MedicinesSearchUIAction.UpdateQuery("")
                    )
                },
                onNavigateBack = navigationActions::navigateUp,
                placeholder = R.string.search_for_medicines,
                modifier = Modifier.fillMaxWidth(),
                actionText = stringResource(R.string.finish),
                onActionClick = {
                    onAction(
                        MedicinesSearchUIAction.Finish
                    )
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding->
        AnimatedContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16),
            targetState = uiState.screenState
        ) { state ->
            when (state) {
                ScreenState.IDLE -> Unit
                ScreenState.LOADING -> FetchingDataItem(Modifier.fillMaxSize())
                ScreenState.ERROR -> {
                    PullToRefreshColumn(
                        onRefresh = {
                            onAction(MedicinesSearchUIAction.Refresh)
                        },
                        refreshing = uiState.isRefreshing,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        ErrorComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }

                ScreenState.SUCCESS -> {
                    //loading dialog
                    LoadingDialog(
                        showDialog = uiState.isLoadingDialogShown,
                        text = stringResource(R.string.please_wait)
                    )
                    //add note dialog
                    if(uiState.isNoteDialogOpened&&uiState.dialogMedicine!=null){
                        AddNoteDialog(
                            title = uiState.dialogMedicine.name,
                            modifier = Modifier.fillMaxWidth(),
                            onDismissRequest = {
                                onAction(
                                    MedicinesSearchUIAction.CloseNoteDialog
                                )
                            },
                            note = uiState.dialogNote,
                            onNoteChanged = {
                                onAction(
                                    MedicinesSearchUIAction.UpdateNote(
                                        newNote = it
                                    )
                                )
                            },
                            onSave = {
                                onAction(
                                    MedicinesSearchUIAction.AddNote(
                                        medicineId = uiState.dialogMedicine.medicineId,
                                        note = uiState.dialogNote,
                                    )
                                )
                            },
                        )
                    }

                    medicines?.let { medicines ->
                        val count = medicines.itemCount
                        if (count == 0) {
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        MedicinesSearchUIAction.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                val description =
                                    if (uiState.query.isBlank()) stringResource(R.string.no_medicines)
                                    else stringResource(
                                        R.string.no_results_subtitle,
                                        R.string.medicines,
                                        uiState.query.trim()
                                    )
                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16),
                                    title = stringResource(R.string.no_matching_result),
                                    description = description
                                )
                            }
                        } else {
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                columns = GridCells.Fixed(2),
                                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                            ) {
                                items(count) { index ->
                                    val medicine = medicines[index]
                                    medicine?.run {
                                        MedicineCard(
                                            imageUrl = medImageUrl,
                                            medicineName = name,
                                            drug = pharmaceuticalTiter,
                                            price = price,
                                            numberOfPharmacies = numberOfPharmacies,
                                            onClick = {
                                                navigationActions.navigateToMedicineDetails(medicineId)
                                            },
                                            onPharmaciesClick = {
                                                navigationActions.navigateToPharmacies(
                                                    medicineId
                                                )
                                            },
                                            onButtonClick = {
                                                onAction(
                                                    MedicinesSearchUIAction.AddMedicineToPrescription(
                                                        this
                                                    )
                                                )
                                            },
                                        )
                                    }
                                }
                                item {
                                    //due to the bottom sheet is opened
                                    Spacer(
                                        modifier = Modifier.height(
                                            MaterialTheme.spacing.sheetPeekHeight
                                        ))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}