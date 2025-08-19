package com.example.medical_records.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateNameFormat
import com.example.model.enums.ScreenState
import com.example.model.medical_record.MedicalRecord
import com.example.model.user.FullName
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.IllustrationCard
import com.example.ui_components.components.card.MedicalRecordCard
import com.example.ui_components.components.progress_indicator.SmallCircularProgressIndicator
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


@Composable
fun MedicalRecordsScreen(
    uiState: MedicalRecordsUiState,
    uiActions: MedicalRecordsUiActions,
    medicalRecords: LazyPagingItems<MedicalRecord>,
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
    LaunchedEffect(medicalRecords.loadState.refresh) {
        medicalRecords.let {
            when (it.loadState.refresh) {
                is LoadState.Error -> {
                    Log.d("AllVaccinesViewModel", "Error ")
                    uiActions.onUpdateScreenState(ScreenState.ERROR)
                }

                LoadState.Loading -> {
                    Log.d("AllVaccinesViewModel", "Loading ")
                    uiActions.onUpdateScreenState(ScreenState.LOADING)
                }

                is LoadState.NotLoading -> {
                    Log.d("AllVaccinesViewModel", "Not loading ")
                    uiActions.onUpdateScreenState(ScreenState.SUCCESS)
                }
            }
        }
    }

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBar(
                title = "",
                onNavigationIconClick = { uiActions.navigateUp() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
                imageUrl = null,
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {

            if (uiState.screenState == ScreenState.LOADING) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize)
                    )
                }
            } else if (uiState.screenState == ScreenState.ERROR) {
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
                        IllustrationCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MaterialTheme.spacing.medium16),
                            illustration = {
                                Image(
                                    painter = painterResource(R.drawable.ill_error),
                                    contentDescription = null,
                                    modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
                                )
                            },
                            title = stringResource(R.string.network_error),
                            description = stringResource(R.string.something_went_wrong),
                        )
                    }
                }
            }
            if (uiState.screenState == ScreenState.SUCCESS) {
                PullToRefreshBox(
                    refreshing = uiState.isRefreshing,
                    onRefresh = { uiActions.onRefresh() }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                        contentPadding = PaddingValues(
                            vertical = MaterialTheme.spacing.medium16,
                            horizontal = MaterialTheme.spacing.medium16,
                        )
                    ) {
                        items(medicalRecords.itemCount) { index ->
                            val medicalRecord = medicalRecords[index]
                            when (medicalRecord) {
                                null -> Unit
                                else -> MedicalRecordCard(
                                    onClick = {
                                        uiActions.navigateToAppointmentsScreen(
                                            patientId = medicalRecord.patientId,
                                            childId = medicalRecord.childId,

                                            )
                                    },
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    imgUrl = medicalRecord.patientImageUrl,
                                    name = medicalRecord.fullName.toAppropriateNameFormat(),
                                    numberOfAppointments = medicalRecord.numberOfAppointments,
                                    numberOfPrescriptions = medicalRecord.numberOfPrescriptions,
                                    onPrescriptionsClick = { },
                                    onAppointmentsClick = { },
                                    patientId = medicalRecord.patientId,
                                    childId = medicalRecord.childId,
                                )
                            }
                        }
                        if (medicalRecords.loadState.append == LoadState.Loading) {
                            item {
                                SmallCircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicalRecordsScreenPreview() {
    val medicalRecordsFlow: Flow<PagingData<MedicalRecord>> = flowOf(
        PagingData.from(getPreviewMedicalRecords(10))
    )

    Hospital_AutomationTheme {
        Surface {
            MedicalRecordsScreen(
                uiState = MedicalRecordsUiState(),
                uiActions = MedicalRecordsUiActions(
                    navigationActions = mockAllVaccinesNavigationUiActions(),
                    businessActions = mockAllVaccinesBusinessUiActions()
                ),
                medicalRecords = medicalRecordsFlow.collectAsLazyPagingItems(),
            )
        }
    }
}

fun getPreviewMedicalRecords(count: Int = 5): List<MedicalRecord> {
    return List(count) { index ->
        MedicalRecord(
            patientId = index,
            patientImageUrl = "",
            childId = 1,
            fullName = FullName("A", "B", "C"),
            numberOfAppointments = 10,
            numberOfPrescriptions = 2, // Example date
            // Add other fields as necessaryو
        )
    }
}