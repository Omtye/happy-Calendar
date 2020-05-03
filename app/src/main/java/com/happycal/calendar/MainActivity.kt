package com.happycal.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.happycal.calendar.databinding.CalendarListBinding


class MainActivity : AppCompatActivity() {

    /*var mCalendarList : MutableLiveData<ArrayList<Object>> = MutableLiveData<ArrayList<Object>>()*/
    private lateinit var binding : CalendarListBinding
    private lateinit var model   : CalendarListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        lateinit var viewModelFactory : ViewModelProvider.AndroidViewModelFactory

        if(viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }

        model   = ViewModelProvider(this, viewModelFactory).get(CalendarListViewModel::class.java)

        binding.model = model
        binding.lifecycleOwner = this

        val nameObserver = Observer<ArrayList<Any>>{
            var view    : RecyclerView      = binding.pagerCalendar
            var adapter : CalendarAdapter   = view.adapter as CalendarAdapter

            if(adapter != null){
                adapter.setCalendarList(it)
            } else {
              var manager : StaggeredGridLayoutManager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
                adapter = CalendarAdapter(it)
            }

        }



        /*private fun setCalendarList() : Unit{

        var cal : GregorianCalendar = GregorianCalendar()

        var calendarList :ArrayList<Object> = ArrayList<Object>()

        for (i in -300 until 300){
            try{
                var calendar : GregorianCalendar = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0,0 )

                calendarList.add(calendar.timeInMillis as Object)

                var dayOfWeek : Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
                var max : Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                for(j in 1 until dayOfWeek){
                    calendarList.add(" " as Object)
                }

                for(j in 1 until max){
                    calendarList.add(GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j) as Object)
                }
            }catch (e : Exception){
                e.printStackTrace();
            }
        }

        mCalendarList.value = calendarList
    }*/
    }

}
