package com.example.child_profile.presentation

import com.example.child_profile.navigation.ChildProfileMode
import com.example.model.FileInfo
import com.example.model.child.ChildFullData
import com.example.model.download_file.DownloadProgress
import com.example.model.enums.FileDownloadingState
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class ChildProfileUIState(
    val childId: Int,
    val childProfileMode: ChildProfileMode,
    val child: ChildFullData? = null,
    val state: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,

    val showFileDownloaderDialog: Boolean = false,
    val fileDownloadingState: FileDownloadingState = FileDownloadingState.IDLE,
    val fileInfo: FileInfo? = null,
    val downloadProgress: DownloadProgress? = null,
)
