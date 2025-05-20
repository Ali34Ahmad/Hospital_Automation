package com.example.model.guardian

data class PagedData<T>(
    val page: Int,
    val data: List<T>
)