package com.example.medical_diagnosis.presentation

import com.example.model.enums.BottomBarState
import com.example.util.UiText

data class DiagnosisUIState(
    val childId: Int?,
    val patientId: Int?,
    val appointmentId: Int,
    val canSkip: Boolean,
    val fullName: String,
    val imgUrl: String? = null,
    val text: String = "",
    val toastMessage: UiText? = null,
    val isValid: Boolean = false,
    val textFieldErrorText: UiText? = null,
    val sendDateState: BottomBarState = BottomBarState.DISABLED,

){
    val textFieldEnabled: Boolean
        get() = !listOf(BottomBarState.LOADING,BottomBarState.SUCCESS).contains(sendDateState)
    val textFieldHasError: Boolean
        get() = textFieldErrorText!=null
    val showChildIcon: Boolean
        get() = patientId == null
}
