package com.eazyrento.common.view

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.graphics.drawable.StateListDrawable

class SelectorButton {

    fun makeSelector(color: Int): StateListDrawable? {
        val res =StateListDrawable()
//        res.setExitFadeDuration(400)
//        res.alpha = 45
        res.addState(intArrayOf(R.attr.state_pressed), ColorDrawable(color))

        res.addState(intArrayOf(), ColorDrawable(Color.TRANSPARENT))
        return res
    }
}