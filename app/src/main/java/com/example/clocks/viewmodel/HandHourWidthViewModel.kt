package com.example.clocks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HandHourWidthViewModel : ViewModel() {

    private val _resultVM = MutableLiveData<Float>()
    val resultVM: LiveData<Float> get() = _resultVM

    fun saveHandWidth(handWidth: Float) {
        _resultVM.value = handWidth
    }
}