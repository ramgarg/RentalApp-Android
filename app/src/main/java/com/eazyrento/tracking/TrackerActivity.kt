package com.eazyrento.tracking

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizPermission
import com.eazyrento.appbiz.AppBizPermission.Companion.isPermissionGiven
import com.eazyrento.appbiz.AppBizPermission.Companion.requestPermission
import com.eazyrento.appbiz.location.Utils.Companion.isGPSEnabled

class TrackerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tracker)

// location provider chk
        if (isGPSEnabled().not()) {
            Toast.makeText(this,getString(R.string.location_enable), Toast.LENGTH_LONG).show()
             finish()
        }
// permission check
        if (isPermissionGiven(Manifest.permission.ACCESS_FINE_LOCATION)){
            startTrackerService()
        }
        else
            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun startTrackerService() {

        startService(Intent(this,TrackerService::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == AppBizPermission.LOCATION_GPS_REQUEST_CODE &&
            grantResults.size==1 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            startTrackerService()
        }else
        {
            finish()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}