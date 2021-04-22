package com.oleksandrlysun.securemessenger.presentation.utils

import com.oleksandrlysun.securemessenger.api.Settings
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateFormatter {

    @JvmStatic
    fun timeAsString(time: DateTime): String {
        return DateTimeFormat.forPattern(Settings.APP_TIME_FORMAT).print(time)
    }

    @JvmStatic
    fun dateAsString(dateTime: DateTime, format: String = Settings.APP_DATE_FORMAT): String {
        return DateTimeFormat.forPattern(format).print(dateTime)
    }
}