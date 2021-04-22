package com.oleksandrlysun.securemessenger.api.typeadapters

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.oleksandrlysun.securemessenger.api.Settings
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.lang.Exception

class DateTimeTypeAdapter : TypeAdapter<DateTime>() {

    override fun write(out: JsonWriter, value: DateTime) {
        val dateStr = DateTimeFormat.forPattern(Settings.SERVER_DATE_FORMAT).print(value)
        out.value(dateStr)
    }

    override fun read(input: JsonReader): DateTime? {
        val dateStr = input.nextString()
        if (dateStr.isNullOrEmpty()) {
            return null
        }

        return try {
            DateTime.parse(dateStr)
        } catch (e: Exception) {
            LocalDateTime.parse(dateStr).toLocalDate().toDateTimeAtStartOfDay()
        }
    }
}