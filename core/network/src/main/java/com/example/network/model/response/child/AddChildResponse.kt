package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildDto
import com.example.network.model.dto.child.ChildGuardianRelationDto
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AddChildResponse(
    val child: ChildDto,
    @SerialName("child_Guardiant_relation") val childGuardianRelation : ChildGuardianRelationDto
)
