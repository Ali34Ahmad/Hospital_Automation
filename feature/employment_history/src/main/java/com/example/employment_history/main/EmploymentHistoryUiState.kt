package com.example.employment_history.main

import com.example.model.FileInfo
import com.example.model.download_file.DownloadProgress
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.enums.FileDownloadingState
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class EmploymentHistoryUiState(
    val employmentHistory: EmploymentHistoryResponse?=null,
    val screenState: ScreenState= ScreenState.IDLE,

    val fileInfo: FileInfo?=null,
    val showFileDownloaderDialog: Boolean=false,
    val fileDownloadingState: FileDownloadingState= FileDownloadingState.IDLE,
    val downloadProgress: DownloadProgress?=null,

    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,

)
