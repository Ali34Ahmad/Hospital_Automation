package com.example.pharmacy_medicines.presentation.preview

import androidx.paging.PagingData
import com.example.model.medicine.MedicineSummaryData
import com.example.pharmacy_medicines.presentation.PharmacyMedicinesNavigationActions
import kotlinx.coroutines.flow.flowOf

internal val fakeNavigationActions = object : PharmacyMedicinesNavigationActions{
    override fun navigateUp() {

    }

    override fun navigateToPharmacy(pharmacyId: Int) {

    }

    override fun navigateToMedicineDetails(medicineId: Int) {

    }
}

private val medicinesList = listOf(
    MedicineSummaryData(
        id = 1,
        name = "Para cetamol",
        pharmaceuticalTiter = 500,
        imageUrl = "",
        price = 10000
    ),
    MedicineSummaryData(
        id = 2,
        name = "Panadol",
        pharmaceuticalTiter = 1000,
        imageUrl = "",
        price = 15000
    ),
    MedicineSummaryData(
        id = 3,
        name = "Omparazol",
        pharmaceuticalTiter = 1000,
        imageUrl = "",
        price = 20000
    ),
    MedicineSummaryData(
        id = 4,
        name = "Vitamine D3",
        pharmaceuticalTiter = 5000,
        imageUrl = "",
        price = 6000
    ),
)
internal val medicinesFlow = flowOf(
    PagingData.from(medicinesList)
)