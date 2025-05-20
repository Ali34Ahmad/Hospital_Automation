package com.example.add_child_screen

import com.example.constants.enums.Gender

sealed interface AddChildUIActions {
    data class OnFirstNameChanged(val newValue: String): AddChildUIActions
    data class OnLastNameChanged(val newValue: String): AddChildUIActions
    data class OnDateChanged(val date: String): AddChildUIActions
    data class OnGenderChanged(val newGender: Gender ): AddChildUIActions
    data class OnFatherFirstNameChanged(val newValue: String): AddChildUIActions
    data class OnFatherLastNameChanged(val newValue: String): AddChildUIActions
    data class OnMotherFirstNameChanged(val newValue: String): AddChildUIActions
    data class OnMotherLastNameChanged(val newValue: String): AddChildUIActions

    object Validate: AddChildUIActions
    object SendData: AddChildUIActions

    /**
     *Clears all input fields in the form.
     * Resets the bottom bar to its initial state.
     * This action also verifies if data has already been successfully sent
     * to prevent redundant submissions.
     */
    object ClearForm: AddChildUIActions

    data class ChangeDatePickerVisibility(val isVisible: Boolean): AddChildUIActions

    //navigation
    object NavigateBack: AddChildUIActions
    object NavigateToNextScreen: AddChildUIActions

}