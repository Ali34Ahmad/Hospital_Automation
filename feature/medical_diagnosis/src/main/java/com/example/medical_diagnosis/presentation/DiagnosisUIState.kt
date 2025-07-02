package com.example.medical_diagnosis.presentation

import com.example.model.enums.BottomBarState
import com.example.util.UiText

data class DiagnosisUIState(
    val appointmentId: Int = -1,
    val imgUrl: String? = null,
    val fullName: String="",
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
        get() = imgUrl.isNullOrBlank()
}
