package com.example.pharmacies_search.presentation.preview

import androidx.paging.PagingData
import com.example.model.pharmacy.PharmacistData
import com.example.model.pharmacy.PharmacyData
import com.example.pharmacies_search.presentation.PharmaciesSearchNavigationActions
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate

private val pharmacies = listOf(
    PharmacyData(
        pharmacyId = 1,
        name = "BBA",
        addressGovernorate = "Syria",
        addressCity = "Lattkia",
        addressRegion = "Lattakia",
        addressStreet = "Al Zera'a",
        addressNote = "next to the bakery",
        phoneNumber = "0994345893",
        isDeactivated = false,
        deactivationReason = null,
        startDate = LocalDate.now().minusYears(10),
        endDate = null,
        deactivatedBy = null,
        pharmacist = PharmacistData(
            id = 1,
            firstName = "Ali",
            middleName = "Bassam",
            lastName = "Mansoura",
            imageUrl = ""
        )
    ),
    PharmacyData(
        pharmacyId = 2,
        name = "Al Hilal",
        addressGovernorate = "Syria",
        addressCity = "Lattkia",
        addressRegion = "Lattakia",
        addressStreet = "Al Zera'a",
        addressNote = "next to the bakery",
        phoneNumber = "0994345893",
        isDeactivated = false,
        deactivationReason = null,
        startDate = LocalDate.now().minusYears(10),
        endDate = null,
        deactivatedBy = null,
        pharmacist = PharmacistData(
            id = 2,
            firstName = "Hadel",
            middleName = "Omran",
            lastName = "Tareq",
            imageUrl = ""
        )
    ),
    PharmacyData(
        pharmacyId = 3,
        name = "Al Mahabba",
        addressGovernorate = "Syria",
        addressCity = "Lattkia",
        addressRegion = "Lattakia",
        addressStreet = "Al Zera'a",
        addressNote = "next to the bakery",
        phoneNumber = "0994345893",
        isDeactivated = false,
        deactivationReason = null,
        startDate = LocalDate.now().minusYears(10),
        endDate = null,
        deactivatedBy = null,
        pharmacist = PharmacistData(
            id = 3,
            firstName = "Zahra",
            middleName = "Samer",
            lastName = "Samer",
            imageUrl = ""
        )
    )
)

internal val pharmaciesFlow = flowOf(
    PagingData.from(pharmacies)
)

internal val mockNavigation = object : PharmaciesSearchNavigationActions{
    override fun navigateToPharmacyDetails(pharmacyId: Int) {

    }

    override fun navigateToAdminProfile() {
    }

    override fun navigateToVaccines() {
    }

    override fun navigateToNotifications() {
    }

    override fun navigateToPrescriptions() {

    }

    override fun navigateToMedicalRecords() {
    }

    override fun navigateToVaccineTable() {
    }
}