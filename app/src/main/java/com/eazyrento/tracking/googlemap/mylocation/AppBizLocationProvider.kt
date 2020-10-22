package com.eazyrento.tracking.googlemap.mylocation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import com.appbiz.location.LocationUtils.Companion.isGPSEnabled
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.AppBizPermission
import com.eazyrento.appbiz.AppBizPermission.Companion.isPermissionGiven
import com.eazyrento.appbiz.AppBizPermission.Companion.requestPermission
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY

class AppBizLocationProvider(private val mActivity:Activity){
    private val mTag = "AppBizLocationProvider"
    private lateinit var mLocationCallback: AppBizLocationCallback
    companion object{
        const val INTERVAL = 5*60*1000L
        const val FASTEST_INTERVAL = 3000L
    }

  fun setLocationCallback(mLocationCallback: AppBizLocationCallback): AppBizLocationProvider {
      this.mLocationCallback = mLocationCallback
      return this
  }
     fun start(): AppBizLocationProvider {

// location provider chk
        if (mActivity.isGPSEnabled().not()) {

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"$mTag ...GPS is off")

            Toast.makeText(mActivity,"Please enable GPS!",Toast.LENGTH_LONG).show()

            mLocationCallback.canRequestLocation(false)
        }
// permission check
        if (mActivity.isPermissionGiven(Manifest.permission.ACCESS_FINE_LOCATION)){
            mLocationCallback.canRequestLocation(true)
        }
        else {

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"$mTag Requesting for permission")

            mActivity.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)

            mLocationCallback.canRequestLocation(false)
        }

         return this

    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdate(context: Context){

        val client = LocationServices.getFusedLocationProviderClient(context)

        if (context.isPermissionGiven(Manifest.permission.ACCESS_FINE_LOCATION)){

            client.requestLocationUpdates( LocationRequest().apply{
                interval = INTERVAL
                fastestInterval = FASTEST_INTERVAL
                priority = PRIORITY_HIGH_ACCURACY
            },object : LocationCallback(){
                override fun onLocationResult(locationResult: LocationResult?) {
                    mLocationCallback.onLocation(locationResult)
                }
            },null)
        }
        else{
//            activity.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Permission is not provided")
        }


    }



    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == AppBizPermission.LOCATION_GPS_REQUEST_CODE &&
            grantResults.size==1 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            mLocationCallback.permissionGiven()
        }else
        {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"$mTag permission has not given...activity is finished")

            mActivity.finish()
        }
    }


}

interface AppBizLocationCallback{
    fun canRequestLocation(canRequest:Boolean)
    fun onLocation(locationResult: LocationResult?)
    fun permissionGiven()
}


