package com.example.medicines_search.presentation.preview

import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineData

sealed interface MedicinesSearchUIAction{
    //top bar
    object ToggleTopBarState: MedicinesSearchUIAction
    data class UpdateQuery(val newQuery: String): MedicinesSearchUIAction

    //screen
    data class UpdateScreenState(val newState: ScreenState): MedicinesSearchUIAction
    object Refresh: MedicinesSearchUIAction

    //bottom sheet
    data class AddMedicineToPrescription(val medicine: MedicineData): MedicinesSearchUIAction
    data class RemoveMedicineFromPrescription(val medicine: MedicineData): MedicinesSearchUIAction
    object OpenBottomSheet : MedicinesSearchUIAction
    object  CloseBottomSheet: MedicinesSearchUIAction

    //bottom bar
    object Clear: MedicinesSearchUIAction
    object Finish: MedicinesSearchUIAction
    data class UpdateClearButtonState(val newState: BottomBarState)
    data class UpdateFinishClearButtonState(val newState: BottomBarState)

}

interface MedicinesSearchNavigationActions{
    fun navigateUp()
    fun navigateToPharmacies(medicineId: Int)
}