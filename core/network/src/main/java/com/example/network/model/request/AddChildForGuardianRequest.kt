package com.example.network.model.request

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AddChildForGuardianRequest(
    @SerialName("child-id")
    val childId: Int,
    @SerialName("guardian-id")
    val guardianId: Int
)
