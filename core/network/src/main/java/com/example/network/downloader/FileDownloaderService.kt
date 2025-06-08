package com.example.network.downloader

import com.example.network.model.dto.downloader.DownloadProgressDto
import kotlinx.coroutines.flow.Flow

interface FileDownloaderService {
    fun downloadFile(url: String): Long

    fun observeDownloadProgress(downloadId: Long): Flow<DownloadProgressDto>

    fun cancelDownload(downloadId: Long): Int
}