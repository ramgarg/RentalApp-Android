package com.eazyrento.login.model.modelclass

import android.os.Build
import com.eazyrento.BuildConfig

data class DeviceInfo(
    val android_id: String,
    val app_version: String?=BuildConfig.VERSION_NAME,
    val imei: String?="",
    val os_version: String?=""+Build.VERSION.CODENAME,
    val platform: String?="android",
    val push_token: String?=""
)