package com.example.clocks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CircleWidthViewModel : ViewModel() {

    private val _resultVM = MutableLiveData<Float>()
    val resultVM: LiveData<Float> get() = _resultVM

    fun saveCircleWidth(circleWidth: Float) {
        _resultVM.value = circleWidth
    }
}