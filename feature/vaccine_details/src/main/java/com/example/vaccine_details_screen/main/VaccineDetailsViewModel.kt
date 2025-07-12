package com.example.vaccine_details_screen.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.vaccine.GetVaccineByIdUseCase
import com.example.model.enums.ScreenState
import com.example.model.role_config.RoleAppConfig
import com.example.model.vaccine.VaccineData
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import com.example.vaccine_details_screen.navigation.VaccineDetailsRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class VaccineDetailsViewModel(
    private val getVaccineByIdUseCase: GetVaccineByIdUseCase,
    private val appRoleAppConfig: RoleAppConfig,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(VaccineDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val routeArgs = savedStateHandle.toRoute<VaccineDetailsRoute>()
        _uiState.update {
            it.copy(
                vaccineId = routeArgs.vaccineId,
                vaccinePreviousScreen = routeArgs.vaccinePreviousScreen
            )
        }
        getVaccineDetails()
    }

    fun getUiActions(
        navActions: VaccineDetailsNavigationUiActions,
    ): VaccineDetailsUiActions = VaccineDetailsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): VaccineDetailsBusinessUiActions =
        object : VaccineDetailsBusinessUiActions {
            override fun onRefresh() {
                refreshVaccineDetails()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

        }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(screenState = screenState) }
    }

    private fun updateVaccineData(vaccine: VaccineData?) {
        _uiState.update { it.copy(vaccine = vaccine) }
    }


    private fun getVaccineDetails() {
        val vaccineId = uiState.value.vaccineId
        if (vaccineId == null) return
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Fetching Doctor Profile", "DoctorProfileViewModel")
            getVaccineByIdUseCase(vaccineId, appRoleAppConfig.role)
                .onSuccess { data ->
                    Log.v("DoctorProfile fetched Successfully", "DoctorProfileViewModel")
                    updateScreenState(ScreenState.SUCCESS)
                    updateVaccineData(data)
                }.onError { error ->
                    Log.v("Failed to fetch DoctorProfile", "DoctorProfileViewModel")
                    updateScreenState(ScreenState.ERROR)
                    updateVaccineData(null)
                }
        }
    }

    private fun refreshVaccineDetails() {
        val vaccineId = uiState.value.vaccineId
        if (vaccineId == null) return
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Vaccine Details ", "VaccineDetailsViewModel")
            getVaccineByIdUseCase(vaccineId, appRoleAppConfig.role)
                .onSuccess { data ->
                    Log.v("Vaccine Details  fetched Successfully", "VaccineDetailsViewModel")
                    updateIsRefreshing(false)
                    updateVaccineData(data)
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch Vaccine Details ", "VaccineDetailsViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }

}