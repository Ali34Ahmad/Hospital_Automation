package com.example.network.remote.upload_employee_documents

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.response.ProgressUpdateDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.file.FileReader
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File

class UploadEmployeeDocumentsApiImpl(
    private val client: HttpClient,
    private val fileReader: FileReader,
    private val userPreferencesRepository: UserPreferencesRepository,
) : UploadEmployeeDocumentsApi {
    @Volatile // Ensure visibility across threads if isPaused is modified externally
    private var isPaused = false
    private var currentChunkIndex = 0 // Consider persisting this for robust resume

    private fun readFileInChunks(file: File, chunkSize: Int): List<ByteArray> {
        val chunks = mutableListOf<ByteArray>()
        file.inputStream().use { inputStream ->
            val buffer = ByteArray(chunkSize)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                chunks.add(buffer.copyOf(bytesRead))
            }
        }
        return chunks
    }

    private fun uploadChunk(
        chunk: ByteArray,
        chunkIndex: Int, // Renamed for clarity
        totalChunks: Int,
        originalFileName: String // Pass original file name for server
    ): Flow<ProgressUpdateDto> = channelFlow {
        val token = userPreferencesRepository.userPreferencesFlow.first().token

        if (token.isNullOrBlank()) {
            close(IllegalStateException("Bearer token is not available."))
            return@channelFlow
        }

        Log.d("UploadChunk", "AuthToken: ${token.take(10)}...") // Avoid logging full token
        try {
            val response = client.submitFormWithBinaryData(
                url = ApiRoutes.UPLOAD_EMPLOYEE_DOCUMENTS, // Ensure this endpoint can handle chunks
                formData = formData {
                    append("fileChunk", chunk, Headers.build { // Changed key to "fileChunk"
                        append(HttpHeaders.ContentType, "application/octet-stream")
                        // Server needs to know chunk index, total chunks, and original filename
                        // to reassemble the file. You might send these as separate form fields
                        // or as part of the filename/headers if your server supports it.
                        append(
                            HttpHeaders.ContentDisposition,
                            "filename=\"${originalFileName}_part_$chunkIndex\""
                        )
                    })
                    // It's crucial the server knows how to reassemble these parts.
                    // You might need to send additional metadata:
                    append("chunkIndex", chunkIndex.toString())
                    append("totalChunks", totalChunks.toString())
                    append("originalFileName", originalFileName)

                    Log.v(
                        "Uploading File Client",
                        "Chunk: $chunkIndex / $totalChunks for $originalFileName"
                    )
                }
            ) {
                method = HttpMethod.Post
                bearerAuth(token)
                onUpload { bytesSent, totalBytes ->
                    if ((totalBytes ?: 0) > 0L) { // Simpler check
                        // This progress is for the current chunk
                        send(ProgressUpdateDto(bytesSent, totalBytes ?: 0))
                        Log.v(
                            "Uploading Chunk Progress",
                            "Chunk $chunkIndex: $bytesSent/$totalBytes"
                        )
                    }
                }
            }

            when (response.status.value) {
                in 200..299 -> {
                    Log.v(
                        "UploadChunk",
                        "Chunk ${chunkIndex + 1} for $originalFileName uploaded successfully: ${response.status}"
                    )
                    // Optionally, you can send a specific DTO indicating chunk success if needed by the collector
                    // send(ProgressUpdateDto(chunk.size.toLong(), chunk.size.toLong(), true, fileTotalSize, fileTotalSize)) // Signify chunk completion
                    channel.trySend(
                        ProgressUpdateDto(
                            chunk.size.toLong(),
                            chunk.size.toLong()*totalChunks,
                        )
                    ).isSuccess
                }

                else -> {
                    val errorBody = response.body<String>()
                    val errorMessage =
                        "Chunk ${chunkIndex + 1} upload failed: ${response.status}, Body: $errorBody"
                    Log.e("UploadChunk", errorMessage)
                    close(Exception(errorMessage)) // Close the flow with an error
                }
            }
        } catch (e: CancellationException) {
            Log.i("UploadChunk", "Chunk ${chunkIndex + 1} upload cancelled.", e)
            close(e) // Propagate cancellation
        } catch (e: Exception) {
            val errorMessage = "Chunk ${chunkIndex + 1} upload exception: ${e.message}"
            Log.e("UploadChunk", errorMessage, e)
            close(Exception(errorMessage, e)) // Close the flow with an error
        }

    }

    fun uploadFileInChunks(uri: Uri, chunkSize: Int): Flow<ProgressUpdateDto> = channelFlow {
        val file = fileReader.uriToFile(uri)

        if (file == null || !file.exists()) {
            val error = Exception("Upload failed: File not found or not accessible at URI $uri")
            Log.e("UploadFileInChunks", error.message ?: "File error for URI: $uri")
            close(error)
            return@channelFlow
        }

        val originalFileName = file.name
        val fileTotalSize = file.length()

        if (fileTotalSize == 0L) {
            Log.w("UploadFileInChunks", "File $originalFileName is empty. Reporting as complete.")
            // Send a completion event for an empty file
            send(
                ProgressUpdateDto(
                    bytesSent = 0,
                    totalBytes=0,
                )
            )
            close()
            return@channelFlow
        }

        // --- Option 1: Read all chunks into memory (as per your existing readFileInChunks) ---
        val chunks = readFileInChunks(file, chunkSize)
        if (chunks.isEmpty() && fileTotalSize > 0) {
            val error = Exception("Failed to read any chunks from file: $originalFileName")
            Log.e("UploadFileInChunks", error.message!!)
            close(error)
            return@channelFlow
        }

        var cumulativeBytesSent = 0L

        launch {
            try {
                chunks.forEachIndexed { index, chunk ->
                    if (isPaused) {
                        Log.i("UploadFileInChunks", "Upload paused at chunk $index for $originalFileName")
                        // Potentially emit a specific ProgressUpdateDto for paused state if needed
                        // For now, it just stops sending more chunks until isPaused is false
                        // A more robust resume would require saving currentChunkIndex and restarting from there.
                        // This simplistic pause will require restarting the flow or re-evaluating `isPaused`.
                        return@launch // Exit this inner launch, effectively pausing sending new chunks.
                        // The outer channelFlow remains open unless closed explicitly.
                    }
                    currentChunkIndex = index // Track the current chunk being processed

                    Log.d("UploadFileInChunks", "Starting upload for chunk ${index + 1}/${chunks.size} of $originalFileName")

                    uploadChunk(chunk, index, chunks.size, originalFileName)
                        .onEach { chunkProgress ->
                            val overallProgressBytesSent = cumulativeBytesSent + chunkProgress.bytesSent
                            send(ProgressUpdateDto(overallProgressBytesSent, fileTotalSize))

                            Log.v("UploadFileInChunks", "Overall progress: $overallProgressBytesSent / $fileTotalSize for $originalFileName (Chunk ${index + 1}: ${chunkProgress.bytesSent}/${chunkProgress.totalBytes})")
                        }
                        .onCompletion { cause ->
                            if (cause == null) {
                                // Current chunk completed successfully
                                cumulativeBytesSent += chunk.size
                                Log.i("UploadFileInChunks", "Chunk ${index + 1}/${chunks.size} for $originalFileName completed. Total sent: $cumulativeBytesSent/$fileTotalSize")
                                // If this was the last chunk, the flow will be closed after this loop.
                                if (index == chunks.size - 1) {
                                    // Ensure a final 100% progress event if not already sent.
                                    if (cumulativeBytesSent == fileTotalSize) {
                                        send(ProgressUpdateDto(fileTotalSize, fileTotalSize))
                                        Log.i("UploadFileInChunks", "All chunks for $originalFileName uploaded successfully. Total: $fileTotalSize/$fileTotalSize")
                                    }
                                }
                            } else {
                                // Error occurred in uploadChunk
                                Log.e("UploadFileInChunks", "Error uploading chunk ${index + 1} for $originalFileName.", cause)
                                close(cause) // Propagate error and close the main flow
                            }
                        }

                    // If the channel was closed due to an error in uploadChunk, stop processing further chunks.
                    if (isClosedForSend) {
                        Log.w("UploadFileInChunks", "Channel closed, stopping chunk upload for $originalFileName.")
                        return@launch
                    }
                }
                // If the loop completes without errors and the channel is still open, close it.
                if (!isClosedForSend) {
                    Log.i("UploadFileInChunks", "Finished processing all chunks for $originalFileName.")
                    close()
                }
            } catch (e: CancellationException) {
                Log.i("UploadFileInChunks", "Upload for $originalFileName was cancelled.", e)
                close(e) // Propagate cancellation
            } catch (e: Exception) {
                Log.e("UploadFileInChunks", "Exception during chunk processing for $originalFileName: ${e.message}", e)
                close(e) // Close with exception
            }
        }
    }

    override fun uploadFile(uri: Uri): Flow<ProgressUpdateDto> = channelFlow {
        val info = fileReader.uriToFileInfo(uri)

        val token = userPreferencesRepository.userPreferencesFlow.first().token

        if (token.isNullOrBlank()) {
            close(IllegalStateException("Bearer token is not available."))
            return@channelFlow
        }

        println("AuthToken: $token")

        try {
            val response = client.submitFormWithBinaryData(
                url = ApiRoutes.UPLOAD_EMPLOYEE_DOCUMENTS,
                formData = formData {
                    append(
                        "image",
                        info.bytes,
                        Headers.build {
                            append(HttpHeaders.ContentType, info.mimeType)
                            append(HttpHeaders.ContentDisposition, "filename=\"${info.name}\"")
                        }
                    )
                    Log.v("Uploading File Client", info.name + "." + info.mimeType)
                }
            ) {
                method = HttpMethod.Post
                bearerAuth(token)
                onUpload { bytesSent, totalBytes ->
                    if ((totalBytes ?: 0) > 0L) {
                        send(ProgressUpdateDto(bytesSent, totalBytes ?: 0))
                        Log.v("Uploading File", "$bytesSent/$totalBytes")
                    }
                }
            }
            when (response.status.value) {
                in 200..299 -> {
                    Log.v("Uploading File", "Server responded with success: ${response.status}")
                }

                else -> {
                    val errorBody = response.body<String>()
                    Log.e(
                        "Uploading File",
                        "Server responded with error: ${response.status}, Body: $errorBody"
                    )
                    close(Exception("Upload failed on server: ${response.status} - $errorBody"))
                }
            }
        } catch (e: Exception) {
            Log.e("Uploading File Exception", ": ${e.message}")
            close(Exception("Upload failed on server: ${e.message}"))
        }
    }
}
