package com.happycal.calendar

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class CalendarViewModel : ViewModel() {
    val mCalendar : TSLiveData<Calendar> = TSLiveData<Calendar>()

    fun setCalendar(calendar : Calendar){
        this.mCalendar.setValue(calendar)
    }

    fun startRecord(id : Any){
        Log.d("id","id : " + this.mCalendar.value)
    }

}