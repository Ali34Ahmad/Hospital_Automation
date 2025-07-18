package com.example.medicine_details.presentation

interface MedicineDetailsUIActions {
    object ShowDialog: MedicineDetailsUIActions
    object HideDialog: MedicineDetailsUIActions
    object Refresh: MedicineDetailsUIActions
    data class SelectNewMedicine(val medicineId: Int): MedicineDetailsUIActions
    object ClearToastMessage: MedicineDetailsUIActions
}
interface MedicineDetailsNavigationActions{
    fun navigateBack()
}