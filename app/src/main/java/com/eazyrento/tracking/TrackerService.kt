package com.eazyrento.tracking

import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.AppbizNotificationManager
import com.eazyrento.appbiz.location.AppBizLocationCallback
import com.eazyrento.appbiz.location.AppBizLocationProvider
import com.google.android.gms.location.LocationResult

class TrackerService : Service() {

    companion object{
        const val SERVICE_ID = 1
    }
    private val receiverStop = object :BroadcastReceiver(){

        override fun onReceive(context: Context?, intent: Intent?) {


            // Stop the service when the notification is tapped

            unregisterStopReceiver()
            stopSelf()
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"tracking BroadcastReceiver stop")
        }

    }

    private fun registerStopServiceReciver() {
        registerReceiver(receiverStop, IntentFilter(TrackingConstant.BRODCAST_EVENT_TRACKING_SERVICE_STOP))
    }

    private fun unregisterStopReceiver(){
        unregisterReceiver(receiverStop)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        registerStopServiceReciver()

        startForeground(SERVICE_ID, buildNotification().build())

        // location provider...
        AppBizLocationProvider().requestLocationUpdate(this,object : AppBizLocationCallback{
            override fun onLocation(locationResult: LocationResult?) {

                    if(locationResult!=null) {
                        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Location : ${locationResult.locations}")
                    }
                  else{
                        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Location Result is NULL")
                    }

            }

        })
    }

    private fun buildNotification(): NotificationCompat.Builder {

       return AppbizNotificationManager.createNotificationCompatBuilder(this).apply {
            setContentTitle(getString(R.string.app_name))
            setContentText(getString(R.string.tracking_name))
            setOngoing(true)
            setSmallIcon(R.drawable.ic_tracker)
            setContentIntent(PendingIntent.getBroadcast(this@TrackerService,
                TrackingConstant.TRAKING_BRODCAST_REQUEST_CODE,Intent(TrackingConstant.BRODCAST_EVENT_TRACKING_SERVICE_STOP),
                PendingIntent.FLAG_UPDATE_CURRENT
            ))
        }

    }

    override fun onDestroy() {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onDestroy Traker service is Finished")
        super.onDestroy()
    }

}
