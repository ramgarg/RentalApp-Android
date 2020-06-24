package com.eazyrento.appbiz

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.eazyrento.R

class CustomProgrssBar(val activity: Activity) {

    private var progrssBar:ProgressBar?=null
    private var layout:RelativeLayout?=null

    /*init {

        layout = RelativeLayout(activity)
        layout?.setBackgroundColor(Color.TRANSPARENT)
//        layout.alpha =0.0f
        val progrssBar = ProgressBar(activity,null, R.attr.progressBarStyle)
        progrssBar.setBackgroundColor(Color.TRANSPARENT)
        progrssBar.isIndeterminate = true
        val params =  RelativeLayout.LayoutParams(100,100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)

        layout?.addView(progrssBar,params)

        activity.setContentView(layout)

    }*/

    fun visible(){
        //layout?.visibility = View.VISIBLE
    }
    fun hide(){

        //layout?.visibility = View.GONE

    }
}