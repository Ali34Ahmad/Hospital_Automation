package com.example.add_child_screen.presentation

import com.example.model.enums.Gender
import java.time.LocalDate

sealed interface AddChildUIActions {
    data class OnFirstNameChanged(val newValue: String): AddChildUIActions
    data class OnLastNameChanged(val newValue: String): AddChildUIActions
    data class OnDateChanged(val date: LocalDate): AddChildUIActions
    data class OnGenderChanged(val newGender: Gender ): AddChildUIActions
    data class OnFatherFirstNameChanged(val newValue: String): AddChildUIActions
    data class OnFatherLastNameChanged(val newValue: String): AddChildUIActions
    data class OnMotherFirstNameChanged(val newValue: String): AddChildUIActions
    data class OnMotherLastNameChanged(val newValue: String): AddChildUIActions

    object SendData: AddChildUIActions

    data class ChangeDatePickerVisibility(val isVisible: Boolean): AddChildUIActions


}

interface AddChildNavigationAction{
    fun navigateUp()
    fun navigateToNextScreen(childId: Int)
}