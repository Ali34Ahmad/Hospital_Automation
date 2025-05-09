package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildDto
import com.example.network.model.dto.child.ChildGuardianRelationDto
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName


@OptIn(InternalSerializationApi::class)
data class AddChildResponse(
    val child: ChildDto,
    @SerialName("child_Guardiant_relation") val childGuardianRelation : ChildGuardianRelationDto
)
