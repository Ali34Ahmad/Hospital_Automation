package com.example.network.remote.pharmacy

import com.example.network.model.response.pharmacy.GetPharmaciesByMedicineIdResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get

private const val PHARMACY_TAG = "PharmacyApiService"
class PharmacyApiServiceImp(
    private val client: HttpClient
): PharmacyApiService {
    override suspend fun getPharmaciesByMedicineId(
        token: String,
        medicineId: Int,
    ): Result<GetPharmaciesByMedicineIdResponse, NetworkError> =

        doApiCall<GetPharmaciesByMedicineIdResponse>(
            tag = PHARMACY_TAG,
        ){
            client.get(ApiRoutes.Pharmacy.PHARMACIES_BY_MEDICINE_ID+"/$medicineId") {
                bearerAuth(token)
            }
        }

}
