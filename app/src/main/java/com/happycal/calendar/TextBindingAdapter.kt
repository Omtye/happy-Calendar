package com.happycal.calendar

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*


class TextBindingAdapter {

}

@BindingAdapter("setCalendarHeaderText")
fun setCalendarHeaderText(view: TextView, date: Long?) {
    try {
        if (date != null) {
            view.setText(
                DateFormat().getDate(
                    date,
                    DateFormat().CALENDAR_HEADER_FORMAT
                )
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("setDayText")
fun setDayText(view: TextView, calendar: Calendar?) {
    try {
        if (calendar != null) {
            val gregorianCalendar = GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                0,
                0,
                0
            )
            view.setText(
                DateFormat().getDate(
                    gregorianCalendar.getTimeInMillis(),
                    DateFormat().DAY_FORMAT
                )
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}