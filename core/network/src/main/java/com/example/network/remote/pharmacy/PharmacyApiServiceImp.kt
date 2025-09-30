package com.example.network.remote.pharmacy

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.pharmacy.GetPharmaciesByMedicineIdResponse
import com.example.network.model.response.pharmacy.PharmacyDetailsResponseDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val PHARMACY_TAG = "PharmacyApiService"

class PharmacyApiServiceImp(
    private val client: HttpClient
) : PharmacyApiService {
    override suspend fun getPharmacyDetailsById(
        token: String,
        pharmacyId: Int,
        role: RoleDto,
    ): Result<PharmacyDetailsResponseDto, NetworkError> =
        doApiCall<PharmacyDetailsResponseDto>(
            tag = PHARMACY_TAG
        ) {
            Log.d(PHARMACY_TAG, "getPharmacyDetailsById")

            client.get("${ApiRoutes.getPharmacyDetailsByIdEndPoint(role)}/$pharmacyId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun getPharmaciesByMedicineId(
        token: String,
        medicineId: Int,
    ): Result<GetPharmaciesByMedicineIdResponse, NetworkError> =

        doApiCall<GetPharmaciesByMedicineIdResponse>(
            tag = PHARMACY_TAG,
        ) {
            client.get(ApiRoutes.Pharmacy.PHARMACIES_BY_MEDICINE_ID + "/$medicineId") {
                bearerAuth(token)
            }
        }

}
