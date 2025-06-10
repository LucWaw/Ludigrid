package com.lucwaw.ludigrid.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

class DateUtil {
    companion object {
        fun LocalDateTime.Companion.now(): LocalDateTime {
            return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }

        fun LocalDate.formatDate(): String {
            val dateFormat = LocalDate.Format {
                dayOfMonth()
                char('/')
                monthNumber()
                char('/')
                year()
            }
            return dateFormat.format(this)
        }
    }

}