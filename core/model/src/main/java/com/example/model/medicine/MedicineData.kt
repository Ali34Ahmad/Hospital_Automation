package com.example.model.medicine

data class MedicineData(
    val medicineId : Int,
    val name: String,
    val pharmaceuticalTiter: Int,
    val pharmaceuticalIndications: String,
    val pharmaceuticalComposition: String,
    val companyName: String,
    val price: Int,
    val isAllowedWithoutPrescription: Boolean,
    val barcode: String,
    val medImageUrl: String,
    val numberOfPharmacies: Int
)
