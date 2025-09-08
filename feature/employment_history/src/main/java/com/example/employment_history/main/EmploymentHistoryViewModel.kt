package com.example.employment_history.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.download_file.CancelFileDownloadUseCase
import com.example.domain.use_cases.download_file.DownloadFileUseCase
import com.example.domain.use_cases.download_file.ObserveFileDownloadProgressUseCase
import com.example.domain.use_cases.employment_history.GetEmploymentHistoryUseCase
import com.example.domain.use_cases.employment_history.GetPharmacyContractUseCase
import com.example.employment_history.navigation.EmploymentHistoryRoute
import com.example.model.FileInfo
import com.example.model.download_file.DownloadProgress
import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.employment_history.PharmacyContractResponse
import com.example.model.enums.FileDownloadingState
import com.example.model.enums.Role
import com.example.model.enums.ScreenState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.utility.url.getFileNameFromUrl

class EmploymentHistoryViewModel(
    private val getEmploymentHistoryUseCase: GetEmploymentHistoryUseCase,
    private val getPharmacyContractUseCase: GetPharmacyContractUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val observeFileDownloadProgressUseCase: ObserveFileDownloadProgressUseCase,
    private val cancelFileDownloadUseCase: CancelFileDownloadUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        EmploymentHistoryUiState(
            userId = savedStateHandle.toRoute<EmploymentHistoryRoute>().userId,
            pharmacyId = savedStateHandle.toRoute<EmploymentHistoryRoute>().pharmacyId,
            roleOfRequestedUserHistory = savedStateHandle.toRoute<EmploymentHistoryRoute>().roleOfRequestedUserHistory
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        when (uiState.value.roleOfRequestedUserHistory) {
            Role.EMPLOYEE, Role.DOCTOR -> getEmploymentHistory()
            Role.PHARMACIST -> getPharmacyContract()
            else -> null
        }
    }

    fun getUiActions(
        navActions: EmploymentHistoryNavigationUiActions,
    ): EmploymentHistoryUiActions = EmploymentHistoryUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): EmploymentHistoryBusinessUiActions =
        object : EmploymentHistoryBusinessUiActions {
            override fun onReloadEmploymentHistory() {
                getEmploymentHistory()
            }

            override fun onHideFileDownloaderDialog() {
                hideFileDownloaderDialog()
            }

            override fun onDownloadFile() {
                downloadFile()
            }

            override fun onCancelFileDownloading() {
                cancelFileDownload()
            }

            override fun onShowFileDownloaderDialog() {
                showFileDownloaderDialog()
            }

            override fun onRefresh() {
                when (uiState.value.roleOfRequestedUserHistory) {
                    Role.EMPLOYEE, Role.DOCTOR -> refreshEmploymentHistory()
                    Role.PHARMACIST -> refreshPharmacyContract()
                    else -> null
                }
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

        }

    private fun updateEmploymentHistory(employmentHistory: EmploymentHistoryResponse?) {
        _uiState.update {
            it.copy(
                employmentHistory = employmentHistory,
            )
        }
        val url = uiState.value.employmentHistory?.currentUser?.documentsFileUrl
            ?: uiState.value.pharmacyContractResponse?.pharmacyInHistory?.pharmacist?.pharmacyFileUrl
        val fileInfo = uiState.value.employmentHistory?.let {
            FileInfo(
                fileName = getFileNameFromUrl(url) ?: "",
                fileSizeWithBytes = uiState.value.employmentHistory?.employeeDocumentsFileSize
                    ?: uiState.value.pharmacyContractResponse?.fileSize ?: 0L,
                uploadingProgress = 0,
            )
        }
        updateFileInfo(
            fileInfo = fileInfo
        )
    }

    private fun updateFileInfo(fileInfo: FileInfo?) {
        _uiState.update {
            it.copy(
                fileInfo = fileInfo
            )
        }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update {
            it.copy(screenState = screenState)
        }
    }

    private fun getEmploymentHistory() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Getting EmploymentHistory", "EmploymentHistoryViewModel")
            getEmploymentHistoryUseCase(uiState.value.userId)
                .onSuccess { data ->
                    Log.v("EmploymentHistory fetched Successfully", "EmploymentHistoryViewModel")
                    updateEmploymentHistory(data)
                    updateScreenState(ScreenState.SUCCESS)
                }.onError { error ->
                    Log.v("Failed to fetch EmploymentHistory", "EmploymentHistoryViewModel")
                    updateEmploymentHistory(null)
                    updateScreenState(ScreenState.ERROR)
                }
        }
    }

    private fun hideFileDownloaderDialog() {
        updateFileDownloaderDialogVisibility(false)
    }

    private fun showFileDownloaderDialog() {
        updateFileDownloaderDialogVisibility(true)
    }

    private fun updateFileDownloaderDialogVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(showFileDownloaderDialog = isVisible) }

    }

    private fun downloadFile() {
        viewModelScope.launch {
            val fileUrl = uiState.value.employmentHistory?.currentUser?.documentsFileUrl
                ?: uiState.value.pharmacyContractResponse?.pharmacyInHistory?.pharmacist?.pharmacyFileUrl
            if (fileUrl == null) return@launch
            val downloadId = downloadFileUseCase(url = fileUrl)
            if (downloadId == -1L) return@launch

            observeFileDownloadProgressUseCase(downloadId)
                .collect { downloadProgress ->
                    updateDownloadProgress(
                        downloadProgress
                    )
                }
        }
    }

    private fun updateDownloadProgress(downloadProgress: DownloadProgress) {
        _uiState.update {
            it.copy(
                downloadProgress = downloadProgress,
                fileInfo = uiState.value.fileInfo?.copy(
                    uploadingProgress = downloadProgress.progressPercent,
                ),
                fileDownloadingState = downloadProgress.fileDownloadingState,
            )
        }
    }

    private fun cancelFileDownload() {
        val downloadId = uiState.value.downloadProgress?.downloadId
        if (downloadId == -1L || downloadId == null) return
        val removedDownload = cancelFileDownloadUseCase(downloadId)
        if (removedDownload > 0) {
            updateFileDownloadingState(FileDownloadingState.CANCELLED)
        }
    }

    private fun updateFileDownloadingState(fileDownloadingState: FileDownloadingState) {
        _uiState.update {
            it.copy(fileDownloadingState = fileDownloadingState)
        }
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun refreshEmploymentHistory() {
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Getting EmploymentHistory", "EmploymentHistoryViewModel")
            getEmploymentHistoryUseCase(uiState.value.userId)
                .onSuccess { data ->
                    Log.v("EmploymentHistory fetched Successfully", "EmploymentHistoryViewModel")
                    updateIsRefreshing(false)
                    updateEmploymentHistory(data)
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch EmploymentHistory", "EmploymentHistoryViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }


    private fun updatePharmacyContract(pharmacyContractResponse: PharmacyContractResponse?) {
        _uiState.update {
            it.copy(
                pharmacyContractResponse = pharmacyContractResponse,
            )
        }
        val url =
            uiState.value.pharmacyContractResponse?.pharmacyInHistory?.pharmacist?.pharmacyFileUrl
        val fileInfo = uiState.value.pharmacyContractResponse?.let {
            FileInfo(
                fileName = getFileNameFromUrl(url) ?: "",
                fileSizeWithBytes = uiState.value.pharmacyContractResponse?.fileSize
                    ?: 0L,
                uploadingProgress = 0,
            )
        }
        updateFileInfo(
            fileInfo = fileInfo
        )
    }

    private fun getPharmacyContract() {
        val pharmacyId = uiState.value.pharmacyId
        if (pharmacyId == null) return
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Getting EmploymentHistory", "EmploymentHistoryViewModel")
            getPharmacyContractUseCase(pharmacyId)
                .onSuccess { data ->
                    Log.v("EmploymentHistory fetched Successfully", "EmploymentHistoryViewModel")
                    updatePharmacyContract(data)
                    updateScreenState(ScreenState.SUCCESS)
                }.onError { error ->
                    Log.v("Failed to fetch EmploymentHistory", "EmploymentHistoryViewModel")
                    updatePharmacyContract(null)
                    updateScreenState(ScreenState.ERROR)
                }
        }
    }

    private fun refreshPharmacyContract() {
        val pharmacyId = uiState.value.pharmacyId
        if (pharmacyId == null) return
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Getting EmploymentHistory", "EmploymentHistoryViewModel")
            getPharmacyContractUseCase(pharmacyId)
                .onSuccess { data ->
                    Log.v("EmploymentHistory fetched Successfully", "EmploymentHistoryViewModel")
                    updateIsRefreshing(false)
                    updatePharmacyContract(data)
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch EmploymentHistory", "EmploymentHistoryViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

}