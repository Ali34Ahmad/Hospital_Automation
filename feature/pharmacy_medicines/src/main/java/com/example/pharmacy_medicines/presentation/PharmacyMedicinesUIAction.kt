package com.example.pharmacy_medicines.presentation

import com.example.model.enums.ScreenState

interface PharmacyMedicinesUIAction {
    object ToggleTopBarState: PharmacyMedicinesUIAction
    data class UpdateQuery(val newValue:String) : PharmacyMedicinesUIAction
    data class UpdateScreenState(val newState: ScreenState) : PharmacyMedicinesUIAction
    object Refresh: PharmacyMedicinesUIAction
}

interface PharmacyMedicinesNavigationActions{
    fun navigateUp()
    fun navigateToPharmacy(pharmacyId: Int)
    fun navigateToMedicineDetails(medicineId: Int)
}