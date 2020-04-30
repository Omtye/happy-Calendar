package com.happycal.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.happycal.calendar.databinding.ItemCalendarHeaderBinding
import com.happycal.calendar.databinding.ItemDayBinding
import com.happycal.calendar.databinding.ItemDayEmptyBinding
import java.util.*


class CalendarAdapter : RecyclerView.Adapter<ViewHolder>() {

    val HEADER_TYPE : Int = 0
    val EMPTY_TYPE  : Int = 1
    val DAY_TYPE    : Int = 2
    var mCalendarList : List<Object>? = null


    class CalendarAdapter() {

        constructor(mCalendarList: List<Object>?) {
            this.mCalendarList = mCalendarList
        }
    }






    fun setCalendarList(calendarList : List<Object>){
        mCalendarList = calendarList
        notifyDataSetChanged()
    }



    override fun getItemViewType(position: Int): Int {
        var item : Any = mCalendarList!!.get(position)

        if (item is Long)
            return HEADER_TYPE
        else if(item is String)
            return EMPTY_TYPE
        else
            return DAY_TYPE
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType === HEADER_TYPE) { // 날짜 타입
            val binding: ItemCalendarHeaderBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_calendar_header,
                parent,
                false
            )
            val params =
                binding.root.layoutParams as StaggeredGridLayoutManager.LayoutParams
            params.isFullSpan = true //Span을 하나로 통합하기
            binding.root.layoutParams = params
            return HeaderViewHolder(binding)
        } else if (viewType === EMPTY_TYPE) { //비어있는 일자 타입
            val binding: ItemDayEmptyBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_day_empty,
                parent,
                false
            )
            return EmptyViewHolder(binding)
        }
        val binding: ItemDayBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_day,
            parent,
            false
        ) // 일자 타입

        return DayViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (mCalendarList != null)
            return mCalendarList!!.size

        return 0;
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        if (viewType == HEADER_TYPE) { //날짜 타입 꾸미기
            val holder = viewHolder as HeaderViewHolder
            val item: Any = mCalendarList!![position]
            val model = CalendarHeaderViewModel()
            if (item is Long) {
                model.setHeaderDate(item)
            }
            holder.setViewModel(model)
        } else if (viewType == EMPTY_TYPE) { //비어있는 날짜 타입 꾸미기
            val holder = viewHolder as EmptyViewHolder
            val model = EmptyViewModel()
            holder.setViewModel(model)
        } else if (viewType == DAY_TYPE) { // 일자 타입 꾸미기
            val holder = viewHolder as DayViewHolder
            val item: Any = mCalendarList!![position]
            val model = CalendarViewModel()
            if (item is Calendar) {
                model.setCalendar(item as Calendar)
            }
            holder.setViewModel(model)
        }
    }



    private class HeaderViewHolder : ViewHolder {
        private var binding: ItemCalendarHeaderBinding? = null

        constructor(binding: ItemCalendarHeaderBinding) : super(binding.root){
            this.binding = binding
        }

        fun setViewModel(model: CalendarHeaderViewModel) {
            binding!!.model = model
            binding!!.executePendingBindings()
        }
    }



    private class EmptyViewHolder : ViewHolder{
        private var binding: ItemDayEmptyBinding? = null

        constructor(binding: ItemDayEmptyBinding) : super(binding.root){
            this.binding = binding
        }

        fun setViewModel(model: EmptyViewModel) {
            binding!!.model = model
            binding!!.executePendingBindings()
        }
    }



    private class DayViewHolder : ViewHolder{
        private var binding: ItemDayBinding? = null

        constructor(binding: ItemDayBinding) : super(binding.root){
            this.binding = binding
        }

        fun setViewModel(model: CalendarViewModel) {
            binding!!.model = model
            binding!!.executePendingBindings()
        }
    }





}