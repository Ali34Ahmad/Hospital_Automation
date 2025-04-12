package com.example.network.utility

sealed class Resource<T>(val data: T? = null, message : String? = null) {
    class Success<T>(data: T) : Resource<T>()
    class Error<T>(message: String, data: T? = null) : Resource<T>()
    class Loading<T>(data: T? = null) : Resource<T>()
}