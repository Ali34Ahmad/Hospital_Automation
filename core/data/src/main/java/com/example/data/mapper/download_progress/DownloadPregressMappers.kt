package com.example.data.mapper.download_progress

import android.app.DownloadManager
import com.example.model.download_file.DownloadProgress
import com.example.model.enums.FileDownloadingState
import com.example.network.model.dto.downloader.DownloadProgressDto
//
//fun DownloadProgressDto.toDownloadProgress()=
//    DownloadProgress(
//        downloadId = this.downloadId,
//        fileDownloadingState = this.status,
//        bytesDownloaded = this.bytesDownloaded,
//        totalBytes = this.totalBytes,
//        progressPercent = this.progressPercent,
//        reason = this.reason
//    )


// Mapper function
fun DownloadProgressDto.toDownloadProgress(): DownloadProgress {
    val fileState: FileDownloadingState = when (this.status) {
        DownloadManager.STATUS_PENDING -> FileDownloadingState.PENDING
        DownloadManager.STATUS_RUNNING -> FileDownloadingState.DOWNLOADING
        DownloadManager.STATUS_PAUSED -> FileDownloadingState.PAUSED
        DownloadManager.STATUS_SUCCESSFUL -> FileDownloadingState.COMPLETE
        DownloadManager.STATUS_FAILED -> FileDownloadingState.FAILED
        else -> FileDownloadingState.FAILED
    }

    return DownloadProgress(
        downloadId = this.downloadId,
        fileDownloadingState = fileState,
        bytesDownloaded = this.bytesDownloaded,
        totalBytes = this.totalBytes,
        progressPercent = this.progressPercent,
        reason = if (fileState == FileDownloadingState.FAILED || fileState == FileDownloadingState.PAUSED) this.reason else null,
    )
}
