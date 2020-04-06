package com.rental.login.model.modelclass

import android.app.Activity
import android.os.Build
import com.rental.BuildConfig
import java.lang.reflect.Constructor

data class DeviceInfo(
    val android_id: String,
    val app_version: String?=BuildConfig.VERSION_NAME,
    val imei: String?="",
    val os_version: String?=""+Build.VERSION.SDK_INT,
    val platform: String?="Android",
    val push_token: String?=""
)