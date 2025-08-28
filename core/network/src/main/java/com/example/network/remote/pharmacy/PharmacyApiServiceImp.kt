package com.example.network.remote.pharmacy

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.pharmacy.GetPharmaciesByMedicineIdResponse
import com.example.network.model.response.pharmacy.PharmacyDetailsResponseDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val PHARMACY_TAG = "PharmacyApiService"
class PharmacyApiServiceImp(
    private val client: HttpClient
): PharmacyApiService {
    override suspend fun getPharmacyDetailsById(
        token: String,
        id:Int,
        role: RoleDto,
    ) : Result<PharmacyDetailsResponseDto, NetworkError> =try {
        val response = client.get("${ApiRoutes.getPharmacyDetailsByIdEndPoint(role)}/$id") {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val pharmacyDetails: PharmacyDetailsResponseDto = response.body()
                Log.v("PharmacyDetailsApi:In Range 2xx", pharmacyDetails.toString())
                Result.Success(data = pharmacyDetails)
            }

            else -> {
                val errorMessage: NetworkMessage = response.body()
                Log.v("PharmacyDetailsApi:Out of Range 2xx", errorMessage.message)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.v("PharmacyDetailsApi:Exception", e.message ?: "Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }

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
