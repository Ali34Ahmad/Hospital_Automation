package com.example.medicines_search.presentation

import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineData

sealed interface MedicinesSearchUIAction{
    //top bar
    data class UpdateQuery(val newQuery: String): MedicinesSearchUIAction
    object Finish: MedicinesSearchUIAction

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

    //dialog
    data class AddNote(val medicineId: Int,val note: String): MedicinesSearchUIAction
    data class OpenNoteDialog(val medicineId: Int): MedicinesSearchUIAction
    object CloseNoteDialog: MedicinesSearchUIAction
    data class UpdateNote(val newNote: String): MedicinesSearchUIAction

}

interface MedicinesSearchNavigationActions{
    fun navigateUp()
    fun navigateToPharmacies(medicineId: Int)
    fun navigateToMedicineDetails(medicineId: Int)
    fun navigateToAppointmentDetails(appointmentId: Int)
}