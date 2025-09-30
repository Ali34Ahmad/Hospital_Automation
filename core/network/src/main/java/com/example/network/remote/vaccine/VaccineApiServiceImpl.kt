package com.example.network.remote.vaccine

import android.util.Log
import com.example.network.model.dto.vaccine.ChildVaccinationTableDto
import com.example.network.model.dto.vaccine.GenericVaccinationTableDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.vaccine.InteractionsWrapper
import com.example.network.model.request.vaccine.VaccineIdToVisitNumberDto
import com.example.network.model.request.vaccine.VaccinesIdsToVisitNumberDto
import com.example.network.model.response.vaccine.GetAllVaccinesResponseDto
import com.example.network.model.response.vaccine.VaccineResponseDto
import com.example.network.model.response.vaccine.VaccinesMainInfoListWrapperDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

const val VACCINE_TAG = "VaccineApi"

class VaccineApiServiceImpl(
    private val client: HttpClient,
) : VaccineApiService {
    override suspend fun getAllVaccines(
        token: String,
        page: Int,
        limit: Int,
        role: RoleDto,
    ): Result<GetAllVaccinesResponseDto, NetworkError> =
        doApiCall<GetAllVaccinesResponseDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "getAllVaccines")

            client.get(ApiRoutes.getAllVaccinesEndPointForRole(role)) {
                url {
                    parameter("page", page)
                    parameter("limit", limit)
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun getVaccinesWithNoVisitNumber(
        token: String
    ): Result<VaccinesMainInfoListWrapperDto, NetworkError> =
        doApiCall<VaccinesMainInfoListWrapperDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "getVaccinesWithNoVisitNumber")

            client.get(ApiRoutes.Doctor.GET_VACCINE_WITH_NO_VISIT_NUMBER) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun addNewVaccine(
        token: String,
        vaccineDto: VaccineDto,
    ): Result<VaccineResponseDto, NetworkError> =
        doApiCall<VaccineResponseDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "addNewVaccine")

            val interactionsWrapper = InteractionsWrapper(vaccineDto.interactions)
            val interactionsJsonString = Json.encodeToString(interactionsWrapper)
            client.post(ApiRoutes.Doctor.ADD_NEW_VACCINE) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("name", vaccineDto.name)
                            append("description", vaccineDto.description)
                            append("quantity", vaccineDto.quantity)
                            append("minAge", vaccineDto.minAge)
                            append("maxAge", vaccineDto.maxAge)
                            append("minAgeUnit", vaccineDto.minAgeUnit.toString())
                            append("maxAgeUnit", vaccineDto.maxAgeUnit.toString())
                            append("interactions", interactionsJsonString)
                        }
                    ))
            }
        }

    override suspend fun getVaccineById(
        token: String,
        id: Int,
        role: RoleDto
    ): Result<VaccineResponseDto, NetworkError> =
        doApiCall<VaccineResponseDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "getVaccineById")

            client.get("${ApiRoutes.getVaccineDetailsEndPointForRole(role)}/$id") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun getGenericVaccinationTable(
        token: String,
        roleDto: RoleDto
    ): Result<GenericVaccinationTableDto, NetworkError> =
        doApiCall<GenericVaccinationTableDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "getGenericVaccinationTable")

            client.get(ApiRoutes.getGenericVaccinationTableEndPoint(role = roleDto)) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun updateVaccineVisitNumber(
        token: String,
        vaccineIdToVisitNumberDto: VaccineIdToVisitNumberDto
    ): Result<GenericVaccinationTableDto, NetworkError> =
        doApiCall<GenericVaccinationTableDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "updateVaccineVisitNumber")

            client.post(ApiRoutes.Doctor.UPDATE_VACCINE_VISIT_NUMBER) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(vaccineIdToVisitNumberDto)
            }
        }

    override suspend fun updateVaccinesVisitNumber(
        token: String,
        vaccinesIdsToVisitNumberDto: VaccinesIdsToVisitNumberDto
    ): Result<GenericVaccinationTableDto, NetworkError> =
        doApiCall<GenericVaccinationTableDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "updateVaccinesVisitNumber")

            client.post(ApiRoutes.Doctor.UPDATE_VACCINES_LIST_VISIT_NUMBER) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            if (vaccinesIdsToVisitNumberDto.visitNumber != null) {
                                append("visit_number", vaccinesIdsToVisitNumberDto.visitNumber)
                            }
                            append(
                                "vaccinesIds",
                                vaccinesIdsToVisitNumberDto.vaccinesIds.toString()
                            )
                        }
                    ))
            }
        }

    override suspend fun getChildVaccinationTable(
        token: String,
        childId: Int
    ): Result<ChildVaccinationTableDto, NetworkError> =
        doApiCall<ChildVaccinationTableDto>(
            tag = VACCINE_TAG
        ) {
            Log.d(VACCINE_TAG, "getChildVaccinationTable")

            client.get("${ApiRoutes.Admin.GET_CHILD_VACCINATION_TABLE}/$childId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

}