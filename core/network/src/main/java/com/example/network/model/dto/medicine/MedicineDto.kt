package com.example.network.model.dto.medicine

import com.example.network.model.response.NetworkPagination
import com.example.network.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Date
import java.time.LocalDateTime


//don't forget the number of pharmacies.
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
    val medImageUrl: String,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("pharmacy_medicines")
    val numberOfPharmaciesList: List<NumberOfPharmacies>,
    val pagination: NetworkPagination,
)
@Serializable
data class NumberOfPharmacies(
    val numOfPharmacies : Int
)