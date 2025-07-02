package com.example.medical_diagnosis.presentation

sealed interface DiagnosisUIAction {
    data class UpdateText(val text: String) : DiagnosisUIAction
    object AddDiagnosis: DiagnosisUIAction
}
interface DiagnosisNavigationActions{

    /**
     * Navigates up in to states
     * When clicking on the navigation button from the top bar,
     * or when clicking on finish to show the result.
     * @author Ali Mansoura.
     */
    fun navigateUp()
    fun navigateToPrescriptionScreen(appointmentId: Int)
}
