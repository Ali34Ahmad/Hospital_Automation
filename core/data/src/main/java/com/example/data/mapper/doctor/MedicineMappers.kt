package com.example.data.mapper.doctor

import com.example.model.medicine.MedicineData
import com.example.model.medicine.MedicineDetailsData
import com.example.model.medicine.MedicineSummaryData
import com.example.network.model.dto.medicine.MedicineDetailsDto
import com.example.network.model.dto.medicine.MedicineDto
import com.example.network.model.dto.medicine.MedicineSummaryDto
import com.example.network.utility.ApiRoutes

fun MedicineDto.toMedicineData() =
    MedicineData(
        medicineId = medicineId,
        name = name,
        pharmaceuticalTiter = pharmaceuticalTiter,
        pharmaceuticalIndications = pharmaceuticalIndications,
        pharmaceuticalComposition = pharmaceuticalComposition,
        companyName = companyName,
        price = price,
        isAllowedWithoutPrescription = isAllowedWithoutPrescription,
        barcode = barcode,
        medImageUrl = "${ApiRoutes.BASE_URL}/$medImageUrl",
        numberOfPharmacies = numberOfPharmaciesList.size
    )
fun MedicineSummaryDto.toMedicineSummaryData() = MedicineSummaryData(
    id = id,
    name = name,
    pharmaceuticalTiter = pharmaceuticalTiter,
    imageUrl = imageUrl
)


fun MedicineDetailsDto.toMedicineDetailsData() = MedicineDetailsData(
    medicineId = medicineId,
    name = name,
    pharmaceuticalTiter = pharmaceuticalTiter,
    pharmaceuticalIndications = pharmaceuticalIndications,
    pharmaceuticalComposition = pharmaceuticalComposition,
    price = price,
    imageUrl = imageUrl,
    companyName = companyName,
    isAllowedWithoutPrescription = isAllowedWithoutPrescription,
    alternatives = alternatives.map {alternative ->
        alternative.medicine.toMedicineSummaryData()
    }
)