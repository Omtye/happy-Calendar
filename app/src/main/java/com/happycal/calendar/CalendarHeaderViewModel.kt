package com.happycal.calendar

import androidx.lifecycle.ViewModel

class CalendarHeaderViewModel  : ViewModel() {
    var mHeaderDate : TSLiveData<Long> = TSLiveData()

    open fun setHeaderDate(headerDate : Long){
        this.mHeaderDate.setValue(headerDate)
    }

}