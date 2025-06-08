package com.example.network.downloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.util.Log
import android.widget.Toast
import org.koin.android.ext.koin.androidContext

class DownloadCompletedReceiver: BroadcastReceiver() {
    private val TAG="AndroidDownloaderBroadcast"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)

            if (downloadId == -1L) {
                Log.e(TAG, "ACTION_DOWNLOAD_COMPLETE received but no Download ID found.")
                return
            }

            Log.d(TAG, "Received ACTION_DOWNLOAD_COMPLETE for ID: $downloadId")

            val downloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val query = DownloadManager.Query().setFilterById(downloadId)
            val cursor: Cursor? = try {
                downloadManager.query(query)
            } catch (e: Exception) {
                Log.e(TAG, "Error querying DownloadManager: ${e.message}", e)
                null
            }

            if (cursor == null) {
                Log.e(TAG, "DownloadManager query returned null cursor for ID: $downloadId.")
                return
            }

            if (cursor.moveToFirst()) {
                val statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                val reasonIndex = cursor.getColumnIndex(DownloadManager.COLUMN_REASON)
                val titleIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)
                val localUriIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)

                // Check if columns exist (robustness)
                if (statusIndex == -1) {
                    Log.e(TAG, "COLUMN_STATUS not found in cursor for ID: $downloadId")
                    cursor.close()
                    return
                }

                val status = cursor.getInt(statusIndex)
                val title = if (titleIndex != -1) cursor.getString(titleIndex) else "N/A"

                when (status) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        val localUri =
                            if (localUriIndex != -1) cursor.getString(localUriIndex) else null
                        Log.i(TAG, "Download '$title' (ID: $downloadId) successful. URI: $localUri")
                        Toast.makeText(context, "Download '$title' successful!", Toast.LENGTH_LONG)
                            .show()
                        // Handle the successful download (e.g., open file, notify user)
                    }

                    DownloadManager.STATUS_FAILED -> {
                        val reason = if (reasonIndex != -1) cursor.getInt(reasonIndex) else 0
                        Log.e(
                            TAG,
                            "Download '$title' (ID: $downloadId) failed. Status: $status, Reason: $reason"
                        )
                        logDownloadManagerReason(TAG, reason) // Use the helper function
                        Toast.makeText(
                            context,
                            "Download '$title' unsuccessful. Reason: ${getReasonText(reason)}",
                            Toast.LENGTH_LONG
                        ).show()
                        // Handle the failed download (e.g., notify user, offer retry)
                    }

                    DownloadManager.STATUS_PAUSED -> {
                        val reason = if (reasonIndex != -1) cursor.getInt(reasonIndex) else 0
                        Log.w(TAG, "Download '$title' (ID: $downloadId) paused. Reason: $reason")
                        logDownloadManagerReason(TAG, reason)
                        Toast.makeText(context, "Download '$title' paused.", Toast.LENGTH_SHORT)
                            .show()
                    }

                    else -> {
                        Log.w(
                            TAG,
                            "Download '$title' (ID: $downloadId) completed with unhandled status: $status"
                        )
                    }
                }
            } else {
                // This case should ideally not happen if we received an ID from the intent.
                // It might indicate the download was removed from the DownloadManager's database
                // very quickly, or an issue with the query.
                Log.w(
                    TAG,
                    "Download ID $downloadId not found in DownloadManager database after receiving broadcast."
                )
                Toast.makeText(
                    context,
                    "Download info for ID $downloadId not found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            cursor.close()
        }
    }


    // Helper function to log specific reasons (can be in the same file or a utility file)
    private fun logDownloadManagerReason(tag: String, reason: Int) {
        val reasonText = getReasonText(reason)
        Log.e(tag, "Download Failure Reason Code: $reason ($reasonText)")
    }

    private fun getReasonText(reason: Int): String {
        return when (reason) {
            DownloadManager.ERROR_CANNOT_RESUME -> "ERROR_CANNOT_RESUME"
            DownloadManager.ERROR_DEVICE_NOT_FOUND -> "ERROR_DEVICE_NOT_FOUND (e.g. SD card removed)"
            DownloadManager.ERROR_FILE_ALREADY_EXISTS -> "ERROR_FILE_ALREADY_EXISTS"
            DownloadManager.ERROR_FILE_ERROR -> "ERROR_FILE_ERROR (Disk full or other file system issue)"
            DownloadManager.ERROR_HTTP_DATA_ERROR -> "ERROR_HTTP_DATA_ERROR (Server error or network issue)"
            DownloadManager.ERROR_INSUFFICIENT_SPACE -> "ERROR_INSUFFICIENT_SPACE"
            DownloadManager.ERROR_TOO_MANY_REDIRECTS -> "ERROR_TOO_MANY_REDIRECTS"
            DownloadManager.ERROR_UNHANDLED_HTTP_CODE -> "ERROR_UNHANDLED_HTTP_CODE (Server returned an error like 4xx or 5xx)"
            DownloadManager.ERROR_UNKNOWN -> "ERROR_UNKNOWN"
            0 -> "REASON_UNKNOWN (No specific error code provided by DownloadManager)" // Common if status is not FAILED
            else -> "Unknown or undocumented reason code: $reason"
        }
    }
}