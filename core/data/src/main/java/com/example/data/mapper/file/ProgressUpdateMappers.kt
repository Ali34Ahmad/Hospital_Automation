package com.example.data.mapper.file

import com.example.model.file.ProgressUpdate
import com.example.network.model.response.ProgressUpdateDto

fun ProgressUpdateDto.toProgressUpdate() =
    ProgressUpdate(
        bytesSent = this.bytesSent,
        totalBytes = this.totalBytes
    )