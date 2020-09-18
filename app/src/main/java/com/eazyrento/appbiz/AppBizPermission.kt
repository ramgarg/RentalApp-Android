package com.eazyrento.appbiz

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eazyrento.appbiz.AppBizPermission.Companion.isPermissionGiven

interface AppBizPermission {

    // Check location permission is granted - if it is, start
    // the service, otherwise request the permission


    companion object{

        const val LOCATION_GPS_REQUEST_CODE = 100

        fun Context.isPermissionGiven(strPermission: String):Boolean{

            val permission = ContextCompat.checkSelfPermission(this, strPermission)

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Permission provided if value is Zero == $permission")

            return permission == PackageManager.PERMISSION_GRANTED

        }

        fun Activity.requestPermission(strPermission: String){

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Permission is requested == $strPermission")

            ActivityCompat.requestPermissions(this, arrayOf(strPermission), LOCATION_GPS_REQUEST_CODE)
        }
    }

}