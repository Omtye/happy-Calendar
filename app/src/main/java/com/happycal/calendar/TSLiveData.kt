package com.happycal.calendar

import androidx.lifecycle.MutableLiveData


class TSLiveData<T> : MutableLiveData<T>() {

    override fun setValue(value: T) {
        super.setValue(value)
    }
}