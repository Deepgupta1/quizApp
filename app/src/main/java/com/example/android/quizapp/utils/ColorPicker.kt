package com.example.android.quizapp.utils

import android.util.Log

object ColorPicker {
    var color = arrayOf("#FFBB86FC","#FF6200EE","#FF3700B3","#FF03DAC5","#18206F")
    var currentColor =0

    fun getColor():String{
        currentColor=(currentColor+1)% color.size
        Log.d("current----", currentColor.toString())
        return color[currentColor]
    }
}