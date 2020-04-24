package com.eazyrento

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MessagingService : FirebaseMessagingService() {

    val TAG = "FCM Service"


    /*override fun onNewToken(token: String) {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        FirebaseMessaging.getInstance().subscribeToTopic("all")
        Log.d(TAG, "Refreshed token: $refreshedToken")


         sendRegistrationToServer(refreshedToken.toString());
    } */

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG,"From: "+ "${remoteMessage?.from}")
        remoteMessage?.data?.isNotEmpty()?.let {
            Log.d(TAG,"Message data payload: "+ remoteMessage.data)
            if(!remoteMessage.data.isNullOrEmpty()){
                val msg: String = remoteMessage.data.get("message").toString()
            }

        }
        remoteMessage?.notification?.let {
            sendNotification(remoteMessage.notification?.body)
        }

    }

    private fun sendNotification(messageBody: String?) {
        val intent = Intent(this,CustomerMainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent  = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel= NotificationChannel(channelId,"Readable Title", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0,notificationBuilder.build())
    }


}