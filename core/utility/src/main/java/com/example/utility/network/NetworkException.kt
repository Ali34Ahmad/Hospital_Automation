package com.example.utility.network


class NetworkException(val error: NetworkError) : Exception(error.name)
