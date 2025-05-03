package com.example.network.model.response

data class ProgressUpdate(
    val bytesSent: Long,
    val totalBytes: Long,
)