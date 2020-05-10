package com.eazyrento.supporting

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.eazyrento.Constant
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult


class LocationPermissionUser(val activity: Activity) {


     fun verifingPermission(verifyUserPermission:VerifyUserPermission) {


        if (ContextCompat.checkSelfPermission(
                activity.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED

        ) {
            verifyUserPermission.onSuccess()

        } else {
              verifyUserPermission.onFailler()
        }
    }


    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        mLocationPermissionGranted = false
        when (requestCode) {
            Constant.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
//                    mLocationPermissionGranted = true

                }
            }
        }
    }

    fun locationCreater(fusedLocationClient: FusedLocationProviderClient, locationCreator:LocationGetter){
        val locationRequest = LocationRequest.create()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setInterval(20 * 1000)
       val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult == null) {
                    return
                }
                for (location in locationResult.locations) {
                    if (location != null) {
                        locationCreator.onLocation(location)
//                        fusedLocationClient.removeLocationUpdates(locationCallback)
                    }
                }
            }
        }
    }
}
interface VerifyUserPermission{
      fun onSuccess()
     fun onFailler()
}
interface LocationGetter{
    fun onLocation(location:Location)
}