package com.eazyrento

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.login.view.LoginUserActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"From:${remoteMessage.from}")

        remoteMessage.data.isNotEmpty().let {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Message data payload: "+remoteMessage.data)
            //handle message here
        }
        remoteMessage.notification?.let {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Message notification body: ${it.body}")
            sendNotification(it.body)
        }
    }

    override fun onNewToken(token: String) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "onNewToken: $token")
        Session.getInstance(this)?.savePushNotificationToken(token)
    }

    private fun sendNotification(messageBody: String?) {
        val intent = Intent(this, LoginUserActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }



    /*private val mTAG = "MessagingService"


    *//*override fun onNewToken(token: String) {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        FirebaseMessaging.getInstance().subscribeToTopic("all")
        Log.d(TAG, "Refreshed token: $refreshedToken")


         sendRegistrationToServer(refreshedToken.toString());
    } *//*

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
            val intent = Intent(this@MessagingService, CustomerMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("message", remoteMessage.notification?.body!!)
            startActivity(intent)
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
    }*/


}

fun fcmTokenByFirebaseInstanceId(context: Context){
    FirebaseInstanceId.getInstance().instanceId
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"getInstanceId failed: ${task.exception}")
                return@OnCompleteListener
            }
            // Get new Instance ID token
            val token = task.result?.token
            // Log and toast
            Session.getInstance(context)?.savePushNotificationToken(token)

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"fcmTokenByFirebaseInstanceId token $token")
        })
}