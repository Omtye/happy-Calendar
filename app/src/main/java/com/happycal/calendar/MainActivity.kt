package com.happycal.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.happycal.calendar.databinding.CalendarListBinding
import sun.util.locale.provider.LocaleProviderAdapter.getAdapter


class MainActivity : AppCompatActivity() {

    /*var mCalendarList : MutableLiveData<ArrayList<Object>> = MutableLiveData<ArrayList<Object>>()*/
    private lateinit var binding : CalendarListBinding
    private lateinit var model   : CalendarListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModelFactory : ViewModelProvider.AndroidViewModelFactory? = null

        if(viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        model = ViewModelProvider(this, viewModelFactory) as CalendarListViewModel

        binding.setModel(model);
        binding.setLifecycleOwner(this);

        val nameObserver = Observer<ArrayList<Any>> { objects ->
            // Update the UI, in this case, a TextView.
            val view = binding.pagerCalendar
            var adapter = view.adapter as CalendarAdapter?
            if (adapter != null) {
                adapter.setCalendarList(objects as List<Object>)
            } else {
                val manager =
                    StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
                adapter = CalendarAdapter()
                view.layoutManager = manager
                view.adapter = adapter
                if (model.mCenterPosition!! >= 0) {
                    view.scrollToPosition(model.mCenterPosition!!)
                }
            }
        }

        observe();
        if (model != null) {
            model.initCalendarList();
        }

    }

    private fun observe() {
        model.mCalendarList.observe(this, Observer<ArrayList<Any>>() {

        })


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
