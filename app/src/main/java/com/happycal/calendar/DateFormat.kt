package com.happycal.calendar

import java.text.SimpleDateFormat
import java.util.*

class DateFormat {

    val CALENDAR_HEADER_FORMAT = "yyyy년 MM월"
    val DAY_FORMAT = "d"

    constructor()

    fun getDate(date: Long, pattern: String?): String? {
        return try {
            val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
            val d = Date(date)
            formatter.format(d).toUpperCase()
        } catch (e: Exception) {
            " "
        }
    }

}