package com.example.domain.repositories

import com.example.model.download_file.DownloadProgress
import kotlinx.coroutines.flow.Flow

interface DownloadFileRepository {
    fun downloadFile(url: String): Long

    fun observeDownloadProgress(downloadId: Long): Flow<DownloadProgress>

    fun cancelDownload(downloadId: Long): Int
}