package com.example.network.remote.prescription

import android.util.Log
import com.example.network.model.dto.prescription.PrescriptionMedicineDto
import com.example.network.model.request.prescription.PrescriptionMedicineField
import com.example.network.model.response.prescription.AddPrescriptionResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.utils.io.InternalAPI
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder

const val PRESCRIPTION_TAG = "prescription api service"
class PrescriptionApiServiceImp(
    private val client: HttpClient
): PrescriptionApiService{

    @OptIn(InternalAPI::class)
    override suspend fun addPrescription(
        token: String,
        patientId: Int?,
        childId: Int?,
        note: String,
        medicines: List<PrescriptionMedicineDto>,
    ): Result<AddPrescriptionResponse, NetworkError> {

        if(!isExactlyOneInputProvided(patientId,childId)) {
            return Result.Error(NetworkError.INVALID_PARAMETERS)
        }
        val jsonPayload = Json.encodeToString(
            mapOf("medicineInfo" to medicines)
        )
        Log.d(PRESCRIPTION_TAG,"json : \n $jsonPayload")

        val response = try {
            client.submitForm(
                url = ApiRoutes.Prescription.ADD_PRESCRIPTION,
                formParameters = Parameters.build {
                    append("note", note)
                    append("medicineInfo", jsonPayload)
                }
            ) {
                url {
                    patientId?.let { parameter("patient_id", it.toString()) }
                    childId?.let { parameter("child_id", it.toString()) }
                }
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(PRESCRIPTION_TAG,e.message.toString())
            return Result.Error(NetworkError.UNKNOWN)
        }
        Log.d(PRESCRIPTION_TAG,response.request.toString())
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(PRESCRIPTION_TAG,"Success : ${response.bodyAsText()}")
                val body: AddPrescriptionResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(PRESCRIPTION_TAG, "Unauthorized : ${response.status.description}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(PRESCRIPTION_TAG, "Unknown : ${response.status.description}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    /**
     * Returns true if **exactly one** of the two inputs is non-null,
     * meaning the user provided either input1 or input2, but not both.
     */
    fun  isExactlyOneInputProvided(input1: Any?, input2: Any?): Boolean =
        (input1 == null) xor (input2 == null)
}

