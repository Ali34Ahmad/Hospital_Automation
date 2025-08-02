package com.example.medical_diagnosis.presentation

sealed interface DiagnosisUIAction {
    data class UpdateText(val text: String) : DiagnosisUIAction
    object AddDiagnosis: DiagnosisUIAction
    object ClearToast: DiagnosisUIAction
}
interface DiagnosisNavigationActions{
    fun navigateToAppointmentDetails(
        appointmentId: Int
    )
    fun navigateToMedicinesSearch(
        childId: Int?,
        patientId: Int?,
        appointmentId: Int,
    )
}
