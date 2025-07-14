package com.example.data.mapper.doctor

import com.example.model.medicine.MedicineData
import com.example.network.model.dto.medicine.MedicineDto
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