package com.example.add_new_vaccine.main

import com.example.model.InteractionData
import com.example.model.enums.AgeUnit
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class AddNewVaccineUiState(
    val vaccineName: String = "",
    val vaccineNameError: UiText? = null,

    val fromAge: String = "",
    val fromAgeError: UiText? = null,
    val isFromAgeMenuExpanded: Boolean=false,
    val fromAgeUnit: AgeUnit = AgeUnit.NOT_SPECIFIED,
    val fromAgeUnitError: UiText? = null,

    val toAge: String = "",
    val toAgeError: UiText? = null,
    val isToAgeMenuExpanded: Boolean=false,
    val toAgeUnit: AgeUnit = AgeUnit.NOT_SPECIFIED,
    val toAgeUnitError: UiText? = null,

    val quantity: String = "",
    val quantityError: UiText? = null,
    val vaccineDescription: String = "",
    val vaccineDescriptionError: UiText? = null,
    val interactionName: String = "",
    val interactionNameError: UiText? = null,
    val interactionDescription: String = "",
    val interactionDescriptionError: UiText? = null,
    val interactions: List<InteractionData> = emptyList(),
    val isSubmitButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState = ScreenState.IDLE,

    val vaccineId:Int?=null,
)