package com.example.clocks.viewmodel

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CircleInnerColorViewModel(
    private var red: Int = 0,
    private var green: Int = 0,
    private var blue: Int = 0,
    private var alpha: Int = 0
) : ViewModel() {

    private val _resultVM = MutableLiveData<Int>()
    val resultVM: LiveData<Int> get() = _resultVM

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveColor(
        red: Int = this.red,
        green: Int = this.green,
        blue: Int = this.blue,
        alpha: Int = this.alpha
    ) {
        this.red = red
        this.green = green
        this.blue = blue
        this.alpha = alpha
        _resultVM.value = Color.argb(alpha, red, green, blue)
    }
}