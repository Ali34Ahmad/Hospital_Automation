package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildFullDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChildFullResponse(
    val child: ChildFullDto,
    @SerialName("numOfGuardian")
    val numOfGuardians: Int
)
