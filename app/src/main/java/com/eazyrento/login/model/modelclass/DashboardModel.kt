package com.eazyrento.login.model.modelclass


import com.google.gson.annotations.SerializedName

data class DashboardModel(
    @SerializedName("wishlist_count")
    val wishlistCount: Int,
    @SerializedName("notification_count")
    val notificationCount: Int
)