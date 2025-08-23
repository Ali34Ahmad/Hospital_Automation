

package com.example.network.model.response.user

import com.example.network.model.dto.user.UserFullDto
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames


@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class GetGuardianByIdResponse(
    @JsonNames("user","data")
    val user: UserFullDto
)
