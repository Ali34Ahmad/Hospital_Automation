package com.example.network.model.dto.medicine

import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MedicineDto(
    @SerialName("medicinesId")
    val medicineId : Int,
    val name: String,
    val pharmaceuticalTiter: Int,
    val pharmaceuticalIndications: String,
    val pharmaceuticalComposition: String,
    @SerialName("company_Name")
    val companyName: String,
    val price: Int,
    val isAllowedWithoutPrescription: Boolean,
    val barcode: String,
    val medImageUrl: String? = null,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("pharmacy_medicines")
    val numberOfPharmaciesList: List<NumberOfPharmacies>,
)
@Serializable
data class NumberOfPharmacies(
    @SerialName("pharmacy_id")
    val pharmacyId : Int
)
