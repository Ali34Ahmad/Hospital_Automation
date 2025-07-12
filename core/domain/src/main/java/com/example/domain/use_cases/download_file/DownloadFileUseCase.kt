package com.example.domain.use_cases.download_file

import com.example.domain.repositories.file.DownloadFileRepository

class DownloadFileUseCase(
    private val downloadFileRepository: DownloadFileRepository,
) {
    operator fun invoke(
        url: String
    ): Long {
        return downloadFileRepository.downloadFile(url)
    }
}