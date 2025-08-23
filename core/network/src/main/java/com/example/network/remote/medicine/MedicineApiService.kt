package com.example.network.remote.medicine

import com.example.network.model.dto.medicine.MedicineSummaryDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.medicine.GetAllMedicinesResponse
import com.example.network.model.response.medicine.GetMedicineByIdResponse
import com.example.network.utility.PagingAPIResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface MedicineApiService {
    suspend fun getAllMedicines(
        token: String,
        page: Int,
        limit: Int,
        name: String?
    ): Result<GetAllMedicinesResponse, NetworkError>

    suspend fun getMedicineById(
        token: String,
        medicineId: Int,
        role: RoleDto,
    ): Result<GetMedicineByIdResponse,NetworkError>

    suspend fun searchForMedicinesByPharmacyId(
        token: String,
        pharmacyId: Int,
        page: Int,
        limit: Int,
        name: String
    ): Result<PagingAPIResponse<MedicineSummaryDto>, NetworkError>
}