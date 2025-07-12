package com.example.domain.use_cases.download_file

import com.example.domain.repositories.file.DownloadFileRepository
import com.example.model.download_file.DownloadProgress
import kotlinx.coroutines.flow.Flow

class ObserveFileDownloadProgressUseCase(
    private val downloadFileRepository: DownloadFileRepository,
) {
    operator fun invoke(
        downloadId: Long
    ): Flow<DownloadProgress> {
        return downloadFileRepository.observeDownloadProgress(downloadId)
    }
}