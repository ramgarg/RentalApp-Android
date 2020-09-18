package com.eazyrento.appbiz

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.eazyrento.R

class AppbizNotificationManager(private val context: Context) {
    companion object {
//        const val TRAKING_CHANAL_ID =  "traking_ch_id"

        private val CHANNEL_ID = "tracking_ch_id"

       // private val CHANNEL_NAME = "Your human readable notification channel name"
       // private val CHANNEL_DESCRIPTION = "description"

        fun createNotificationCompatBuilder(context: Context): NotificationCompat.Builder {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                AppbizNotificationManager(context).createMainNotificationChannel()
                return NotificationCompat.Builder(context, AppbizNotificationManager(context).getMainNotificationId())
            } else {
                return NotificationCompat.Builder(context, CHANNEL_ID)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMainNotificationId(): String {
        return CHANNEL_ID
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createMainNotificationChannel() {

        val importance =NotificationManager.IMPORTANCE_LOW
        val mChannel = NotificationChannel(CHANNEL_ID, context.getString(R.string.app_name), importance)
        mChannel.description = context.getString(R.string.tracking_name)
        mChannel.enableLights(true)
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(mChannel)

    }


}