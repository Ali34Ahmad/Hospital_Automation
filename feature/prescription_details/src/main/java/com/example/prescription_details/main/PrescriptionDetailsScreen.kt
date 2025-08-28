package com.example.prescription_details.main

import android.widget.Toast
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateNameFormat
import com.example.model.enums.ScreenState
import com.example.ui_components.components.bottom_sheets.PrescriptionMedicineViewerBottomSheet
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.NotSpecifiedErrorCard
import com.example.ui_components.components.card.PrescriptionDetailsCard
import com.example.ui_components.components.dialog.DialogWithDescription
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionDetailsScreen(
    uiState: PrescriptionDetailsUiState,
    uiActions: PrescriptionDetailsUiActions,
    modifier: Modifier = Modifier
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

    if (uiState.selectedMedicineIndex != null) {
        val selectedMedicine =
            uiState.prescription?.medicines[uiState.selectedMedicineIndex]
        DialogWithDescription(
            onDismissRequest = {
                uiActions.onUpdateSelectedMedicineIndex(null)
            },
            onActionClick = {
                uiActions.onUpdateSelectedMedicineIndex(null)
            },
            title = selectedMedicine?.medicine?.name + " " + "(${selectedMedicine?.medicine?.titer})",
            subtitle = selectedMedicine?.note ?: "",
        )
    }

    val modalSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true, // Common for modal sheets
        confirmValueChange = {
            // You can still use confirmValueChange if you need to prevent dismissal
            // based on certain conditions before onDismissRequest is called.
            true
        }
    )
    val scope= rememberCoroutineScope()

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBar(
                title = uiState.prescription?.doctorMainInfo?.fullName?.toAppropriateNameFormat()
                    ?: "",
                subTitle = uiState.prescription?.doctorMainInfo?.subInfo,
                onNavigationIconClick = { uiActions.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
                imageUrl = uiState.prescription?.doctorMainInfo?.imageUrl,
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

                uiState.screenState == ScreenState.SUCCESS && uiState.prescription != null -> {
                    PullToRefreshBox(
                        refreshing = uiState.isRefreshing,
                        onRefresh = { uiActions.onRefresh() }
                    ) {

                        Column(
                            Modifier
                                .fillMaxSize()
                                .verticalScroll(scrollState),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            PrescriptionDetailsCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium16),
                                prescriptionDetails = uiState.prescription,
                                onGoToPatientProfile = { uiActions.navigateToPatientProfile(it) },
                                onMedicinesDetailsItemClick = {
                                    uiActions.showBottomSheet()
                                },
                                onGoToChildProfile = { uiActions.navigateToChildProfile(it) },
                            )

                        }
                    }
                    if (uiState.isBottomSheetVisible&&uiState.selectedMedicineIndex==null){
                        ModalBottomSheet(
                            onDismissRequest = {
                                uiActions.hideBottomSheet()
                            },
                            sheetState = modalSheetState,
                            windowInsets = WindowInsets.navigationBars
                        ){
                            PrescriptionMedicineViewerBottomSheet(
                                openNoteDialog = { medicineIndex ->
                                    uiActions.onUpdateSelectedMedicineIndex(medicineIndex)
                                },
                                prescriptionMedicines = uiState.prescription.medicines,
                                navigateToFulfillingPharmacy = { pharmacyId ->
                                    scope.launch {
                                        modalSheetState.hide() // Hide the sheet first
                                    }.invokeOnCompletion {
                                        if (!modalSheetState.isVisible) { // Ensure it's hidden before navigating
                                            uiActions.hideBottomSheet() // Update your ViewModel state
                                            uiActions.navigateToFulfillingPharmacy(pharmacyId)
                                        }
                                    }
                                },
                                title = stringResource(R.string.prescribed_medicines),
                                navigateToMedicineDetailsScreen = {medicineId->
                                    uiActions.navigateToMedicineDetails(medicineId)
                                }
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }

}

/*sheetPeekHeight = MaterialTheme.spacing.default,
        modifier = modifier.windowInsetsPadding(
            WindowInsets.navigationBars
        ),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            PrescriptionMedicineViewerBottomSheet(
                openNoteDialog = { medicineIndex ->
                    uiActions.onUpdateSelectedMedicineIndex(medicineIndex)
                },
                prescriptionMedicines = uiState.prescription?.medicines ?: emptyList(),
                navigateToFulfillingPharmacy = { pharmacyId ->
                    uiActions.navigateToFulfillingPharmacy(pharmacyId)
                },
                title = stringResource(R.string.prescribed_medicines),
                navigateToMedicineDetailsScreen = {medicineId->
                    uiActions.navigateToMedicineDetails(medicineId)
                }
            )
        },*/