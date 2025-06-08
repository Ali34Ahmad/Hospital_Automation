package com.example.domain.use_cases.download_file

import com.example.domain.repositories.DownloadFileRepository

class CancelFileDownloadUseCase(
    private val downloadFileRepository: DownloadFileRepository,
) {
    operator fun invoke(
        downloadId: Long
    ): Int {
        return downloadFileRepository.cancelDownload(downloadId)
    }
}