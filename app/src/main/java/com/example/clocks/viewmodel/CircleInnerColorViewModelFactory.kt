package com.example.clocks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CircleInnerColorViewModelFactory(
    private var red: Int,
    private var green: Int,
    private var blue: Int,
    private var alpha: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CircleInnerColorViewModel(red, green, blue, alpha) as T
    }
}