package com.eazyrento.agent.view

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

class CustomViewOutlineProvider : ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {
        val w= view?.width
        val h = view?.height
        outline?.setRoundRect(0,0,200,200,20.0f)

//        view?.getWidth()?.let { outline?.setRoundRect(0, 0, it, view?.getHeight(), 30.0f) }
    }
}