package com.happycal.calendar

import androidx.lifecycle.ViewModel
import java.util.*

class CalendarViewModel : ViewModel() {
    val mCalendar : TSLiveData<Calendar> = TSLiveData<Calendar>()

    fun setCalendar(calendar : Calendar){
        this.mCalendar.setValue(calendar)
    }
}