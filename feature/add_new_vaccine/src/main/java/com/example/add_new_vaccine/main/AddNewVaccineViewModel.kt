package com.example.add_new_vaccine.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.add_new_vaccine.validator.AddNewVaccineValidationResult
import com.example.add_new_vaccine.validator.AddNewVaccineValidator
import com.example.domain.use_cases.prescription.AddNewVaccineUseCase
import com.example.model.age.Age
import com.example.model.enums.AgeUnit
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.vaccine.VaccineData
import com.example.model.vaccine.VaccineInteraction
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AddNewVaccineViewModel(
    private val addNewVaccineValidator: AddNewVaccineValidator,
    private val addNewVaccineUseCase: AddNewVaccineUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddNewVaccineUiState())
    val uiState = _uiState.asStateFlow()

    fun getUiActions(
        navActions: AddNewVaccineNavigationUiActions,
    ): AddNewVaccineUiActions = AddNewVaccineUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): AddNewVaccineBusinessUiActions =
        object : AddNewVaccineBusinessUiActions {
            override fun onVaccineNameChange(vaccineName: String) {
                updateVaccineName(vaccineName)
            }

            override fun onFromAgeChange(fromAge: String) {
                updateFromAgeText(fromAge)
            }

            override fun onFromAgeMenuExpandedChange(isExpanded: Boolean) {
                updateIsFromAgeMenuExpanded(isExpanded)
            }

            override fun onUpdateSelectedFromAgeUnitIndex(selectedUnit: AgeUnit) {
                updateSelectedFromAgeUnit(selectedUnit)
            }

            override fun onToAgeChange(toAge: String) {
                updateToAgeText(toAge)
            }

            override fun onToAgeMenuExpandedChange(isExpanded: Boolean) {
                updateIsToAgeMenuExpanded(isExpanded)
            }

            override fun onUpdateSelectedToAgeUnitIndex(selectedUnit: AgeUnit) {
                updateSelectedToAgeUnit(selectedUnit)
            }

            override fun onQuantityChange(quantity: String) {
                updateQuantityText(quantity)
            }

            override fun onVaccineDescriptionChange(vaccineDescription: String) {
                updateVaccineDescription(vaccineDescription)
            }

            override fun onInteractionNameChange(interactionName: String) {
                updateInteractionName(interactionName)
            }

            override fun onInteractionDescriptionChange(interactionDescription: String) {
                updateInteractionDescription(interactionDescription)
            }

            override fun onAddInteractionClick() {
                addNewInteraction()
            }

            override fun onSaveInteractionClick() {
                saveEditedInteraction()
            }

            override fun onTabItemClick(index: Int) {
                updateSelectedTab(index)
            }

            override fun onVaccineInteractionTableItemClick(index: Int) {
                initiateEditingDialog(index)
            }

            override fun onUpdateVaccineInteractionDialogVisibilityState(isVisible: Boolean) {
                updateVaccineInteractionDialogVisibilityState(isVisible)
            }

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onSubmitButtonClick() {
                validateAndSubmit()
            }
        }

    private fun updateVaccineName(value: String) {
        _uiState.update { it.copy(vaccineName = value) }
        updateSendOtpButtonEnablement()
    }

    private fun updateFromAgeText(value: String) {
        _uiState.update { it.copy(fromAge = value) }
        updateSendOtpButtonEnablement()
    }

    private fun updateIsFromAgeMenuExpanded(isExpanded: Boolean) {
        _uiState.update { it.copy(isFromAgeMenuExpanded = isExpanded) }
        updateSendOtpButtonEnablement()
    }

    private fun updateSelectedFromAgeUnit(ageUnit: AgeUnit) {
        _uiState.update { it.copy(fromAgeUnit = ageUnit) }
        updateSendOtpButtonEnablement()
    }

    private fun updateToAgeText(value: String) {
        _uiState.update { it.copy(toAge = value) }
        updateSendOtpButtonEnablement()
    }

    private fun updateIsToAgeMenuExpanded(isExpanded: Boolean) {
        _uiState.update { it.copy(isToAgeMenuExpanded = isExpanded) }
        updateSendOtpButtonEnablement()
    }

    private fun updateSelectedToAgeUnit(ageUnit: AgeUnit) {
        _uiState.update { it.copy(toAgeUnit = ageUnit) }
        updateSendOtpButtonEnablement()
    }

    private fun updateQuantityText(value: String) {
        _uiState.update { it.copy(quantity = value) }
        updateSendOtpButtonEnablement()
    }

    private fun updateVaccineDescription(value: String) {
        _uiState.update { it.copy(vaccineDescription = value) }
        updateSendOtpButtonEnablement()
    }

    private fun updateInteractionToEditIndex(index: Int?) {
        _uiState.update { it.copy(interactionToEditIndex = index) }
        updateInteractionDialogConfirmButtonEnabled()
    }

    private fun updateInteractionName(value: String) {
        _uiState.update { it.copy(interactionName = value) }
        updateInteractionDialogConfirmButtonEnabled()
    }

    private fun updateInteractionDescription(value: String) {
        _uiState.update { it.copy(interactionDescription = value) }
        updateInteractionDialogConfirmButtonEnabled()
    }

    private fun addNewInteraction() {
        val interaction = VaccineInteraction(
            name = uiState.value.interactionName,
            description = uiState.value.interactionDescription
        )
        val interactions = uiState.value.interactions.toMutableList()
        interactions.add(interaction)

        _uiState.update {
            it.copy(
                interactions = interactions
            )
        }
        updateInteractionName("")
        updateInteractionDescription("")
        updateVaccineInteractionDialogVisibilityState(false)
    }

    private fun saveEditedInteraction() {
        val indexToEdit = uiState.value.interactionToEditIndex
        if (indexToEdit == null) return

        val interaction = VaccineInteraction(
            name = uiState.value.interactionName,
            description = uiState.value.interactionDescription
        )
        val interactions = uiState.value.interactions.toMutableList()
        interactions[indexToEdit] = interaction

        _uiState.update {
            it.copy(
                interactions = interactions
            )
        }
        updateInteractionName("")
        updateInteractionDescription("")
        updateVaccineInteractionDialogVisibilityState(false)
        updateInteractionToEditIndex(null)
    }


    private fun updateSelectedTab(index: Int) {
        _uiState.update { it.copy(selectedTabItem = index) }
        if (uiState.value.selectedTabItem == 1) {
            showAddInteractionDialog()
            updateIsAddingInteraction(true)
            updateInteractionName("")
            updateInteractionDescription("")
        }
    }

    private fun initiateEditingDialog(index: Int) {
        showAddInteractionDialog()
        updateInteractionToEditIndex(index)
        updateIsAddingInteraction(false)
        val i = uiState.value.interactionToEditIndex
        if (i == null) return
        updateInteractionName(uiState.value.interactions[i].name)
        updateInteractionDescription(uiState.value.interactions[i].description)
    }

    private fun updateIsAddingInteraction(isAdding: Boolean) {
        _uiState.update { it.copy(isAddingNewInteraction = isAdding) }
    }

    private fun showAddInteractionDialog() {
        updateVaccineInteractionDialogVisibilityState(isVisible = true)
    }


    private fun updateVaccineInteractionDialogVisibilityState(isVisible: Boolean) {
        _uiState.update { it.copy(showAddInteractionDialog = isVisible) }
        if (!isVisible) {
            updateSelectedTab(0)
            updateInteractionName("")
            updateInteractionDescription("")
        }
    }


    private fun updateSendOtpButtonEnablement() {
        _uiState.update { currentState ->
            val allFieldsFilled = currentState.vaccineName.isNotEmpty() &&
                    currentState.fromAge.isNotEmpty() &&
                    currentState.fromAgeUnit != AgeUnit.NONE &&
                    currentState.toAge.isNotEmpty() &&
                    currentState.toAgeUnit != AgeUnit.NONE &&
                    currentState.quantity.isNotEmpty() &&
                    currentState.vaccineDescription.isNotEmpty()


            currentState.copy(
                bottomBarState = if (allFieldsFilled) {
                    BottomBarState.IDLE
                } else {
                    BottomBarState.DISABLED
                }
            )
        }
    }

    private fun updateInteractionDialogConfirmButtonEnabled() {
        _uiState.update { currentState ->
            val allFieldsFilled = currentState.interactionName.isNotEmpty() &&
                    currentState.interactionDescription.isNotEmpty()

            currentState.copy(isInteractionDialogConfirmButtonEnabled = allFieldsFilled)
        }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateValidationErrors(result: AddNewVaccineValidationResult) {
        _uiState.update {
            it.copy(
                vaccineNameError = result.vaccineNameError,
                fromAgeError = result.fromAgeError,
                fromAgeUnitError = result.fromAgeUnitError,
                toAgeError = result.toAgeError,
                toAgeUnitError = result.toAgeUnitError,
                quantityError = result.quantityError,
                vaccineDescriptionError = result.vaccineDescriptionError
            )
        }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update {
            it.copy(
                screenState = screenState
            )
        }
    }

    private fun updateBottomBarState(bottomBarState: BottomBarState) {
        _uiState.update { currentState ->
            currentState.copy(
                bottomBarState = bottomBarState
            )
        }
    }

    private fun updateVaccineId(vaccineId: Int?){
        _uiState.update { it.copy(vaccineId=vaccineId) }
    }

    private fun submitVaccineData() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            updateBottomBarState(BottomBarState.LOADING)
            Log.v("Submitting sign up info", "SignUpViewModel")
            addNewVaccineUseCase(
                vaccineData = VaccineData(
                    name = uiState.value.vaccineName,
                    description = uiState.value.vaccineDescription,
                    quantity = uiState.value.quantity.toInt(),
                    minAge = Age(uiState.value.fromAge.toInt(), uiState.value.fromAgeUnit),
                    maxAge = Age(uiState.value.toAge.toInt(), uiState.value.toAgeUnit),
                    interactions = uiState.value.interactions,
                )
            ).onSuccess { response ->
                Log.v("Successful sign up", "SignUpViewModel")
                updateBottomBarState(BottomBarState.IDLE)
                updateScreenState(ScreenState.SUCCESS)
                updateVaccineId(response.id)
                updateShowErrorDialogState(false)
            }.onError { error ->
                Log.v("Failed sign up", "SignUpViewModel")
                updateBottomBarState(BottomBarState.IDLE)
                updateScreenState(ScreenState.ERROR)
                updateShowErrorDialogState(true)
            }
        }
    }

    private fun validateAndSubmit() {
        val validationResult = addNewVaccineValidator.validate(uiState.value)
        updateValidationErrors(validationResult)
        if (!validationResult.hasErrors()) {
            submitVaccineData()
        }
    }

}