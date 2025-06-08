package com.example.network.model.dto.downloader

data class DownloadProgressDto(
    val downloadId: Long,
    val status: Int, // e.g., DownloadManager.STATUS_RUNNING, STATUS_PAUSED, etc.
    val bytesDownloaded: Long,
    val totalBytes: Long,
    val progressPercent: Int, // Calculated as (bytesDownloaded * 100 / totalBytes)
    val reason: Int? = null // For paused or failed states
)