package com.example.network.model.response

data class ProgressUpdateDto(
    val bytesSent: Long,
    val totalBytes: Long,
)