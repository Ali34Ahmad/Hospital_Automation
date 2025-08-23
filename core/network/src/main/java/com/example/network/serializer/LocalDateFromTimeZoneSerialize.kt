package com.example.network.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object LocalDateFromTimeZoneSerialize: KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "LocalDateFromTimeZone",
            PrimitiveKind.STRING
        )

    override fun serialize(
        encoder: Encoder,
        value: LocalDate,
    ) {
        val dateTime = value.atStartOfDay().atOffset(java.time.ZoneOffset.UTC)
        encoder.encodeString(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        val value = decoder.decodeString()
        return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDate()
    }
}