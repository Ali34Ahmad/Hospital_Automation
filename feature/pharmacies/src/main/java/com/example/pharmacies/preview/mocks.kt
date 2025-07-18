package com.example.pharmacies.preview

import com.example.model.pharmacy.PharmacistData
import com.example.model.pharmacy.PharmacyData
import com.example.pharmacies.presentaion.PharmaciesNavigationActions
import java.time.LocalDate

val mockNavigationAction = object : PharmaciesNavigationActions{
    override fun navigateBack() {
        TODO("Not yet implemented")
    }

    override fun navigateToPharmacyDetails(pharmacyId: Int) {
        TODO("Not yet implemented")
    }
}
private val ali = PharmacistData(
    id = 1,
    firstName = "Ali",
    middleName = "Bassam",
    lastName = "Mansoura",
    imageUrl = null
)
private val ahmad = PharmacistData(
    id = 2,
    firstName = "Ahmad",
    middleName = "Milad",
    lastName = "Al Ali",
    imageUrl = null
)
val mockList = listOf(
    PharmacyData(
        pharmacyId = 1,
        name = "Al Hanan",
        addressGovernorate = "Syria",
        addressCity = "Damascus",
        addressRegion = "Al maza",
        addressStreet = "Abo Ali streen",
        addressNote = "next to the bakery",
        phoneNumber = "0998432456",
        isDeactivated = false,
        deactivationReason = null,
        startDate = LocalDate.now().minusWeeks(10),
        endDate = LocalDate.now().plusDays(10),
        deactivatedBy = null,
        pharmacist = ali
    ),
    PharmacyData(
        pharmacyId = 2,
        name = "Al Salam",
        addressGovernorate = "Syria",
        addressCity = "Lattakia",
        addressRegion = "Bassnada",
        addressStreet = "Domino's streen",
        addressNote = "opposite the caffe",
        phoneNumber = "0998432456",
        isDeactivated = false,
        deactivationReason = null,
        startDate = LocalDate.now().minusWeeks(10),
        endDate = LocalDate.now().plusDays(10),
        deactivatedBy = null,
        pharmacist = ahmad
    ),
)