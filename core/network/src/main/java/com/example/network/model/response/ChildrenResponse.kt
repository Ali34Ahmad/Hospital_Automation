package com.example.network.model.response

data class ChildrenResponse(
    val data: List<NetworkChild>,
    val pagination: NetworkPagination
)
