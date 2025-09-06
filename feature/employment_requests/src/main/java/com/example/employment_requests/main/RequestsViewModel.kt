package com.example.employment_requests.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import com.example.domain.use_cases.work_request.ChangeRequestStateUseCase
import com.example.domain.use_cases.work_request.GetRequestsUseCase
import com.example.model.enums.ScreenState
import com.example.model.work_request.RequestState
import com.example.model.work_request.SingleRequestResponse
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RequestsViewModel(
    private val getRequestsUseCase: GetRequestsUseCase,
    private val changeRequestStateUseCase: ChangeRequestStateUseCase,
    private val updateIsDarkThemeUseCase: UpdateIsDarkThemeUseCase,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RequestsUiState())
    val uiState: StateFlow<RequestsUiState> = _uiState.asStateFlow()

    init {
        getAppTheme()
    }

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)


    @OptIn(ExperimentalCoroutinesApi::class)
    val requestsFlow: Flow<PagingData<SingleRequestResponse>> = combine(
        refreshTrigger.onStart { emit(Unit) }
    ) {

    }.flatMapLatest {
        updateIsRefreshing(false)
        getRequestsUseCase()
    }
        .cachedIn(viewModelScope)


    fun getUiActions(
        navActions: RequestsNavigationUiActions,
    ): RequestsUiActions = RequestsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): RequestsBusinessUiActions =
        object : RequestsBusinessUiActions {
            override fun onChangeRequestState(
                id: Int,
                state: RequestState
            ) {
                changeRequestState(id, state)
            }

            override fun onRefresh() {
                viewModelScope.launch {
                    updateIsRefreshing(true)
                    refreshTrigger.emit(Unit)
                }
            }

            override fun onUpdateScreenState(screenState: ScreenState) {
                updateScreenState(screenState)
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

            override fun onToggleTheme() {
                toggleTheme()
            }

            override fun onToggleDrawer() {
                toggleDrawer()
            }
        }

    private fun toggleDrawer() {
        _uiState.update { it.copy(isDrawerOpened = !uiState.value.isDrawerOpened) }
    }

    private fun toggleTheme() {
        viewModelScope.launch {
            updateIsDarkThemeUseCase(!uiState.value.isDarkTheme)
        }
    }

    private fun getAppTheme() {
        viewModelScope.launch{
            getUserPreferencesUseCase().collect { userPreference ->
                _uiState.update { it.copy(isDarkTheme = userPreference.isDarkTheme) }
            }
        }
    }


    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(screenState = screenState) }
    }

    private fun updateToastMessage(toastMessage: UiText?) {
        _uiState.update { it.copy(toastMessage = toastMessage) }
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun changeRequestState(id: Int, state: RequestState) {
        viewModelScope.launch {
//            setLoadingDialogState(true, UiText.StringResource(R.string.deactivating))
            Log.v("ChangingRequest $id State", "RequestsViewModel")
            changeRequestStateUseCase(
                requestId = id,
                state = state
            ).onSuccess {
                Log.v("Request State Changed Successfully", "RequestsViewModel")
//                setLoadingDialogState(false, null)
//                setErrorDialogState(false, null)
//                updateAccountDeactivationErrorState(null)
//                updateIsDeactivatedSuccessfullyState(true)
                refreshTrigger.emit(Unit)
                val toastMessage = when (state) {
                    RequestState.ACCEPTED ->
                        UiText.StringResource(R.string.request_accepted_successfully)

                    RequestState.REJECTED -> {
                        UiText.StringResource(R.string.request_rejected_successfully)
                    }

                    else -> null
                }
                updateToastMessage(toastMessage)
            }.onError { error ->
                Log.v("Failed to Change Request State", "RequestsViewModel")
//                setLoadingDialogState(false, null)
//                setErrorDialogState(
//                    true,
//                    UiText.StringResource(R.string.failed_to_deactivate_account)
//                )
//                updateAccountDeactivationErrorState(error)
//                updateIsDeactivatedSuccessfullyState(false)
                updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
            }
        }
    }
}