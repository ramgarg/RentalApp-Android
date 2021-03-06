package com.eazyrento.login.model.modelclass

import android.os.Build
import com.eazyrento.BuildConfig

data class DeviceInfo(
    val android_id: String,
    val app_version: String,
    val imei: String?,
    val os_version: String,
    val platform: String,
    val push_token: String?
)