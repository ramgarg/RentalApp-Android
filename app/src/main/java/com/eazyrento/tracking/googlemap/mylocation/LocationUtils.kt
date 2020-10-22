package com.appbiz.location

import android.content.Context
import android.location.LocationManager

interface LocationUtils{
    companion object{

        fun Context.isGPSEnabled():Boolean{

           val lm:LocationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager

           return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
    }
}