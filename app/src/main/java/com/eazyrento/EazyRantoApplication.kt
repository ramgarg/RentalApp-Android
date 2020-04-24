package com.eazyrento

import android.app.Application
import android.content.Context

class EazyRantoApplication : Application() {

    companion object{
        var context:Context?=null
    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }

}