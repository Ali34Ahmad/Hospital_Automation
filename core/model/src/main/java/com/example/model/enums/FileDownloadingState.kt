package com.example.model.enums

enum class FileDownloadingState {
    IDLE,
    PAUSED,
    PENDING,
    DOWNLOADING,
    COMPLETE,
    FAILED,
    CANCELLED,
}