package com.example.add_child_screen.presentation

import com.example.constants.enums.Gender
import com.example.model.child.ChildFullData
import com.example.model.enums.BottomBarState
import com.example.util.UiText

data class AddChildUIState(
    val guardianId: Int = -1,

    val firstName: String = "",
    val lastName: String= "",
    val dateOfBirth: String= "",
    val gender: Gender = Gender.MALE,
    val fatherFirstName: String = "",
    val fatherLastName: String = "",
    val motherFirstName: String = "",
    val motherLastName: String = "",

    val firstNameErrorMessage: UiText? = null,
    val lastNameErrorMessage: UiText? = null,
    val fatherFirstNameErrorMessage: UiText? = null,
    val fatherLastNameErrorMessage: UiText? = null,
    val motherFirstNameErrorMessage: UiText? = null,
    val motherLastNameErrorMessage: UiText? = null,
    val dateOfBirthErrorMessage: UiText? = null,

    val isDatePickerVisible: Boolean = false,
    val isValid: Boolean = false,

    val sendingDataButtonState: BottomBarState = BottomBarState.IDLE,
    val isSendingDataButtonVisible: Boolean = true,
    val childId: Int? = null
)

internal fun AddChildUIState.toChildFullData(): ChildFullData =
    ChildFullData(
        firstName = firstName.trim(),
        lastName = lastName.trim(),
        fatherFirstName = fatherFirstName.trim(),
        fatherLastName = fatherLastName.trim(),
        motherFirstName = motherFirstName.trim(),
        motherLastName = motherLastName.trim(),
        dateOfBirth = dateOfBirth.trim(),
        gender = gender.toString().trim(),
    )

