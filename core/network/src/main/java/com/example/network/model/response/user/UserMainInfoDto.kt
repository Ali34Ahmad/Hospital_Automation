package com.example.network.model.response.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserMainInfoDto(
    @SerialName("userId")
    val id:Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("middle_name")
    val middleName: String?,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("imgurl")
    val imageUrl: String?=null,
    @SerialName("specialty")
    val subInfo: String?=null,
)