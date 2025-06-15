package com.example.network.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

object LocalTimeSerializer : KSerializer<LocalTime> {
    override fun serialize(
        encoder: Encoder,
        value: LocalTime,
    ) {
        val timeString = value.format(timeFormatter)
        encoder.encodeString(timeString)
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("LocalTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalTime {
        val timeString = decoder.decodeString()
        return LocalTime.parse(timeString, timeFormatter)
    }
}