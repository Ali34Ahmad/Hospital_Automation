package com.example.network.downloader

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.dto.downloader.DownloadProgressDto
import com.example.utility.url.getFileNameFromUrl
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import java.io.File
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import kotlin.collections.remove
import kotlin.compareTo
import kotlin.text.ifEmpty

class FileDownloaderServiceImpl(
    private val context: Context,
    private val downloadManager: DownloadManager,
) : FileDownloaderService {
    private val TAG = "AndroidDownloader"
    override fun downloadFile(url: String): Long {
        val extractedTitle = getFileNameFromUrl(url)
        if (extractedTitle.isNullOrBlank()) {
            Log.e(TAG, "Could not extract a valid filename from URL: $url. Using default.")
        }

        val title = if (extractedTitle.isNullOrBlank()) "DownloadedFile.pdf" else extractedTitle

        val requestUri = try {
            url.toUri()
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "Invalid URL format: $url", e)
            return -1L
        }

        val request = DownloadManager.Request(requestUri)
            .setMimeType("application/pdf")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(title)
            .setDestinationInExternalFilesDir(
                context,
                Environment.DIRECTORY_DOWNLOADS,
                title
            )
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        Log.d(
            TAG,
            "Attempting to download from URL: $url to ${context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}/$title"
        )

        return downloadManager.enqueue(request)
    }

    override fun observeDownloadProgress(downloadId: Long): Flow<DownloadProgressDto> =
        callbackFlow {
            if (downloadId == -1L) {
                send(
                    DownloadProgressDto(
                        downloadId,
                        DownloadManager.STATUS_FAILED,
                        0,
                        0,
                        0,
                        DownloadManager.ERROR_UNKNOWN // Or a custom error
                    )
                )
                close()
                return@callbackFlow
            }

            val query = DownloadManager.Query().setFilterById(downloadId)
            val pollingIntervalMillis = 50L // Poll every 1 second (adjust as needed)

            while (isActive) { // Loop while the coroutine is active
                var cursor: Cursor? = null
                try {
                    cursor = downloadManager.query(query)
                    if (cursor != null && cursor.moveToFirst()) {
                        val statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                        val reasonIndex = cursor.getColumnIndex(DownloadManager.COLUMN_REASON)
                        val bytesDownloadedIndex =
                            cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                        val totalBytesIndex =
                            cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)

                        // Ensure columns exist
                        if (statusIndex == -1 || bytesDownloadedIndex == -1 || totalBytesIndex == -1) {
                            Log.e(
                                TAG,
                                "Required columns not found in DownloadManager cursor for ID: $downloadId"
                            )
                            send(
                                DownloadProgressDto(
                                    downloadId,
                                    DownloadManager.STATUS_FAILED,
                                    0,
                                    0,
                                    0,
                                    DownloadManager.ERROR_UNKNOWN
                                )
                            )
                            close() // Close the flow on critical error
                            return@callbackFlow
                        }


                        val status = cursor.getInt(statusIndex)
                        val bytesDownloaded = cursor.getLong(bytesDownloadedIndex)
                        val totalBytes = cursor.getLong(totalBytesIndex)
                        val reason = if (reasonIndex != -1) cursor.getInt(reasonIndex) else 0

                        val progressPercent = if (totalBytes > 0) {
                            ((bytesDownloaded * 100L) / totalBytes).toInt()
                        } else {
                            0 // Avoid division by zero, or handle as indeterminate
                        }

                        val progress = DownloadProgressDto(
                            downloadId = downloadId,
                            status = status,
                            bytesDownloaded = bytesDownloaded,
                            totalBytes = totalBytes,
                            progressPercent = progressPercent,
                            reason = if (status == DownloadManager.STATUS_FAILED || status == DownloadManager.STATUS_PAUSED) reason else null
                        )
//                        trySend(progress).isSuccess // Offer the latest progress
                        send(progress)
                        // If download is completed (successful or failed), stop polling
                        if (status == DownloadManager.STATUS_SUCCESSFUL || status == DownloadManager.STATUS_FAILED) {
                            close() // Close the flow
                        }
                    } else {
                        // Download ID not found, could mean it was cancelled or completed and removed
                        Log.w(
                            TAG,
                            "Download ID $downloadId not found in DownloadManager for progress."
                        )
                        send(
                            DownloadProgressDto(
                                downloadId,
                                DownloadManager.STATUS_FAILED, // Assume failure or completion
                                0,
                                0,
                                0,
                                DownloadManager.ERROR_UNKNOWN // Or a specific "not found" status
                            )
                        )
                        close()
                    }
                } catch (e: Exception) {
                    Log.e(
                        TAG,
                        "Error querying download progress for ID $downloadId: ${e.message}",
                        e
                    )
                    send(
                        DownloadProgressDto(
                            downloadId,
                            DownloadManager.STATUS_FAILED,
                            0,
                            0,
                            0,
                            DownloadManager.ERROR_UNKNOWN
                        )
                    )
                    close(e) // Close flow with error
                } finally {
                    cursor?.close()
                }

                // IMPORTANT: Introduce a delay to prevent busy-waiting and freezing
                if (isActive) { // Only delay if still active, avoid delaying after close()
                    delay(pollingIntervalMillis)
                }
            }
            awaitClose {
                Log.d(TAG, "Download progress observation for ID $downloadId ended.")
            }
        }

    override fun cancelDownload(downloadId: Long): Int {
        if (downloadId == -1L) {
            Log.w(TAG, "Cannot cancel download with invalid ID: -1")
            return 0 // Indicate no downloads were removed
        }
        Log.d(TAG, "Attempting to cancel download with ID: $downloadId")
        return try {
            val itemsRemoved = downloadManager.remove(downloadId)
            if (itemsRemoved > 0) {
                Log.i(TAG, "Successfully requested cancellation and removal for download ID: $downloadId. Items removed: $itemsRemoved")
            } else {
                Log.w(TAG, "No download found with ID: $downloadId to cancel, or it was already completed/removed. Items removed: $itemsRemoved")
            }
            itemsRemoved
        } catch (e: Exception) {
            Log.e(TAG, "Exception while trying to cancel download ID $downloadId: ${e.message}", e)
            0
        }
    }
}