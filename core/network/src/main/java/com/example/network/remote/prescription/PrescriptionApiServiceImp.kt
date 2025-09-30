package com.example.network.remote.prescription

import android.util.Log
import com.example.network.model.dto.prescription.PrescriptionMedicineDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.prescription.AddPrescriptionResponse
import com.example.network.model.response.prescription.GetAllPrescriptionsResponseDto
import com.example.network.model.response.prescription.PrescriptionDetailsWithMedicinesDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.utils.io.InternalAPI
import kotlinx.serialization.json.Json
import kotlin.toString

private const val PRESCRIPTION_TAG = "PrescriptionApi"

class PrescriptionApiServiceImp(
    private val client: HttpClient
) : PrescriptionApiService {

    @OptIn(InternalAPI::class)
    override suspend fun addPrescription(
        token: String,
        patientId: Int?,
        childId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineDto>,
    ): Result<AddPrescriptionResponse, NetworkError> {

        if (!isExactlyOneInputProvided(patientId, childId)) {
            return Result.Error(NetworkError.INVALID_PARAMETERS)
        }
        val jsonPayload = Json.encodeToString(
            mapOf("medicineInfo" to medicines)
        )
        Log.d(PRESCRIPTION_TAG, "json : \n $jsonPayload")
        return doApiCall(
            tag = PRESCRIPTION_TAG
        ) {
            client.submitForm(
                url = ApiRoutes.Prescription.ADD_PRESCRIPTION,
                formParameters = Parameters.build {
                    if (note.isNotBlank()) {
                        append("note", note)
                    }
                    append("medicineInfo", jsonPayload)
                }
            ) {
                url {
                    patientId?.let { parameter("patient_id", it.toString()) }
                    childId?.let { parameter("child_id", it.toString()) }
                }
                bearerAuth(token)
            }
        }
    }

    /**
     * Returns true if **exactly one** of the two inputs is non-null,
     * meaning the user provided either input1 or input2, but not both.
     */
    fun isExactlyOneInputProvided(input1: Any?, input2: Any?): Boolean =
        (input1 == null) xor (input2 == null)

    override suspend fun getAllPrescriptions(
        token: String,
        page: Int,
        limit: Int,
        role: RoleDto,
        patientId: Int?,
        childId: Int?,
        name: String?,
        doctorId: Int?,
    ): Result<GetAllPrescriptionsResponseDto, NetworkError> =
        doApiCall<GetAllPrescriptionsResponseDto>(
            tag = PRESCRIPTION_TAG
        ) {
            Log.d(PRESCRIPTION_TAG, "getAllPrescriptions")

            val routeSuffix = if (doctorId != null) "/$doctorId"
            else ""
            client.get("${ApiRoutes.getPrescriptionsEndPointForRole(role)}$routeSuffix") {
                url {
                    parameter("page", page)
                    parameter("limit", limit)
                    when {
                        name != null -> {
                            parameter("filter", "NAME")
                            parameter("name", name)
                        }

                        patientId != null -> {
                            parameter("filter", "ID")
                            parameter(
                                "who",
                                "patient"
                            )
                            parameter("id", patientId)
                        }

                        childId != null -> {
                            parameter("filter", "ID")
                            parameter(
                                "who",
                                "child"
                            )
                            parameter("id", childId)
                        }

                        else -> {
                            parameter("filter", "NONE")
                        }
                    }
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun getPrescriptionDetailsById(
        token: String, id: Int,
        role: RoleDto
    ): Result<PrescriptionDetailsWithMedicinesDto, NetworkError> =
        doApiCall<PrescriptionDetailsWithMedicinesDto>(
            tag = PRESCRIPTION_TAG
        ) {
            Log.d(PRESCRIPTION_TAG, "getPrescriptionDetailsById")

            client.get("${ApiRoutes.getPrescriptionDetailsByIdEndPoint(role)}/$id") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }
}

