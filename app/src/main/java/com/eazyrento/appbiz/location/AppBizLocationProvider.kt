package com.eazyrento.appbiz.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.AppBizPermission.Companion.isPermissionGiven
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY

class AppBizLocationProvider{
    companion object{
        const val INTERVAL = 5000L
        const val FASTED_INTERVAL = 3000L
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdate(context: Context, locationCallback: AppBizLocationCallback){

        val client = LocationServices.getFusedLocationProviderClient(context)

        if (context.isPermissionGiven(Manifest.permission.ACCESS_FINE_LOCATION)){

            client.requestLocationUpdates( LocationRequest().apply{
                interval = INTERVAL
                fastestInterval = FASTED_INTERVAL
                priority = PRIORITY_HIGH_ACCURACY
            },object : LocationCallback(){
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationCallback.onLocation(locationResult)
                }
            },null)
        }
        else{
//            activity.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Permission is not provided")
        }


    }
}

interface AppBizLocationCallback{

    fun onLocation(locationResult: LocationResult?)
}


