package com.example.data.repositories.download_file

import com.example.data.mapper.download_progress.toDownloadProgress
import com.example.domain.repositories.file.DownloadFileRepository
import com.example.model.download_file.DownloadProgress
import com.example.network.downloader.FileDownloaderService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DownloadFileRepositoryImpl(
    private val fileDownloaderService: FileDownloaderService,
):DownloadFileRepository {
    override fun downloadFile(url: String): Long =
        fileDownloaderService.downloadFile(url)

    override fun observeDownloadProgress(downloadId: Long): Flow<DownloadProgress> =
        fileDownloaderService.observeDownloadProgress(downloadId)
            .map { downloadProgressDto->
                downloadProgressDto.toDownloadProgress()
            }

    override fun cancelDownload(downloadId: Long): Int=
        fileDownloaderService.cancelDownload(downloadId)

}