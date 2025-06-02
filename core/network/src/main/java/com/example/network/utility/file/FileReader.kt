package com.example.network.utility.file

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.UUID

class FileReader(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun uriToFileInfo(contentUri: Uri): FileInfo {
        return withContext(ioDispatcher) {
            val bytes = context
                .contentResolver
                .openInputStream(contentUri)?.use { inputStream ->
                    inputStream.readBytes()
                } ?: byteArrayOf()

            val name = contentUri.lastPathSegment ?: ""
            val mimeType = context.contentResolver.getType(contentUri) ?: ""
            FileInfo(name, mimeType, bytes)
        }
    }

    suspend fun uriToFile(contentUri: Uri): File? {
        return withContext(ioDispatcher) {
            var tempFile: File? = null
            var inputStream: InputStream? = null
            try {
                inputStream = context.contentResolver.openInputStream(contentUri)
                if (inputStream == null) {
                    // Log error or handle cases where input stream cannot be opened
                    return@withContext null
                }

                // Get a suitable file name for the temporary file
                val fileName = getFileName(contentUri)
                tempFile = File(context.cacheDir, fileName) // Use app's cache directory

                FileOutputStream(tempFile).use { outputStream ->
                    inputStream.copyTo(outputStream) // Efficiently copy all bytes
                }

                // Verify the file was created and has content
                if (tempFile.exists() && tempFile.length() > 0) {
                    tempFile // Return the created temporary file
                } else {
                    tempFile.delete() // Delete if empty or creation failed
                    null
                }
            } catch (e: IOException) {
                // Handle I/O errors (e.g., cannot read from URI, cannot write to file)
                e.printStackTrace() // Log the exception for debugging
                tempFile?.delete() // Clean up partially created file
                null
            } catch (e: Exception) {
                // Catch other unexpected exceptions
                e.printStackTrace() // Log the exception
                tempFile?.delete() // Clean up
                null
            } finally {
                try {
                    inputStream?.close() // Ensure input stream is closed
                } catch (e: IOException) {
                    e.printStackTrace() // Log close error
                }
            }
        }
    }

    /**
     * Tries to get a display name from the URI. If not available, generates a generic name.
     * Adds a timestamp to ensure uniqueness.
     */
    private fun getFileName(uri: Uri): String {
        var name = "temp_file_${System.currentTimeMillis()}" // Default name with timestamp
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (displayNameIndex != -1) {
                    val displayName = cursor.getString(displayNameIndex)
                    if (!displayName.isNullOrBlank()) {
                        name = displayName
                    }
                }
            }
        }
        // Append a timestamp to ensure uniqueness, even if display name is not unique
        // You might also want to append the correct extension if possible
        return "${System.currentTimeMillis()}_$name"
    }
}

class FileInfo(
    val name: String,
    val mimeType: String,
    val bytes: ByteArray,
)
