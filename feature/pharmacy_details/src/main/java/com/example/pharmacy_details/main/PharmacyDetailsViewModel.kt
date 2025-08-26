package com.example.pharmacy_details.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.pharmacy.GetPharmacyDetailsUseCase
import com.example.model.enums.ScreenState
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.PharmacyDetailsRoute
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PharmacyDetailsViewModel(
    private val getPharmacyDetailsUseCase: GetPharmacyDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PharmacyDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<PharmacyDetailsRoute>()
        updateNavArgs(route.pharmacyAccessType, 1)
//        updateNavArgs(route.profileAccessType, route.pharmacyId)
        getPharmacyDetails()
    }

    fun getUiActions(
        navActions: PharmacyDetailsNavigationUiActions,
    ): PharmacyDetailsUiActions = PharmacyDetailsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): PharmacyDetailsBusinessUiActions =
        object : PharmacyDetailsBusinessUiActions {
            override fun onDeactivateAccount() {
                TODO("Not yet implemented")
            }

            override fun onReactivateAccount() {
                TODO("Not yet implemented")
            }

            override fun onStopPharmacy() {
                TODO("Not yet implemented")
            }

            override fun onRefresh() {
                refreshPharmacyData()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

        }

    private fun updateNavArgs(profileAccessType: PharmacyAccessType, employeeId: Int?) {
        _uiState.update {
            it.copy(
                pharmacyAccessType = profileAccessType,
                pharmacyId = employeeId
            )
        }
    }

    private fun updateProfileInfoState(profileInfo: PharmacyDetailsResponse?) {
        _uiState.update { it.copy(pharmacyInfo = profileInfo) }
        Log.v("imgUrl", profileInfo?.userWithAddress?.imageUrl ?: "")
    }

    private fun updateProfileScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(profileScreenState = screenState) }
    }

    private fun getPharmacyDetails() {
        val pharmacyId = uiState.value.pharmacyId
        if (pharmacyId == null) return
        viewModelScope.launch {
            updateProfileScreenState(ScreenState.LOADING)
            Log.v("Fetching Doctor Profile", "PharmacyDetailsViewModel")
            getPharmacyDetailsUseCase(pharmacyId)
                .onSuccess { data ->
                    Log.v("PharmacyDetails fetched Successfully", "PharmacyDetailsViewModel")
                    updateProfileScreenState(ScreenState.SUCCESS)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch PharmacyDetails", "PharmacyDetailsViewModel")
                    updateProfileScreenState(ScreenState.ERROR)
                    updateProfileInfoState(null)
                }
        }
    }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun refreshPharmacyData() {
        val pharmacyId = uiState.value.pharmacyId
        if (pharmacyId == null) return
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Doctor Profile", "PharmacyDetailsViewModel")
            getPharmacyDetailsUseCase(pharmacyId)
                .onSuccess { data ->
                    Log.v("PharmacyDetails fetched Successfully", "PharmacyDetailsViewModel")
                    updateIsRefreshing(false)
                    updateProfileInfoState(data)
                    if (uiState.value.profileScreenState == ScreenState.ERROR) {
                        updateProfileScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch PharmacyDetails", "PharmacyDetailsViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }

}