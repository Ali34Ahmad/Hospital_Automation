package com.example.network.model.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val profile: ProfileData
)