package com.example.network.utility.file

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}

class FileInfo(
    val name: String,
    val mimeType: String,
    val bytes: ByteArray,
)
