package com.example.medicine_details.presentation.preview

import com.example.model.medicine.MedicineDetailsData
import com.example.model.medicine.MedicineSummaryData

val mockList = listOf(
    MedicineSummaryData(
        id = 1,
        name = "Vitamin D3",
        pharmaceuticalTiter = 1000,
        imageUrl = null
    ), MedicineSummaryData(
        id = 2,
        name = "Paracitamol",
        pharmaceuticalTiter = 500,
        imageUrl = null
    ), MedicineSummaryData(
        id = 3,
        name = "Aspirin",
        pharmaceuticalTiter = 1000,
        imageUrl = null
    ),
)

val mockMedicine = MedicineDetailsData(
    medicineId = 10,
    name = "Omega 3",
    pharmaceuticalTiter = 1000,
    pharmaceuticalComposition = """
                Croscarmellose sodium + Sodium starch glycolate + Magnesium stearate + Lactose monohydrate
            """.trimIndent(),
    pharmaceuticalIndications = """
                Always consult with a qualified healthcare professional for any health concerns or before making any decisions regarding your health or treatment.
            """.trimIndent(),
    price = 18000,
    imageUrl = null,
    alternatives = mockList,
    isAllowedWithoutPrescription = true,
    companyName = "Al Fares"
)
