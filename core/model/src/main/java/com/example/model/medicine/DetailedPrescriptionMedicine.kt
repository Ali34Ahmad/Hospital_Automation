package com.example.model.medicine

data class DetailedPrescriptionMedicine(
    val id:Int,
    val medicine: PrescriptionMedicineMainInfo,
    val fulfilledBy:Int?
)

data class PrescriptionMedicineMainInfo(
    val id:Int,
    val name:String,
    val imageUrl:String?,
    val note:String?,
    val titer:Int,
)
