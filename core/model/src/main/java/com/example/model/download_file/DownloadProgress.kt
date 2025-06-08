package com.example.model.download_file

import com.example.model.enums.FileDownloadingState

data class DownloadProgress(
    val downloadId: Long,
    val fileDownloadingState: FileDownloadingState, // e.g., DownloadManager.STATUS_RUNNING, STATUS_PAUSED, etc.
    val bytesDownloaded: Long,
    val totalBytes: Long,
    val progressPercent: Int, // Calculated as (bytesDownloaded * 100 / totalBytes)
    val reason: Int? = null // For paused or failed states
)