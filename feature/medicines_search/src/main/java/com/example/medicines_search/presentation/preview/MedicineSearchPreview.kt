package com.example.medicines_search.presentation.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.medicines_search.presentation.MedicinesSearchNavigationActions
import com.example.medicines_search.presentation.MedicinesSearchScreen
import com.example.medicines_search.presentation.MedicinesSearchUIState
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineData
import com.example.ui.theme.Hospital_AutomationTheme
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MedicineSearchPreview() {
    Hospital_AutomationTheme {
        MedicinesSearchScreen(
            uiState = MedicinesSearchUIState(
                doctorId = 1,
                appointmentId = 1,
                isRefreshing = false,
                screenState = ScreenState.LOADING,
                query = "",
                prescriptionMedicines = mockMedicines,
                toastMessage = null,
                isBottomSheetOpened = false,
                selectedMedicines = mockMedicines.map { it to "" },
                isNoteDialogOpened = true,
                dialogMedicine = mockMedicines[0],
                dialogNote = ""
            ),
            onAction = {},
            navigationActions = mockNavigation,
            medicines = fakeMedicinesFlow.collectAsLazyPagingItems(),
            modifier = Modifier.fillMaxSize()
        )
    }
}

val mockNavigation = object : MedicinesSearchNavigationActions{
    override fun navigateUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToPharmacies(medicineId: Int) {
        TODO("Not yet implemented")
    }

    override fun navigateToMedicineDetails(medicineId: Int) {
        TODO("Not yet implemented")
    }

    override fun navigateToAppointmentDetails(appointmentId: Int) {
        TODO("Not yet implemented")
    }
}
val mockMedicines = listOf(
    MedicineData(
        medicineId = 1,
        name = "Citamol",
        pharmaceuticalTiter = 1000,
        pharmaceuticalIndications = "no indications",
        pharmaceuticalComposition = "",
        companyName = "Ali's Company",
        price = 12000,
        isAllowedWithoutPrescription = true,
        barcode = "12345",
        medImageUrl = "",
        numberOfPharmacies = 4
    ),
    MedicineData(
        medicineId = 2,
        name = "Omega'3",
        pharmaceuticalTiter = 1000,
        pharmaceuticalIndications = "no indications",
        pharmaceuticalComposition = "",
        companyName = "Ali's Company",
        price = 12000,
        isAllowedWithoutPrescription = true,
        barcode = "12345",
        medImageUrl = "",
        numberOfPharmacies = 4
    )
)
private val fakeMedicinesFlow = flowOf(
    PagingData.from(mockMedicines)
)