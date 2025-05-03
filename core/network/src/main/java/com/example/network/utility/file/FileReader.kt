package com.example.network.utility.file

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID

class FileReader(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend fun uriToFileInfo(uri: Uri): FileInfo {
        return withContext(dispatcher) {
            val bytes = context.contentResolver
                .openInputStream(uri)
                ?.use { inputStream ->
                    inputStream.readBytes()
                } ?: byteArrayOf()
            val fileName = UUID.randomUUID().toString()
            val mimeType = context.contentResolver.getType(uri) ?: ""
            FileInfo(fileName, mimeType, bytes)
        }
    }
}

class FileInfo(
    val name: String,
    val mimeType: String,
    val bytes: ByteArray
)