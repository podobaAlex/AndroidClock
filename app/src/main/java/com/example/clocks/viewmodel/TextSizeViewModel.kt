package com.example.clocks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextSizeViewModel : ViewModel() {

    private val _resultVM = MutableLiveData<Float>()
    val resultVM: LiveData<Float> get() = _resultVM

    fun saveTextSize(size: Float) {
        _resultVM.value = size
    }

}