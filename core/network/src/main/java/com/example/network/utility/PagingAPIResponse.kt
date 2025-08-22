package com.example.network.utility

import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.Serializable

/**
 * This is the wrapper class for paging responses
 *
 * @param data the page items
 * @param pagination the paging data like [NetworkPagination.page],
 * [NetworkPagination.limit] and [NetworkPagination.totalPages]
 *
 * @see NetworkPagination
 *
 * @author Ali Mansoura
 */

@Serializable
data class PagingAPIResponse <T>(
    val data : List<T>,
    val pagination: NetworkPagination
)