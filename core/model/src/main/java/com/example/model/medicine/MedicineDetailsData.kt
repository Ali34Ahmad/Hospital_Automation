package com.example.model.medicine

data class MedicineDetailsData(
    val medicineId: Int,
    val name: String,
    val pharmaceuticalTiter: Int,
    val pharmaceuticalIndications: String,
    val pharmaceuticalComposition: String,
    val price: Int,
    val imageUrl: String?,
    val companyName: String,
    val isAllowedWithoutPrescription: Boolean,
    val alternatives: List<MedicineSummaryData>
)