package com.example.guardian_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.users.AddGuardianToChildUseCase
import com.example.domain.use_cases.users.DeactivateUserUseCase
import com.example.domain.use_cases.users.GetGuardianByIdUseCase
import com.example.domain.use_cases.users.ReactivateUserUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
import com.example.guardian_profile.navigation.GuardianProfileRoute
import com.example.guardian_profile.navigation.UserProfileMode
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianFullData
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.constants.DurationConstants
import com.example.utility.network.NetworkError
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GuardianProfileViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val guardianProfileUseCase: GetGuardianByIdUseCase,
    private val addGuardianToChildUseCase: AddGuardianToChildUseCase,
    private val deactivateUserUseCase: DeactivateUserUseCase,
    private val reactivateUserUseCase: ReactivateUserUseCase,
    private val validateTextUseCase: ValidateTextUseCase
): ViewModel() {
    val childId : Int? = null
    val guardianId  = 129
    val mode = UserProfileMode.ADMIN_ACCESS
    private val _uiState = MutableStateFlow(
        GuardianProfileUIState(
            childId = childId,
            guardianId = guardianId,
            userProfileMode = mode
        )
    )

    val uiState: StateFlow<GuardianProfileUIState> = _uiState
        .onStart {
            loadInitialData()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _uiState.value
        )

    fun onAction(action: GuardianProfileActions){
        when(action){
            //network call to add guardian to child
            GuardianProfileActions.SetAsGuardian -> {
                _uiState.value.childId?.let {
                    updateScreenState(ScreenState.LOADING)
                    viewModelScope.launch {
                        addGuardianToChildUseCase(
                            childId = it,
                            userId = _uiState.value.guardianId
                        ).onSuccess{
                            updateScreenState(ScreenState.SUCCESS)
                        }
                            .onError {error->
                                updateScreenState(ScreenState.ERROR)
                                setErrorMessage(error)
                                delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
                                resetButtonState()
                            }
                    }
                }
            }
            is GuardianProfileActions.UpdateScreenState -> {
                updateScreenState(action.state)
            }
            is GuardianProfileActions.UpdateBottomBarState ->{
                _uiState.value = _uiState.value.copy(
                    bottomBarState = action.state
                )
            }
            is GuardianProfileActions.UpdateGuardianData ->{
                updateGuardian(action.data)
            }

            GuardianProfileActions.Refresh ->{
                refreshData()
            }
            is GuardianProfileActions.ShowToast -> {
                showToast(action.message)
            }
            is GuardianProfileActions.UpdateRefreshState ->{
                updateRefreshState(action.isRefreshing)
            }

            GuardianProfileActions.DeactivateAccount -> deactivateAccount()
            GuardianProfileActions.ReactivateAccount -> reactivateAccount()
            GuardianProfileActions.ClearDeactivationReason -> clearDeactivationReason()
            GuardianProfileActions.HideLoadingDialog -> hideLoadingDialog()
            GuardianProfileActions.HideWarningDialog -> hideWarningDialog()
            GuardianProfileActions.ShowLoadingDialog -> showLoadingDialog()
            GuardianProfileActions.ShowWarningDialog -> showWarningDialog()
            is GuardianProfileActions.UpdateDeactivationReason -> updateDeactivationReason(action.newValue)
        }
    }

    private fun validateInput(){
        val textError = validateTextUseCase(uiState.value.deactivationReason)
        val hasError = textError != null
        updateIsValidInput(hasError)
    }
    private fun updateIsValidInput(newValue: Boolean?){
        _uiState.value = _uiState.value.copy(isValidInput = newValue)
    }
    private fun deactivateAccount() = viewModelScope.launch{
        showLoadingDialog()
        deactivateUserUseCase(
            userId = uiState.value.guardianId,
            deactivationReason = uiState.value.deactivationReason
        ).onSuccess{
            refreshData()
            showToast(UiText.StringResource(R.string.deactivate_account_message))
        }.onError {
            showToast(UiText.StringResource(R.string.something_went_wrong))
        }
        clearDeactivationReason()
        hideLoadingDialog()
    }
    private fun reactivateAccount() = viewModelScope.launch{
        showLoadingDialog()
        reactivateUserUseCase(
            userId = uiState.value.guardianId
        ).onSuccess{
            showToast(UiText.StringResource(R.string.reactivate_account_message))
            refreshData()
        }.onError {
            showToast(UiText.StringResource(R.string.something_went_wrong))
        }
        hideLoadingDialog()
    }
    private fun clearDeactivationReason(){
        _uiState.value = _uiState.value.copy(deactivationReason = "")
    }
    private fun hideLoadingDialog(){
        _uiState.value = _uiState.value.copy(isLoadingDialogShown = false)
    }
    private fun showLoadingDialog(){
        _uiState.value = _uiState.value.copy(isLoadingDialogShown = true)
    }
    private fun showWarningDialog(){
        _uiState.value = _uiState.value.copy(isWarningDialogShown = true)
    }
    private fun hideWarningDialog(){
        _uiState.value = _uiState.value.copy(isWarningDialogShown = false)
    }
    private fun updateDeactivationReason(newValue: String){
        _uiState.value = _uiState.value.copy(deactivationReason = newValue)
    }
    private fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    private fun updateRefreshState(isRefreshing: Boolean) {
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun loadInitialData(){
        _uiState.value = _uiState.value.copy(screenState = ScreenState.LOADING)
        viewModelScope.launch {
            guardianProfileUseCase(_uiState.value.guardianId)
                .onSuccess{ data->
                    onAction(GuardianProfileActions.UpdateGuardianData(data))
                    _uiState.value = _uiState.value.copy(screenState = ScreenState.SUCCESS)
                }.onError {
                    _uiState.value = _uiState.value.copy(screenState = ScreenState.ERROR)
                }
        }
    }
    private fun resetButtonState(){
        _uiState.value = _uiState.value.copy(
            toastMessage = null,
            bottomBarState = BottomBarState.IDLE
        )
    }
    private fun updateGuardian(guardian: GuardianFullData){
        _uiState.value = _uiState.value.copy(
            guardianData = guardian
        )
    }
    private fun setErrorMessage(error: NetworkError){
        when(error){
            NetworkError.GUARDIAN_ALREADY_ASSIGNED -> {
                _uiState.value = _uiState.value.copy(
                    toastMessage = UiText.StringResource(
                        R.string.guardian_already_assigned
                    )
                )
            }
            else -> {
                _uiState.value = _uiState.value.copy(
                    toastMessage = UiText.StringResource(
                        R.string.network_error
                    )
                )
            }
        }
    }
    private fun refreshData(){
        viewModelScope.launch{
            updateRefreshState(true)
            guardianProfileUseCase(_uiState.value.guardianId)
                .onSuccess{ result->
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    updateGuardian(result)
                    if(uiState.value.screenState == ScreenState.ERROR){
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError {
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }
}