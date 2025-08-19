package com.example.network.serializer

import io.ktor.websocket.Serializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

private val isoOffsetDateTimeParser = DateTimeFormatter.ISO_OFFSET_DATE_TIME

// Formatter for serializing LocalDateTime back to a string WITHOUT offset
private val isoLocalDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        // When serializing LocalDateTime, it has no offset, so ISO_LOCAL_DATE_TIME is appropriate
        encoder.encodeString(value.format(isoLocalDateTimeFormatter))
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val dateString = decoder.decodeString()
        // Parse the string using a formatter that understands offsets/zone info (like 'Z')
        // Then convert to LocalDateTime. This effectively takes the date/time numbers
        // as they were in UTC and represents them as a LocalDateTime.
        return OffsetDateTime.parse(dateString,
            isoOffsetDateTimeParser
        ).toLocalDateTime()
    }
}