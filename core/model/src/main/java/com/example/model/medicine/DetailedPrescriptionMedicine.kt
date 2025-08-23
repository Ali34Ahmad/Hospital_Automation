package com.example.model.medicine

data class DetailedPrescriptionMedicine(
    val id:Int,
    val medicine: PrescriptionMedicineMainInfo,
    val fulfilledBy:Int?,
    val note:String?,
)

data class PrescriptionMedicineMainInfo(
    val id:Int,
    val name:String,
    val imageUrl:String?,
    val titer:Int?,
)
