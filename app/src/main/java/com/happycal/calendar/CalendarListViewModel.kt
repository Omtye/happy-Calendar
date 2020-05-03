package com.happycal.calendar

import androidx.lifecycle.ViewModel
import java.util.*


class CalendarListViewModel : ViewModel() {

    var mCurrentTime : Long? = null

    var mTitle = TSLiveData<String>()
    var mCalendarList : TSLiveData<ArrayList<Any>> = TSLiveData<ArrayList<Any>>()

    var mCenterPosition : Int? = null

    fun setTitle(position: Int) {
        try {
            val item = mCalendarList.value!![position]
            if (item is Long) {
                setTitle(item)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    fun setTitle(time: Long) {

        mCurrentTime = time

        mTitle.setValue(DateFormat().getDate(time, DateFormat().CALENDAR_HEADER_FORMAT)!!)

    }


    fun initCalendarList() {
        val cal = GregorianCalendar()
        setCalendarList(cal)
    }




    fun setCalendarList(cal: GregorianCalendar) {
        setTitle(cal.timeInMillis)
        val calendarList: ArrayList<Any> = ArrayList()
        for (i in -300..299) {
            try {
                val calendar = GregorianCalendar(
                    cal[Calendar.YEAR],
                    cal[Calendar.MONTH] + i,
                    1,
                    0,
                    0,
                    0
                )
                if (i == 0) {
                    mCenterPosition = calendarList.size
                }
                calendarList.add(calendar.timeInMillis)
                val dayOfWeek =
                    calendar[Calendar.DAY_OF_WEEK] - 1 //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
                val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) // 해당 월에 마지막 요일
                for (j in 0 until dayOfWeek) {
                    calendarList.add(Keys().EMPTY)
                }
                for (j in 1..max) {
                    calendarList.add(
                        GregorianCalendar(
                            calendar[Calendar.YEAR],
                            calendar[Calendar.MONTH],
                            j
                        )
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        mCalendarList.setValue(calendarList)
    }



}