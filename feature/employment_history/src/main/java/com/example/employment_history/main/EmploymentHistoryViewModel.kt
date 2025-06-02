package com.example.employment_history.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.employment_history.GetEmploymentHistoryUseCase
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.utility.network.Error
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmploymentHistoryViewModel(
    private val getEmploymentHistoryUseCase: GetEmploymentHistoryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmploymentHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getEmploymentHistory()
    }

    fun getUiActions(
        navActions: EmploymentHistoryNavigationUiActions,
    ): EmploymentHistoryUiActions = EmploymentHistoryUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): EmploymentHistoryBusinessUiActions =
        object : EmploymentHistoryBusinessUiActions {
            override fun onRefreshProfile() {
                getEmploymentHistory()
            }

            override fun hideErrorDialog() {

            }

        }

    private fun updateErrorState(error: Error?) {
        _uiState.update { it.copy(fetchingHistoryError = error) }
    }

    private fun updateInfoState(employmentHistory: EmploymentHistoryResponse?) {
        _uiState.update { it.copy(employmentHistory = employmentHistory) }
        Log.v("imgUrl", employmentHistory.toString())
    }


    private fun getEmploymentHistory() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Submitting log in info", "LoginViewModel")
            getEmploymentHistoryUseCase()
                .onSuccess { data ->
                    Log.v("EmploymentHistory fetched Successfully", "EmploymentHistoryViewModel")
                    updateIsLoadingState(false)
                    updateErrorState(null)
                    updateInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch EmploymentHistory", "EmploymentHistoryViewModel")
                    updateIsLoadingState(false)
                    updateErrorState(error)
                    updateInfoState(null)
                }
        }
    }

    private fun updateIsLoadingState(value: Boolean) {
        _uiState.update { it.copy(isLoading = value) }
    }

}