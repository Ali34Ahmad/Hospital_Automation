package com.example.utility.url

import android.util.Log
import java.io.File
import androidx.core.net.toUri
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun getFileNameFromUrl(url: String?): String? {
    if (url.isNullOrBlank()) {
        return null
    }
    return try {
        val file = File(url.toUri().path ?: "")
        var fileName = file.name
        if (fileName.isNotEmpty()) {
            fileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.name())
        }
        Log.v("ExtractingFileName",fileName)
        fileName.ifEmpty { null }
    } catch (e: Exception) {
        Log.w("ExtractingFileName", "Error extracting filename from URL '$url': ${e.message}", e)
        null
    }
}