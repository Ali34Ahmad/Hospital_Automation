package com.example.clinics_search.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.clinics_search.presentation.ClinicsSearchNavigationActions
import com.example.clinics_search.presentation.ClinicsSearchUIAction
import com.example.clinics_search.presentation.ClinicsSearchUIState
import com.example.model.Department
import com.example.model.Doctor
import com.example.model.Service
import com.example.model.User
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.doctor.day_scedule.DoctorStatusChecker
import com.example.model.enums.DoctorStatus
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.DepartmentCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.progress_indicator.SmallCircularProgressIndicator
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn

@Composable
fun SelectionScreen(
    uiState: ClinicsSearchUIState,
    onAction: (ClinicsSearchUIAction)-> Unit,
    navigationActions: ClinicsSearchNavigationActions,
    clinics: LazyPagingItems<ClinicFullData>,
    modifier: Modifier = Modifier,
) {
    //observing the load state changes

    when(clinics.loadState.refresh){
        is LoadState.Error ->onAction(ClinicsSearchUIAction.UpdateScreenState(ScreenState.LOADING))
        LoadState.Loading -> onAction(ClinicsSearchUIAction.UpdateScreenState(ScreenState.ERROR))
        is LoadState.NotLoading ->onAction(ClinicsSearchUIAction.UpdateScreenState(ScreenState.SUCCESS))
    }

    when(uiState.screenState){
        ScreenState.IDLE -> Unit
        ScreenState.LOADING ->{
            FetchingDataItem(Modifier.fillMaxSize())
        }
        ScreenState.ERROR ->{
            PullToRefreshColumn(
                refreshing = uiState.isRefreshing,
                modifier = Modifier.fillMaxSize(),
                onRefresh = {
                    onAction(ClinicsSearchUIAction.Refresh)
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
            val itemCount = clinics.itemCount
            if (itemCount == 0) {
                PullToRefreshColumn(
                    refreshing = uiState.isRefreshing,
                    onRefresh = {
                        onAction(
                            ClinicsSearchUIAction.Refresh
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    ErrorComponent(
                        modifier = Modifier
                            .fillMaxWidth(),
                        title = stringResource(R.string.no_matching_result),
                        description = stringResource(R.string.no_departments_subtitle)
                    )
                }
            } else {
                PullToRefreshBox(
                    refreshing = uiState.isRefreshing,
                    onRefresh = {
                        onAction(
                            ClinicsSearchUIAction.Refresh
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ){
                    LazyColumn(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(itemCount) { index ->
                            val clinic = clinics[index]
                            clinic?.run {
                                val department = Department(
                                    id = clinicId,
                                    name = name,
                                    workDays = workDays,
                                    activeDoctors =activeDoctors.map {
                                        Doctor(
                                            imageUrl = it.imageUrl?:"",
                                            name = it.fullName,
                                            specialty = it.speciality
                                        )
                                    },
                                    services = clinicServices.map {
                                        Service.fromClinicService(it)
                                    },
                                    providesVaccine = providesVaccines,
                                    creatingDate = creationDate,
                                    isAvailableNow = DoctorStatusChecker.getDoctorStatus(workDays) == DoctorStatus.OPENED,
                                    isDeactivated = isDeactivated,
                                    deactivationReason = deactivationReason,
                                    deactivatedBy = deactivatedByUser?.let {
                                        User(
                                            id = it.id?:-1,
                                            name = it.fullName
                                        )
                                    }
                                )
                                DepartmentCard(
                                    department = department,
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = navigationActions::navigateToDepartmentDetails
                                )
                            }
                        }
                        if(clinics.loadState.append == LoadState.Loading){
                            item {
                                SmallCircularProgressIndicator(
                                    modifier = Modifier.fillMaxWidth()
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

