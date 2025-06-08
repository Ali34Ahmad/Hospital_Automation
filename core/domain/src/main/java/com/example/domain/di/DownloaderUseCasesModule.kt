package com.example.domain.di

import com.example.domain.use_cases.download_file.CancelFileDownloadUseCase
import com.example.domain.use_cases.download_file.DownloadFileUseCase
import com.example.domain.use_cases.download_file.ObserveFileDownloadProgressUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val downloaderUseCaseModule= module {
    singleOf(::DownloadFileUseCase)

    singleOf(::ObserveFileDownloadProgressUseCase)

    singleOf(::CancelFileDownloadUseCase)
}