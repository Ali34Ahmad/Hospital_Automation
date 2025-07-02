package com.example.network.remote.medicine

import com.example.network.model.response.medicine.GetAllMedicinesResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface MedicineApiService {
    suspend fun getAllMedicines(
        token: String,
        page: Int,
        limit: Int,
        name: String?
    ): Result<GetAllMedicinesResponse, NetworkError>
}